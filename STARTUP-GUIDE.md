# Quiz Application Startup Guide

## Option 1: Using Batch Script (Recommended)

Double-click `start-apps.bat` to start both applications automatically.

## Option 2: Using PowerShell Script

Right-click `start-apps.ps1` and select "Run with PowerShell"

## Option 3: Manual Startup

### Step 1: Start Backend

1. Open Command Prompt or PowerShell
2. Navigate to project directory: `cd "C:\Users\kamal\Desktop\Projects\Project Final Year"`
3. Set JAVA_HOME: `set JAVA_HOME=C:\Program Files\Java\jdk-23`
4. Start Spring Boot: `.\mvnw.cmd spring-boot:run`

### Step 2: Start Frontend (in new terminal)

1. Open new Command Prompt or PowerShell
2. Navigate to project directory: `cd "C:\Users\kamal\Desktop\Projects\Project Final Year"`
3. Start React: `npm start`

## Application URLs

- **Frontend**: http://localhost:3000
- **Backend**: http://localhost:8080
- **H2 Database Console**: http://localhost:8080/h2-console

## Troubleshooting

### If JAVA_HOME is not set:

```cmd
set JAVA_HOME=C:\Program Files\Java\jdk-23
```

### If port 8080 is in use:

```cmd
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

### If port 3000 is in use:

```cmd
netstat -ano | findstr :3000
taskkill /PID <PID> /F
```

### To stop applications:

- Press `Ctrl+C` in each terminal window
- Or close the terminal windows

## Features Available

- ✅ User Registration and Login
- ✅ Create Custom Quizzes
- ✅ Take Quizzes
- ✅ View Recent Attempts
- ✅ AI-Generated Quizzes (using Gemini API)
- ✅ Responsive UI with Bootstrap
