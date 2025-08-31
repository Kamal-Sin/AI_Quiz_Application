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
        healthStatus.put("version", "1.0.0");
        healthStatus.put("environment",
                System.getenv("SPRING_PROFILES_ACTIVE") != null ? System.getenv("SPRING_PROFILES_ACTIVE") : "dev");

        // Add MongoDB connection status if available
        try {
            String mongoUri = System.getenv("MONGODB_URI");
            if (mongoUri != null && !mongoUri.isEmpty()) {
                healthStatus.put("mongodb", "configured");
            } else {
                healthStatus.put("mongodb", "not_configured");
            }
        } catch (Exception e) {
            healthStatus.put("mongodb", "error");
        }

        return ResponseEntity.ok(healthStatus);
    }

    @GetMapping("/")
    public ResponseEntity<String> root() {
        return ResponseEntity.ok("Quiz App Backend is running! Use /health for health check.");
    }
}
