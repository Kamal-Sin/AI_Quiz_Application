#!/bin/bash

echo "=========================================="
echo "Starting Quiz App Backend..."
echo "=========================================="

# Check if MongoDB URI is set (optional for testing)
if [ -z "$MONGODB_URI" ]; then
    echo "INFO: MONGODB_URI environment variable is not set, using hardcoded URL for testing"
fi

# Check if database name is set
if [ -z "$MONGODB_DATABASE" ]; then
    echo "INFO: Using default database name: quizapp"
fi

# Check if port is set
if [ -z "$PORT" ]; then
    echo "INFO: Using default port: 8080"
fi

# Check if JAR file exists
if [ ! -f "target/quiz-app-1.0-SNAPSHOT.jar" ]; then
    echo "ERROR: JAR file not found at target/quiz-app-1.0-SNAPSHOT.jar"
    echo "Building the application..."
    ./mvnw clean package -DskipTests
fi

# Verify JAR file exists after build
if [ ! -f "target/quiz-app-1.0-SNAPSHOT.jar" ]; then
    echo "ERROR: Failed to build JAR file"
    exit 1
fi

# Start the application with error handling
echo "Starting Java application..."
java -jar target/quiz-app-1.0-SNAPSHOT.jar
