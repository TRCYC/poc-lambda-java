package com.rcyc;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaHandlerTest {

    @Test
    public void testHandleRequest() {
        // Arrange
        LambdaHandler handler = new LambdaHandler();
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        Context context = null; // Mock or create a context if needed

        // Act
        APIGatewayProxyResponseEvent response = handler.handleRequest(request, context);

        // Assert
        assertEquals(200, response.getStatusCode());
        assertEquals("{\"message\": \"Lambda function executed successfully\"}", response.getBody());
    }
}