FROM gradle:8.8.0-jdk21-alpine AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle files and the source code to the container
COPY build.gradle settings.gradle ./
COPY src ./src

# Build the application
RUN gradle build -x test

# Use the official OpenJDK 21 image to run the application
FROM openjdk:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port the application will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
