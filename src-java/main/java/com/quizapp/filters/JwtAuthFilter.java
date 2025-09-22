package com.quizapp.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.util.Collections;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Value("${security.jwt.secret:change-me-secret}")
    private String jwtSecret;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Support legacy pid header for backward compatibility
        String legacyPid = request.getHeader("pid");

        String userId = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Jws<Claims> jws = Jwts.parserBuilder()
                        .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8))
                        .build()
                        .parseClaimsJws(token);
                userId = jws.getBody().getSubject();
            } catch (Exception e) {
                // Invalid token → proceed without auth; security rules will block if required
            }
        } else if (StringUtils.hasText(legacyPid) && !"undefined".equals(legacyPid)) {
            try {
                byte[] decoded = Base64.getDecoder().decode(legacyPid);
                userId = new String(decoded, StandardCharsets.UTF_8);
            } catch (IllegalArgumentException ignored) {
            }
        }

        if (StringUtils.hasText(userId) && SecurityContextHolder.getContext().getAuthentication() == null) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(userId, null,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.setAttribute("userId", userId);
        }

        filterChain.doFilter(request, response);
    }
}
