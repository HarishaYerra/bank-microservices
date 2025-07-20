# Use official OpenJDK base image
FROM openjdk:17-jdk-alpine

# Set working directory inside the container
WORKDIR /app

# Copy the built jar file
COPY target/account-service-0.0.1-SNAPSHOT.jar app.jar

# Command to run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
