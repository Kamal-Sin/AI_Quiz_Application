package com.quizapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> healthStatus = new HashMap<>();
        healthStatus.put("status", "UP");
        healthStatus.put("message", "Quiz App Backend is running!");
        healthStatus.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(healthStatus);
    }

    @GetMapping("/")
    public ResponseEntity<String> root() {
        return ResponseEntity.ok("Quiz App Backend is running! Use /health for health check.");
    }
}
