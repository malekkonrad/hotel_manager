package pl.project.hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.project.util.Map;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
    }

    @Test
    void testAddRoom() {
        Room room = new Room(101, 100, "Standard Room", 2, null, null, null);
        hotel.addRoom(room);
        assertEquals(room, hotel.getRoom(101));
    }

    @Test
    void testPrices() {
        Room room1 = new Room(101, 100, "Standard Room", 2, null, null, null);
        Room room2 = new Room(102, 150, "Standard Room", 2, null, null, null);
        hotel.addRoom(room1);
        hotel.addRoom(room2);

        Map<Integer, Double> prices = hotel.prices();
        assertEquals(100.0, prices.get(101));
        assertEquals(150.0, prices.get(102));
        assertEquals(2, prices.size());
    }

    @Test
    void testGetRoomRoom() {
        Room room = new Room(103, 200, "Standard Room", 2, null, null, null);
        hotel.addRoom(room);

        Room retrievedRoom = hotel.getRoom(103);
        assertNotNull(retrievedRoom);
        assertEquals(200.0, retrievedRoom.getPricePerDay());
    }

    @Test
    void testGetRoomNonExistentRoom() {
        assertNull(hotel.getRoom(999)); // Room does not exist
    }

    @Test
    void testListRoomNumbers() {
        Room room1 = new Room(101, 100, "Standard Room", 2, null, null, null);
        Room room2 = new Room(102, 150, "Standard Room", 2, null, null, null);
        hotel.addRoom(room1);
        hotel.addRoom(room2);

        List<Integer> roomNumbers = hotel.list();
        assertTrue(roomNumbers.contains(101));
        assertTrue(roomNumbers.contains(102));
        assertEquals(2, roomNumbers.size());
    }
}