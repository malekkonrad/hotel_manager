package pl.project.exceptions;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomNotExistTest  {

    @Test
    void testRoomNotExistExceptionMessage() {
        // Arrange
        int roomNumber = 101;
        RoomNotExist exception = new RoomNotExist(roomNumber);

        // Act
        String message = exception.getMessage();

        // Assert
        String expectedMessage = "Room number: 101 does not exist, try again with correct room number. (To see available rooms use list command) ";
        assertEquals(expectedMessage, message);
    }
}