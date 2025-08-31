@echo off
echo ========================================
echo    Quiz Application Startup
echo ========================================
echo.

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
echo.

echo Starting React Frontend...
start "React Frontend" cmd /k "npm start"

echo.
echo Starting Spring Boot Backend...
start "Spring Boot Backend" cmd /k "mvnw.cmd spring-boot:run"

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
