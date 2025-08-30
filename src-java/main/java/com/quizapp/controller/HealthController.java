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
        System.out.println("Health check endpoint called at: " + System.currentTimeMillis());

        Map<String, Object> healthStatus = new HashMap<>();
        healthStatus.put("status", "UP");
        healthStatus.put("message", "Quiz App Backend is running!");
        healthStatus.put("timestamp", System.currentTimeMillis());
        healthStatus.put("version", "1.0.0");
        healthStatus.put("environment",
                System.getenv("SPRING_PROFILES_ACTIVE") != null ? System.getenv("SPRING_PROFILES_ACTIVE") : "dev");

        System.out.println("Health check response: " + healthStatus);
        return ResponseEntity.ok(healthStatus);
    }

    @GetMapping("/")
    public ResponseEntity<String> root() {
        return ResponseEntity.ok("Quiz App Backend is running! Use /health for health check.");
    }
}
