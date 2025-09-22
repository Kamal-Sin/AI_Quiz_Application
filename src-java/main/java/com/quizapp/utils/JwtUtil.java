package com.quizapp.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    @Value("${security.jwt.secret:change-me-secret}")
    private String jwtSecret;

    @Value("${security.jwt.ttlMillis:86400000}")
    private long ttlMillis;

    public String generateToken(String userId) {
        long now = System.currentTimeMillis();
        Date issuedAt = new Date(now);
        Date expiry = new Date(now + ttlMillis);
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(issuedAt)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes(StandardCharsets.UTF_8))
                .compact();
    }
}
