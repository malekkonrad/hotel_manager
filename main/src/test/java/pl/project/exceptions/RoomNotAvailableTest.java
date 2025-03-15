package pl.project.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomNotAvailableTest {

    @Test
    void testRoomNotAvailableExceptionMessage() {
        // Arrange
        int roomNumber = 202;
        RoomNotAvailable exception = new RoomNotAvailable(roomNumber);

        // Act
        String message = exception.getMessage();

        // Assert
        String expectedMessage = "Room number: 202 is not available, try again with correct room number. (To see available rooms use list command) ";
        assertEquals(expectedMessage, message);
    }
}