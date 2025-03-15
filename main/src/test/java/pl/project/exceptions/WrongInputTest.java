package pl.project.exceptions;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WrongInputTest{

    @Test
    void testWrongInputExceptionMessage() {
        // Arrange
        String input = "invalidInput";
        WrongInput exception = new WrongInput(input);

        // Act
        String message = exception.getMessage();

        // Assert
        String expectedMessage = "Invalid input: invalidInput";
        assertEquals(expectedMessage, message);
    }

}