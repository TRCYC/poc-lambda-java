FROM public.ecr.aws/lambda/java:21
  
# Copy function code and runtime dependencies from Gradle layout
COPY build/classes/java/main ${LAMBDA_TASK_ROOT}

# Copy dependencies
COPY build/dependencies/* ${LAMBDA_TASK_ROOT}/lib/

# Set the CMD to your handler (could also be done as a parameter override outside of the Dockerfile)
CMD [ "com.rcyc.LambdaHandler::handleRequest" ]