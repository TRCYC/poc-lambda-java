# Stage 1: Build the application
FROM gradle:7.2-jdk11 AS build

# Set the working directory
WORKDIR /app

# Copy the entire project into the Docker image
COPY . .

# Build the application
RUN ./gradlew clean build

# Stage 2: Create the final image
FROM amazoncorretto:11

# Set the working directory
WORKDIR /var/task

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/app.jar app.jar

# Set the entry point for the Lambda function
ENTRYPOINT ["java", "-cp", "app.jar", "com.rcyc.LambdaHandler"]