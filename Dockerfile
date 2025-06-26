FROM public.ecr.aws/lambda/java:21

# Set the working directory to the Lambda task root
WORKDIR ${LAMBDA_TASK_ROOT}

# Copy the compiled Java application JAR
COPY build/libs/app.jar ${LAMBDA_TASK_ROOT}/

# Set the Lambda handler
CMD [ "com.rcyc.LambdaHandler::handleRequest" ]