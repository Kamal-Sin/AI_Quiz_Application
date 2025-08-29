# Quiz Application

A full-stack quiz application built with React frontend and Spring Boot backend.

## Project Structure

```
Quizzy/
├── src/                    # React frontend source code
│   ├── components/         # React components
│   ├── utils/             # Utility functions
│   ├── assets/            # Static assets
│   ├── App.js             # Main React app
│   └── index.js           # React entry point
├── src-java/              # Spring Boot backend source code
│   └── main/
│       ├── java/com/quizapp/
│       │   ├── controller/    # REST controllers
│       │   ├── service/       # Business logic
│       │   ├── repository/    # Data access layer
│       │   ├── models/        # Entity classes
│       │   ├── dto/           # Data transfer objects
│       │   └── configurations/ # Spring configurations
│       └── resources/         # Application properties
├── public/                # Static files for React
├── package.json           # Node.js dependencies
├── pom.xml               # Maven dependencies
├── start-app.bat         # Start both applications
├── fix-java-home.bat     # Fix JAVA_HOME issues
└── README.md             # This file
```

## Prerequisites

- Node.js (v14 or higher)
- Java JDK 17 or higher
- Maven (included via mvnw)
- MongoDB Atlas account (free tier)

## Quick Start

### 1. Fix JAVA_HOME (if needed)

If you get JAVA_HOME errors, run:

```bash
fix-java-home.bat
```

### 2. Start the Application

**Development Mode:**

```bash
start-app.bat
```

**Production Mode:**

```bash
start-prod.bat
```

This will start:

- **Frontend**: http://localhost:3000
- **Backend**: http://localhost:8080
- **Database**: MongoDB Atlas (cloud)
- **Health Check**: http://localhost:8080/actuator/health

### 3. Manual Start (Alternative)

**Frontend:**

```bash
npm start
```

**Backend:**

```bash
mvnw.cmd spring-boot:run
```

## Features

- User authentication and registration
- Quiz creation and management
- Real-time quiz taking
- Score tracking and analytics
- Responsive design

## Technology Stack

**Frontend:**

- React 18
- React Router
- Bootstrap 5
- React Toastify

**Backend:**

- Spring Boot 2.7
- Spring Security
- Spring Data MongoDB
- MongoDB Atlas (cloud database)

## Development

The application uses:

- **MongoDB Atlas** for all environments (cloud database)
- **Maven Wrapper** for dependency management
- **React Scripts** for frontend development

## Deployment

### Environment Variables Required:

- `SPRING_PROFILES_ACTIVE=prod` - Use production configuration
- `GEMINI_API_KEY=your_api_key` - For AI quiz generation (optional)

### Health Check Endpoint:

- `GET /actuator/health` - Application health status
- `GET /quiz/health` - Custom health check

### Deployment Platforms:

- **Railway**: Use `railway.json` configuration
- **Render**: Use `Dockerfile` or direct deployment
- **Vercel**: Frontend deployment only

See `DEPLOYMENT-GUIDE.md` for detailed instructions.

## MongoDB Atlas Setup

MongoDB Atlas is configured as the default database. See `MONGODB-SETUP.md` for setup instructions if you need to change the connection string.
