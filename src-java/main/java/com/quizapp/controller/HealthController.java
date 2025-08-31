package com.quizapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.lang.management.ManagementFactory;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> healthStatus = new HashMap<>();

        // Basic health status
        healthStatus.put("status", "UP");
        healthStatus.put("message", "Quiz App Backend is running!");
        healthStatus.put("timestamp", System.currentTimeMillis());
        healthStatus.put("version", "1.0.0");

        // Environment information
        healthStatus.put("environment",
                System.getenv("SPRING_PROFILES_ACTIVE") != null ? System.getenv("SPRING_PROFILES_ACTIVE") : "dev");

        // MongoDB connection status
        try {
            String mongoUri = System.getenv("MONGODB_URI");
            if (mongoUri != null && !mongoUri.isEmpty()) {
                healthStatus.put("mongodb", "configured");
                healthStatus.put("mongo_uri_length", mongoUri.length());
            } else {
                healthStatus.put("mongodb", "not_configured");
            }
        } catch (Exception e) {
            healthStatus.put("mongodb", "error");
            healthStatus.put("mongodb_error", e.getMessage());
        }

        // System information
        healthStatus.put("port", System.getenv("PORT"));
        healthStatus.put("java_version", System.getProperty("java.version"));
        healthStatus.put("os_name", System.getProperty("os.name"));
        healthStatus.put("available_memory", Runtime.getRuntime().maxMemory() / 1024 / 1024 + "MB");

        // Application status
        healthStatus.put("application_name", "Quiz App Backend");
        healthStatus.put("uptime", System.currentTimeMillis() - getStartTime());

        return ResponseEntity.ok(healthStatus);
    }

    @GetMapping("/")
    public ResponseEntity<String> root() {
        return ResponseEntity.ok("Quiz App Backend is running! Use /health for health check.");
    }

    @GetMapping("/actuator/health")
    public ResponseEntity<Map<String, Object>> actuatorHealth() {
        return health();
    }

    private long getStartTime() {
        try {
            return ManagementFactory.getRuntimeMXBean().getStartTime();
        } catch (Exception e) {
            return System.currentTimeMillis();
        }
    }
}
