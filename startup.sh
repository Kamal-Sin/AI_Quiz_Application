#!/bin/bash

echo "=========================================="
echo "Starting Quiz App Backend..."
echo "=========================================="

# Check if MongoDB URI is set
if [ -z "$MONGODB_URI" ]; then
    echo "WARNING: MONGODB_URI environment variable is not set!"
    echo "Application may fail to start properly."
    echo "Please set MONGODB_URI in your Railway environment variables."
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

# Start the application
echo "Starting Java application..."
java -jar target/quiz-app-1.0-SNAPSHOT.jar
