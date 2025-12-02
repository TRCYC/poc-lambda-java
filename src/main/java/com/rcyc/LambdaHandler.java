package com.rcyc;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LambdaHandler implements RequestHandler<Object, String> {

    private static final Logger logger = LoggerFactory.getLogger(LambdaHandler.class);

    @Override
    public String handleRequest(Object input, Context context) {
        // Log a simple message
        logger.info("Lambda function invoked with DEFAULT LOGLEVEL - " + Env.get("LOGLEVEL") + " and request: ", context);

        logger.debug("Lambda function completed Successfully");


        // Return a basic response
        return "Completed";
    }
}
