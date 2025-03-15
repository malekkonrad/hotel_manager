package pl.project.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomAvailableTest{

    @Test
    void testRoomAvailableExceptionMessage() {
        // Arrange
        int roomNumber = 303;
        RoomAvailable exception = new RoomAvailable(roomNumber);

        // Act
        String message = exception.getMessage();

        // Assert
        String expectedMessage = "Room number: 303 is available - nobody checked into it, try again with correct room number. (To see checked in rooms use list command) ";
        assertEquals(expectedMessage, message);
    }
}