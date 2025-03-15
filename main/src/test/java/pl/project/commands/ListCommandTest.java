package pl.project.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.project.hotel.Hotel;
import pl.project.hotel.Room;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ListCommandTest {

    private ListCommand listCommand;
    private Hotel hotel;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        listCommand = new ListCommand();
        hotel = new Hotel();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream)); // Przechwytywanie System.out

        // Dodawanie przykładowych pokoi do hotelu
        hotel.addRoom(new Room(101, 100, "Standard Room", 2, null, null, null)); // Dostępny pokój
        hotel.addRoom(new Room(102, 300, "Room 102", 3, "Konrad", LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 5))); // Zajęty pokój

    }

    @Test
    void testExecute_DisplayRoomListWithData() {
        // Act: Wywołujemy metodę execute
        listCommand.execute(hotel);

        // Assert: Sprawdzamy, czy metoda wypisała poprawne dane na konsolę
        String output = outputStream.toString();
        assertTrue(output.contains("101"));
        assertTrue(output.contains("Available"));
        assertTrue(output.contains("102"));
        assertTrue(output.contains("Not available"));
        assertTrue(output.contains("2023-11-01"));
        assertTrue(output.contains("2023-11-05"));
    }

    @Test
    void testParseAvailable_RoomAvailable() {
        // Act & Assert
        String status = listCommand.parseAvailable(hotel.getRoom(101));
        assertEquals("  Available  ", status);
    }

    @Test
    void testParseAvailable_RoomNotAvailable() {
        // Act & Assert
        String status = listCommand.parseAvailable(hotel.getRoom(102));
        assertEquals("Not available", status);
    }

    @Test
    void testGetStringBuilder_VisitorNameShorterThan12Characters() {

        Room newRoom =new Room(103, 300, "Room 102", 3, "John Doe", LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 5));
        hotel.addRoom(newRoom);
        // Act
        StringBuilder guestData = listCommand.getStringBuilder(hotel.getRoom(103));

        // Assert
        assertEquals("John Doe    ", guestData.toString()); // Uzupełnione spacjami do 12 znaków
    }

    @Test
    void testGetStringBuilder_VisitorNameLongerThan12Characters() {

        Room newRoom =new Room(104, 300, "Room 102", 3, "VeryLongVisitorName", LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 5));
        hotel.addRoom(newRoom);

        // Act
        StringBuilder guestData = listCommand.getStringBuilder(hotel.getRoom(104));

        // Assert
        assertEquals("VeryLongVisi", guestData.toString()); // Przycięte do 12 znaków
    }

    @Test
    void testParseDateOfCheckIn_DateIsNull_ReturnsHyphen() {
        // Act
        String date = listCommand.parseDateOfCheckIn(hotel.getRoom(101));

        // Assert
        assertEquals("        -       ", date);
    }

    @Test
    void testParseDateOfCheckIn_DateIsNotNull_ReturnsFormattedDate() {
        // Act
        String date = listCommand.parseDateOfCheckIn(hotel.getRoom(102));

        // Assert
        assertEquals("   2023-11-01   ", date);
    }

    @Test
    void testParseDateOfCheckOut_DateIsNull_ReturnsHyphen() {
        // Act
        String date = listCommand.parseDateOfCheckOut(hotel.getRoom(101));

        // Assert
        assertEquals("        -        ", date);
    }

    @Test
    void testParseDateOfCheckOut_DateIsNotNull_ReturnsFormattedDate() {
        // Act
        String date = listCommand.parseDateOfCheckOut(hotel.getRoom(102));

        // Assert
        assertEquals("    2023-11-05   ", date);
    }

    @Test
    void testDisplayHeader_PrintsCorrectHeaderFormat() {
        // Act
        listCommand.displayHeader();

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("LIST OF ROOMS"));
        assertTrue(output.contains("| ROOM NUMBER |    STATUS     |  GUEST DATA  | DATE OF CHECK IN | DATE OF CHECK OUT |"));
    }


}