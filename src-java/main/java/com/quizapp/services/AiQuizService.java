package com.quizapp.services;

import com.quizapp.dto.AiQuizRequest;
import com.quizapp.models.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AiQuizService {

    @Value("${gemini.api.key:}")
    private String geminiApiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent";

    public List<Question> generateQuiz(AiQuizRequest request) {
        try {
            if (geminiApiKey == null || geminiApiKey.trim().isEmpty()) {
                throw new RuntimeException(
                        "Gemini API key is not configured. Please set GEMINI_API_KEY environment variable.");
            }

            String prompt = buildPrompt(request);
            String content = callGeminiApi(prompt);

            return parseQuizResponse(content);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate quiz: " + e.getMessage(), e);
        }
    }

    private String callGeminiApi(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> content = new HashMap<>();
        Map<String, Object> part = new HashMap<>();
        part.put("text", prompt);

        List<Map<String, Object>> parts = new ArrayList<>();
        parts.add(part);
        content.put("parts", parts);

        List<Map<String, Object>> contents = new ArrayList<>();
        contents.add(content);
        requestBody.put("contents", contents);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        String url = GEMINI_API_URL + "?key=" + geminiApiKey;

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Map.class);

        Map<String, Object> responseBody = response.getBody();
        if (responseBody != null && responseBody.containsKey("candidates")) {
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");
            if (!candidates.isEmpty()) {
                Map<String, Object> candidate = candidates.get(0);
                Map<String, Object> contentResponse = (Map<String, Object>) candidate.get("content");
                List<Map<String, Object>> partsResponse = (List<Map<String, Object>>) contentResponse.get("parts");
                if (!partsResponse.isEmpty()) {
                    return (String) partsResponse.get(0).get("text");
                }
            }
        }

        throw new RuntimeException("Invalid response from Gemini API");
    }

    private String buildPrompt(AiQuizRequest request) {
        return String.format(
                "Generate a quiz with 5 multiple choice questions for %s level, %s difficulty, on the subject '%s' and topic '%s'. "
                        +
                        "Format the response exactly as follows:\n\n" +
                        "Q1. [Question text]\n" +
                        "A) [Option A]\n" +
                        "B) [Option B]\n" +
                        "C) [Option C]\n" +
                        "D) [Option D]\n" +
                        "Correct: [A/B/C/D]\n" +
                        "Points: 1\n\n" +
                        "Q2. [Question text]\n" +
                        "A) [Option A]\n" +
                        "B) [Option B]\n" +
                        "C) [Option C]\n" +
                        "D) [Option D]\n" +
                        "Correct: [A/B/C/D]\n" +
                        "Points: 1\n\n" +
                        "Continue for all 5 questions. Make sure the questions are appropriate for %s level and %s difficulty.",
                request.getGrade(), request.getDifficulty(), request.getSubject(), request.getTopic(),
                request.getGrade(), request.getDifficulty());
    }

    private List<Question> parseQuizResponse(String content) {
        List<Question> questions = new ArrayList<>();

        // Split content by question blocks
        String[] questionBlocks = content.split("Q\\d+\\.");

        for (int i = 1; i < questionBlocks.length && i <= 5; i++) { // Skip first empty element
            String block = questionBlocks[i].trim();
            if (block.isEmpty())
                continue;

            try {
                Question question = parseQuestionBlock(block, i);
                questions.add(question);
            } catch (Exception e) {
                // Log error but continue with other questions
                System.err.println("Error parsing question " + i + ": " + e.getMessage());
            }
        }

        return questions;
    }

    private Question parseQuestionBlock(String block, int questionNumber) {
        // Extract question text (everything before the first option)
        String[] lines = block.split("\n");
        String questionText = lines[0].trim();

        // Extract options
        String optionA = "", optionB = "", optionC = "", optionD = "";
        String correctAnswer = "";
        int points = 1;

        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("A)")) {
                optionA = line.substring(2).trim();
            } else if (line.startsWith("B)")) {
                optionB = line.substring(2).trim();
            } else if (line.startsWith("C)")) {
                optionC = line.substring(2).trim();
            } else if (line.startsWith("D)")) {
                optionD = line.substring(2).trim();
            } else if (line.startsWith("Correct:")) {
                correctAnswer = line.substring(8).trim();
            } else if (line.startsWith("Points:")) {
                try {
                    points = Integer.parseInt(line.substring(7).trim());
                } catch (NumberFormatException e) {
                    points = 1; // Default to 1 point
                }
            }
        }

        Question question = new Question();
        question.setQuestionNo(String.valueOf(questionNumber));
        question.setQuestion(questionText);
        question.setOption1(optionA);
        question.setOption2(optionB);
        question.setOption3(optionC);
        question.setOption4(optionD);
        question.setCorrect(correctAnswer);
        question.setPoints("1"); // Always set to 1 point for AI-generated quizzes

        return question;
    }
}
