# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Copy pom.xml first (for better caching)
COPY pom.xml .

# Copy source code
COPY src-java ./src-java

# Build the application using Maven directly
RUN mvn clean package -DskipTests

# Expose port (Render uses PORT environment variable)
EXPOSE $PORT

# Run the application with production profile
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "target/quiz-app-1.0-SNAPSHOT.jar"]
