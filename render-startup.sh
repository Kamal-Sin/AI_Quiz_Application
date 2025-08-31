#!/bin/bash

echo "=========================================="
echo "Starting Quiz App Backend on Render..."
echo "=========================================="

# Set default values for environment variables
export SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE:-render}
export PORT=${PORT:-8080}
export MONGODB_DATABASE=${MONGODB_DATABASE:-quizapp}

echo "Environment Configuration:"
echo "  SPRING_PROFILES_ACTIVE: $SPRING_PROFILES_ACTIVE"
echo "  PORT: $PORT"
echo "  MONGODB_DATABASE: $MONGODB_DATABASE"

# Check if MongoDB URI is set
if [ -z "$MONGODB_URI" ]; then
    echo "ERROR: MONGODB_URI environment variable is not set!"
    echo "This is required for the application to start."
    echo "Please set MONGODB_URI in your Render environment variables."
    exit 1
else
    echo "  MONGODB_URI: ${MONGODB_URI:0:20}..." # Show first 20 chars for security
fi

# Check if JAR file exists, build if not
if [ ! -f "target/quiz-app-1.0-SNAPSHOT.jar" ]; then
    echo "JAR file not found. Building the application..."
    ./mvnw clean package -DskipTests
    
    if [ ! -f "target/quiz-app-1.0-SNAPSHOT.jar" ]; then
        echo "ERROR: Failed to build JAR file"
        exit 1
    fi
    echo "Build completed successfully!"
else
    echo "JAR file found at target/quiz-app-1.0-SNAPSHOT.jar"
fi

# Verify JAR file size
JAR_SIZE=$(stat -c%s "target/quiz-app-1.0-SNAPSHOT.jar" 2>/dev/null || stat -f%z "target/quiz-app-1.0-SNAPSHOT.jar" 2>/dev/null || echo "0")
echo "JAR file size: ${JAR_SIZE} bytes"

if [ "$JAR_SIZE" -lt 1000000 ]; then
    echo "WARNING: JAR file seems too small, rebuilding..."
    ./mvnw clean package -DskipTests
fi

# Start the application
echo "=========================================="
echo "Starting Java application..."
echo "=========================================="

# Set JVM options for Render
export JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseG1GC -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom"

# Start the application with Render profile
exec java $JAVA_OPTS -jar target/quiz-app-1.0-SNAPSHOT.jar --spring.profiles.active=render
