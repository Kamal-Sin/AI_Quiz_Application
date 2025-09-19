package com.quizapp.filters;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.quizapp.services.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class IdFilter extends OncePerRequestFilter {

    @Autowired
    UserService userService;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers",
                    "pid, Content-Type, Accept, X-Requested-With, remember-me");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        String url = request.getRequestURI();

        if (url.equals("/user/login") || url.equals("/user/register") || url.equals("/quiz/generate-ai")
                || url.equals("/quiz/health")) {
            filterChain.doFilter(request, response);
            return;
        }

        String encodedUid = request.getHeader("pid");
        if (encodedUid == null || encodedUid.isEmpty() || "undefined".equals(encodedUid)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        String uid;
        try {
            byte[] dUid = Base64.getDecoder().decode(encodedUid);
            uid = new String(dUid);
        } catch (IllegalArgumentException ex) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        if (!userService.checkUser(uid)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        request.setAttribute("userId", uid);
        filterChain.doFilter(request, response);
    }

}
