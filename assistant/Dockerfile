# Use an official openjdk image to build the application
FROM openjdk:17-jdk-slim AS build

# Set the working directory
WORKDIR /app

# Copy the Maven build file and install dependencies
COPY pom.xml .
RUN apt-get update && apt-get install -y maven
RUN mvn clean install -DskipTests

# Copy the Spring Boot application JAR file from the Maven build
COPY target/*.jar app.jar

# Expose the port that your Spring Boot app will run on (usually 8080)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
