@echo off
echo ========================================
echo    Quiz Application - Production Mode
echo ========================================
echo.

echo Starting Spring Boot Backend with MongoDB Atlas...
echo.

REM Set production profile
set "SPRING_PROFILES_ACTIVE=prod"

REM Start backend with production configuration
start "Spring Boot Backend (Production)" cmd /k "mvnw.cmd spring-boot:run"

echo.
echo ========================================
echo Backend is starting with MongoDB Atlas...
echo.
echo Backend:  http://localhost:8080
echo Health:   http://localhost:8080/quiz/health
echo ========================================
echo.
echo Note: Frontend should be deployed separately
echo (e.g., to Vercel, Netlify, etc.)
echo.
pause
