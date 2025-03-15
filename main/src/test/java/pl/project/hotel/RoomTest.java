package pl.project.hotel;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    private Room room;

    @BeforeEach
    public void setUp() {
        room = new Room(101, 100, "Standard Room", 2, null, null, null);
    }

    @Test
    public void testRoomInitialization() {
        assertEquals(101, room.getNumber());
        assertEquals(100.0, room.getPricePerDay());
        assertEquals("Standard Room", room.getDescription());
        assertEquals(2, room.getCapacity());
        assertTrue(room.isAvailable());
        assertNull(room.getVisitorName());
        assertNull(room.getDateOfCheckIn());
        assertNull(room.getDateOfCheckOut());
    }

    @Test
    public void testCheckIn() {
        LocalDate checkInDate = LocalDate.of(2024, 11, 1);
        room.checkIn("John Doe", checkInDate, 5);

        assertFalse(room.isAvailable());
        assertEquals("John Doe", room.getVisitorName());
        assertEquals(checkInDate, room.getDateOfCheckIn());
        assertEquals(checkInDate.plusDays(5), room.getDateOfCheckOut());
    }


    @Test
    public void testCheckOutWhenTodayAfterDateOfCheckIn() {
        room.checkIn("John Doe", LocalDate.now().minusDays(5), 5);  // check-in 5 dni temu na 5 dni
        double cost = room.checkOut();
        assertEquals(500, cost);  // koszt to 5 dni * 100 za dzień
        assertRoomIsAvailable();
    }

    @Test
    public void testCheckOutWhenTodayBeforeDateOfCheckIn() {
        room.checkIn("Jane Doe", LocalDate.now().plusDays(5), 5);  // check-in w przyszłości
        double cost = room.checkOut();
        assertEquals(0, cost);  // koszt powinien wynosić 0, ponieważ check-in jeszcze się nie odbył
        assertRoomIsAvailable();
    }

    @Test
    public void testCheckOutWhenTodayEqualsDateOfCheckIn() {
        room.checkIn("Alice Smith", LocalDate.now(), 2);  // check-in dzisiaj
        double cost = room.checkOut();
        assertEquals(0, cost);  // koszt powinien wynosić 0, ponieważ check-in odbył się dzisiaj
        assertRoomIsAvailable();
    }

    private void assertRoomIsAvailable() {
        assertTrue(room.isAvailable());
        assertNull(room.getDateOfCheckIn());
        assertNull(room.getDateOfCheckOut());
        assertNull(room.getVisitorName());
    }



}