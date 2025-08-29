package com.quizapp.services.mongo;

import com.quizapp.models.mongo.QuestionMongo;
import com.quizapp.repository.mongo.QuestionMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionMongoService {

    @Autowired
    private QuestionMongoRepository questionMongoRepository;

    public ResponseEntity<?> getQuestions(String quizId, String userId) {
        try {
            List<QuestionMongo> questions = questionMongoRepository.findByQuizId(quizId);
            if (questions.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> getQuiz(String quizId) {
        try {
            List<QuestionMongo> questions = questionMongoRepository.findByQuizId(quizId);
            if (questions.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public boolean addQuestions(List<QuestionMongo> questions, String quizId) {
        try {
            for (QuestionMongo question : questions) {
                question.setQuizId(quizId);
                questionMongoRepository.save(question);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ResponseEntity<?> getQuizQuestions(String quizId) {
        try {
            List<QuestionMongo> questions = questionMongoRepository.findByQuizId(quizId);
            if (questions.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
