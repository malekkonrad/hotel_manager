package pl.project.csv;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.project.hotel.Hotel;
import pl.project.hotel.Room;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CSVReaderTest{

    private static Hotel hotel;

    @BeforeAll
    static void setUp() throws IOException {
        // Reads the data from the provided CSV file and creates a Hotel object
        hotel = CSVReader.read("src/main/resources/Hotel.csv");
    }

    @Test
    void testHotelCreation() {
        // Check that the Hotel object is created with the correct number of rooms
        assertNotNull(hotel);
        assertEquals(7, hotel.list().size(), "Hotel should contain 7 rooms based on the CSV data.");
    }

    @Test
    void testRoom101Data() {
        Room room101 = hotel.getRoom(101);
        assertNotNull(room101);
        assertEquals(300.0, room101.getPricePerDay());
        assertEquals("short description", room101.getDescription());
        assertEquals(5, room101.getCapacity());
        assertEquals("Konrad Malek", room101.getVisitorName());
        assertEquals(LocalDate.of(2024, 10, 26), room101.getDateOfCheckIn());
        assertEquals(LocalDate.of(2024, 10, 30), room101.getDateOfCheckOut());
    }

    @Test
    void testRoom102Data() {
        Room room102 = hotel.getRoom(102);
        assertNotNull(room102);
        assertEquals(200.0, room102.getPricePerDay());
        assertEquals("short description2", room102.getDescription());
        assertEquals(2, room102.getCapacity());
        assertEquals("Jan Malek", room102.getVisitorName());
        assertEquals(LocalDate.of(2024, 10, 22), room102.getDateOfCheckIn());
        assertEquals(LocalDate.of(2024, 10, 23), room102.getDateOfCheckOut());
    }

    @Test
    void testRoom103NullGuestAndDates() {
        Room room103 = hotel.getRoom(103);
        assertNotNull(room103);
        assertEquals(200.0, room103.getPricePerDay());
        assertEquals("medium", room103.getDescription());
        assertEquals(3, room103.getCapacity());
        assertNull(room103.getVisitorName(), "Guest should be null for room 103.");
        assertNull(room103.getDateOfCheckIn(), "DateOfCheckIn should be null for room 103.");
        assertNull(room103.getDateOfCheckOut(), "DateOfCheckOut should be null for room 103.");
    }

    @Test
    void testRoom104NullGuestAndDates() {
        Room room104 = hotel.getRoom(104);
        assertNotNull(room104);
        assertEquals(100.0, room104.getPricePerDay());
        assertEquals("small", room104.getDescription());
        assertEquals(1, room104.getCapacity());
        assertNull(room104.getVisitorName(), "Guest should be null for room 104.");
        assertNull(room104.getDateOfCheckIn(), "DateOfCheckIn should be null for room 104.");
        assertNull(room104.getDateOfCheckOut(), "DateOfCheckOut should be null for room 104.");
    }

    @Test
    void testRoom201Data() {
        Room room201 = hotel.getRoom(201);
        assertNotNull(room201);
        assertEquals(400.0, room201.getPricePerDay());
        assertEquals("apartment", room201.getDescription());
        assertEquals(2, room201.getCapacity());
        assertNull(room201.getVisitorName(), "Guest should be null for room 201.");
        assertNull(room201.getDateOfCheckIn(), "DateOfCheckIn should be null for room 201.");
        assertNull(room201.getDateOfCheckOut(), "DateOfCheckOut should be null for room 201.");
    }

    @Test
    void testRoom202Data() {
        Room room202 = hotel.getRoom(202);
        assertNotNull(room202);
        assertEquals(400.0, room202.getPricePerDay());
        assertEquals("apartment", room202.getDescription());
        assertEquals(2, room202.getCapacity());
        assertNull(room202.getVisitorName(), "Guest should be null for room 202.");
        assertNull(room202.getDateOfCheckIn(), "DateOfCheckIn should be null for room 202.");
        assertNull(room202.getDateOfCheckOut(), "DateOfCheckOut should be null for room 202.");
    }

    @Test
    void testRoom203Data() {
        Room room203 = hotel.getRoom(203);
        assertNotNull(room203);
        assertEquals(400.0, room203.getPricePerDay());
        assertEquals("apartament", room203.getDescription());
        assertEquals(2, room203.getCapacity());
        assertNull(room203.getVisitorName(), "Guest should be null for room 203.");
        assertNull(room203.getDateOfCheckIn(), "DateOfCheckIn should be null for room 203.");
        assertNull(room203.getDateOfCheckOut(), "DateOfCheckOut should be null for room 203.");
    }

    @Test
    void testFileNotFoundOutput() {
        // Provide a non-existent file path
        String invalidFilePath = "src/main/resources/non_existent_file.csv";

        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Save the original System.out
        System.setOut(new PrintStream(outputStream)); // Redirect System.out

        try {
            CSVReader.read(invalidFilePath); // Call the method
        } catch (IOException e) {
            // This is expected due to the file not being found, nothing to handle here
        }

        // Restore the original System.out
        System.setOut(originalOut);

        // Convert output to a string for assertion
        String output = outputStream.toString();

        // Check if the expected error message is in the output
        assertTrue(output.contains("Nie można odnaleźć określonego pliku"), "Output should contain 'File not found'");
    }
}