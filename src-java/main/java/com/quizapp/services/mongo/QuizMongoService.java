package com.quizapp.services.mongo;

import com.quizapp.models.mongo.QuizMongo;
import com.quizapp.repository.mongo.QuizMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizMongoService {

    @Autowired
    private QuizMongoRepository quizMongoRepository;

    public ResponseEntity<List<QuizMongo>> allQuizzes(String userId) {
        try {
            List<QuizMongo> quizzes = quizMongoRepository.findByUserIdOrderByDateDesc(userId);
            if (quizzes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.status(HttpStatus.FOUND).body(quizzes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<QuizMongo> getQuiz(String quizId, String userId) {
        try {
            Optional<QuizMongo> quizOpt = quizMongoRepository.findByQuizId(quizId);
            if (!quizOpt.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            QuizMongo quiz = quizOpt.get();
            if (userQuizRelation(userId, quizId)) {
                quiz.setUserId(null); // Hide userId for security
                return ResponseEntity.ok(quiz);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private boolean userQuizRelation(String userId, String quizId) {
        Optional<QuizMongo> quizOpt = quizMongoRepository.findByQuizId(quizId);
        if (!quizOpt.isPresent() || quizOpt.get().getUserId() == null || quizOpt.get().getUserId().trim().isEmpty()) {
            return false;
        }
        return userId.equals(quizOpt.get().getUserId());
    }

    public String createQuiz(QuizMongo quiz) {
        try {
            QuizMongo savedQuiz = quizMongoRepository.save(quiz);
            return savedQuiz.getQuizId();
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<QuizMongo> getQuizInstructions(String quizId) {
        try {
            Optional<QuizMongo> quizOpt = quizMongoRepository.findByQuizId(quizId);
            if (quizOpt.isPresent()) {
                return ResponseEntity.ok(quizOpt.get());
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
