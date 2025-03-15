package pl.project.commands;

import org.junit.jupiter.api.Test;
import pl.project.exceptions.RoomNotExist;
import pl.project.hotel.Hotel;
import pl.project.hotel.Room;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void testGetRoomSuccess() throws RoomNotExist {
        // Arrange
        Hotel hotel = new Hotel();
        int roomNumber = 101;

        Room expectedRoom = new Room(roomNumber, 100, "Standard Room", 2, null, null, null);

        hotel.addRoom(expectedRoom); // Assuming addRoom is a method to add a room to the hotel

        String input = roomNumber + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        Room result = hotel.getRoom(roomNumber);

        // Assert
        assertEquals(expectedRoom, result);
    }

    @Test
    void testGetRoomRoomNotExistException() {
        // Arrange
        Hotel hotel = new Hotel();
        int roomNumber = 999; // Room that does not exist

        String input = roomNumber + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act & Assert
        assertNull(hotel.getRoom(roomNumber));
    }
}