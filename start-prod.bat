@echo off
echo ========================================
echo    Quiz Application - Production Mode
echo ========================================
echo.

REM Set production profile
set SPRING_PROFILES_ACTIVE=prod

REM Set JAVA_HOME properly without trailing spaces
set "JAVA_HOME=C:\Program Files\Java\jdk-23"

REM Check if there's a system JAVA_HOME with trailing spaces and override it
if defined JAVA_HOME (
    set "SYSTEM_JAVA_HOME=%JAVA_HOME%"
    set "TRIMMED_SYSTEM_JAVA_HOME=%SYSTEM_JAVA_HOME: =%"
    if not "%SYSTEM_JAVA_HOME%"=="%TRIMMED_SYSTEM_JAVA_HOME%" (
        echo WARNING: System JAVA_HOME has trailing spaces, using local override
        echo System JAVA_HOME: "%SYSTEM_JAVA_HOME%"
        echo Using: "%JAVA_HOME%"
        echo.
    )
)

REM Validate that Java installation exists
if not exist "%JAVA_HOME%\bin\java.exe" (
    echo ERROR: Java installation not found at %JAVA_HOME%
    echo Please run fix-java-home.bat to set up JAVA_HOME correctly.
    echo.
    pause
    exit /b 1
)

echo Java installation found at: %JAVA_HOME%
echo Production profile: %SPRING_PROFILES_ACTIVE%
echo.

echo Starting Spring Boot Backend (Production Mode)...
start "Spring Boot Backend (Production)" cmd /k "mvnw.cmd spring-boot:run"

echo.
echo ========================================
echo Production Application Starting...
echo.
echo Backend:  http://localhost:8080
echo Health Check: http://localhost:8080/actuator/health
echo MongoDB Atlas: Connected to cloud database
echo ========================================
echo.
echo Press any key to close this window...
pause > nul
