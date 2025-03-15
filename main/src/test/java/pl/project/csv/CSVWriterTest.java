package pl.project.csv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.project.hotel.Hotel;
import pl.project.hotel.Room;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CSVWriterTest {

    @Mock
    private Hotel hotel;

    @Mock
    private Room room1;

    @Mock
    private Room room2;

    private final String[] expectedHeaders = {"ROOM_NUMBER", "PRICE", "DESCRIPTION", "CAPACITY", "GUEST", "DATE_OF_CHECK_IN", "DATE_OF_CHECK_OUT"};

    private static final String FILE_PATH = "test_output.csv";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testWriteCSVFile() throws IOException {
        // Arrange
        List<Integer> roomNumbers = Arrays.asList(101, 102);
        when(hotel.list()).thenReturn(roomNumbers);

        // Mock room details
        when(hotel.getRoom(101)).thenReturn(room1);
        when(room1.getNumber()).thenReturn(101);
        when(room1.getPricePerDay()).thenReturn(200.0);
        when(room1.getDescription()).thenReturn("Single room");
        when(room1.getCapacity()).thenReturn(2);
        when(room1.getVisitorName()).thenReturn("John Doe");
        when(room1.getDateOfCheckIn()).thenReturn(LocalDate.parse("2024-01-01"));
        when(room1.getDateOfCheckOut()).thenReturn(LocalDate.parse("2024-01-10"));

        when(hotel.getRoom(102)).thenReturn(room2);
        when(room2.getNumber()).thenReturn(102);
        when(room2.getPricePerDay()).thenReturn(300.0);
        when(room2.getDescription()).thenReturn("Double room");
        when(room2.getCapacity()).thenReturn(4);
        when(room2.getVisitorName()).thenReturn("Jane Doe");
        when(room2.getDateOfCheckIn()).thenReturn(LocalDate.parse("2024-02-01"));
        when(room2.getDateOfCheckOut()).thenReturn(LocalDate.parse("2024-02-10"));

        // Act
        CSVWriter.write(hotel, FILE_PATH);

        // Assert
        // Verify if the file was written successfully
        File file = new File(FILE_PATH);
        assertTrue(file.exists(), "CSV file should be created");

        // Verify file content
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            // Check headers
            assertArrayEquals(expectedHeaders, line.split(","));

            // Check room 1 data
            line = br.readLine();
            String[] values = line.split(",");
            assertEquals("101", values[0]);
            assertEquals("200.0", values[1]);
            assertEquals("Single room", values[2]);
            assertEquals("2", values[3]);
            assertEquals("John Doe", values[4]);
            assertEquals("2024-01-01", values[5]);
            assertEquals("2024-01-10", values[6]);

            // Check room 2 data
            line = br.readLine();
            values = line.split(",");
            assertEquals("102", values[0]);
            assertEquals("300.0", values[1]);
            assertEquals("Double room", values[2]);
            assertEquals("4", values[3]);
            assertEquals("Jane Doe", values[4]);
            assertEquals("2024-02-01", values[5]);
            assertEquals("2024-02-10", values[6]);
        }
    }

    @Test
    void testWriteEmptyHotel() throws IOException {
        // Arrange
        when(hotel.list()).thenReturn(Arrays.asList());

        // Act
        CSVWriter.write(hotel, FILE_PATH);

        // Assert
        File file = new File(FILE_PATH);
        assertTrue(file.exists(), "CSV file should be created");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // The file should only have headers
            String line = br.readLine();
            assertArrayEquals(expectedHeaders, line.split(","));
            assertNull(br.readLine(), "The file should be empty after the header");
        }
    }


}
