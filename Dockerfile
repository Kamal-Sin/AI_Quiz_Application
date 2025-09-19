# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first (for better caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Copy source code
COPY src-java ./src-java

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port (Render uses PORT environment variable)
EXPOSE $PORT

# Run the application with production profile
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "target/quiz-app-1.0-SNAPSHOT.jar"]
