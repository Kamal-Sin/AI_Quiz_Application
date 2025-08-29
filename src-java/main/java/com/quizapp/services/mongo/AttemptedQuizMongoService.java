package com.quizapp.services.mongo;

import com.quizapp.models.mongo.AttemptedQuizMongo;
import com.quizapp.repository.mongo.AttemptedQuizMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AttemptedQuizMongoService {

    @Autowired
    private AttemptedQuizMongoRepository attemptedQuizMongoRepository;

    public ResponseEntity<?> getAttempted(String userId) {
        try {
            List<AttemptedQuizMongo> attemptedQuizzes = attemptedQuizMongoRepository.findByUserId(userId);
            if (attemptedQuizzes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You have not attempted any quiz ...");
            }
            return ResponseEntity.ok(attemptedQuizzes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong! Try Again...");
        }
    }

    public boolean checkAttempted(String userId, String quizId) {
        try {
            return attemptedQuizMongoRepository.existsByUserIdAndQuizId(userId, quizId);
        } catch (Exception e) {
            return false;
        }
    }

    public ResponseEntity<?> submitQuiz(String quizId, String userId, String date, Map<String, String> answers) {
        try {
            // Calculate points based on answers (simplified implementation)
            int points = calculatePoints(answers);
            String totalPoints = "100"; // This should be calculated from quiz questions

            AttemptedQuizMongo attemptedQuiz = new AttemptedQuizMongo(userId, quizId, String.valueOf(points), date,
                    totalPoints);
            attemptedQuizMongoRepository.save(attemptedQuiz);

            Map<String, Object> result = new HashMap<>();
            result.put("points", points);
            result.put("totalPoints", totalPoints);
            result.put("message", "Quiz submitted successfully!");

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit quiz");
        }
    }

    private int calculatePoints(Map<String, String> answers) {
        // Simplified point calculation - in real implementation,
        // you would compare answers with correct answers from database
        int points = 0;
        for (String answer : answers.values()) {
            if (answer != null && !answer.trim().isEmpty()) {
                points += 10; // 10 points per answered question
            }
        }
        return Math.min(points, 100); // Cap at 100 points
    }
}
