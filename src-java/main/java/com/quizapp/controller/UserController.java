package com.quizapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.models.mongo.UserMongo;
import com.quizapp.models.mongo.UserMongo;
import com.quizapp.services.mongo.UserMongoService;
import com.quizapp.services.mongo.QuizMongoService;
import com.quizapp.services.mongo.QuestionMongoService;
import com.quizapp.services.mongo.AttemptedQuizMongoService;

@RestController
@CrossOrigin(origins = { "http://localhost:3000", "https://ai-quiz-application-ten.vercel.app", "https://*.vercel.app",
        "https://*.railway.app" })
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserMongoService userMongoService;

    @Autowired
    private QuizMongoService quizMongoService;

    @Autowired
    private QuestionMongoService questionMongoService;

    @Autowired
    private AttemptedQuizMongoService attemptedQuizMongoService;

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody UserMongo user) {
        return userMongoService.registerUser(user);
    }

    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> user) {
        return userMongoService.loginUser(user);
    }

    @GetMapping("getUser")
    public ResponseEntity<?> getUser(@RequestAttribute String userId) {
        return userMongoService.getUsers(userId);
    }

    @GetMapping("creations")
    public ResponseEntity<?> allQuizzes(@RequestAttribute String userId) {
        return quizMongoService.allQuizzes(userId);
    }

    @GetMapping("creations/{quizId}")
    public ResponseEntity<?> specificQuiz(@PathVariable String quizId, @RequestAttribute String userId) {
        return quizMongoService.getQuiz(quizId, userId);
    }

    @GetMapping("creations/{quizId}/questions")
    public ResponseEntity<?> getQuestions(@PathVariable("quizId") String quizId,
            @RequestAttribute("userId") String userId) {
        return questionMongoService.getQuestions(quizId, userId);
    }

    @GetMapping("attempted")
    public ResponseEntity<?> getAttempted(@RequestAttribute String userId) {
        return attemptedQuizMongoService.getAttempted(userId);
    }
}
