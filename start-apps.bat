@echo off
echo ========================================
echo    Quiz Application Startup Script
echo ========================================
echo.

echo Starting React Frontend...
start "React Frontend" cmd /k "npm start"

echo.
echo Starting Spring Boot Backend...
start "Spring Boot Backend" cmd /k "set JAVA_HOME=C:\Program Files\Java\jdk-23 && mvnw.cmd spring-boot:run"

echo.
echo ========================================
echo Applications are starting...
echo.
echo Frontend: http://localhost:3000
echo Backend:  http://localhost:8080
echo H2 Console: http://localhost:8080/h2-console
echo ========================================
echo.
echo Press any key to close this window...
pause > nul
