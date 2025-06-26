# Use Amazon Corretto as the base image for Java applications
FROM amazoncorretto:11

# Set the working directory
WORKDIR /var/task

# Copy your application JAR file into the container
COPY build/libs/app.jar app.jar

# Set the entry point for the Lambda function
ENTRYPOINT ["java", "-cp", "app.jar", "com.rcyc.LambdaHandler"]