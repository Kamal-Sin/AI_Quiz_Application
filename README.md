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

## Quick Start

### 1. Fix JAVA_HOME (if needed)

If you get JAVA_HOME errors, run:

```bash
fix-java-home.bat
```

### 2. Start the Application

Run the startup script:

```bash
start-app.bat
```

This will start both:

- **Frontend**: http://localhost:3000
- **Backend**: http://localhost:8080
- **H2 Database Console**: http://localhost:8080/h2-console

### 3. Manual Start (Alternative)

**Frontend:**

```bash
npm start
```

**Backend:**

```bash
mvnw.cmd spring-boot:run
```

### 4. Production Mode (MongoDB Atlas)

**Backend with MongoDB:**

```bash
start-prod.bat
```

Or manually:

```bash
set SPRING_PROFILES_ACTIVE=prod
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
- Spring Data JPA
- H2 Database (development)
- MongoDB Atlas (production)

## Development

The application uses:

- **H2 Database** for development (in-memory)
- **Maven Wrapper** for dependency management
- **React Scripts** for frontend development

## Deployment

See `DEPLOYMENT-GUIDE.md` for deployment instructions to various platforms.

## MongoDB Atlas Setup

For production deployment with MongoDB Atlas, see `MONGODB-SETUP.md` for detailed setup instructions.
