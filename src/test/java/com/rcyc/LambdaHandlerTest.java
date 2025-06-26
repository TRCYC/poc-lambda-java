package com.rcyc;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaHandlerTest {

    @Test
    public void testHandleRequest() {
        // Arrange
        LambdaHandler handler = new LambdaHandler();
        Context context = null; // Mock or create a context if needed

        // Act
        String response = handler.handleRequest("", context);

        // Assert
        assertEquals("Completed", response);
    }
}