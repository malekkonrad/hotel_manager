package pl.project.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.project.hotel.Hotel;
import pl.project.hotel.Room;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PricesCommandTest {

    private PricesCommand pricesCommand;
    private Hotel hotel;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        pricesCommand = new PricesCommand();
        hotel = new Hotel();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream)); // Przechwytywanie System.out

        // Dodajemy przykładowe pokoje z cenami
        hotel.addRoom(new Room(101, 200, "Standard Room", 2, null, null, null));
        hotel.addRoom(new Room(102, 300, "Standard Room", 2, null, null, null));
    }

    @Test
    void testExecute_PrintsPricesForRooms() {
        // Act
        pricesCommand.execute(hotel);

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("| 101 | 200.0 |"));
        assertTrue(output.contains("| 102 | 300.0 |"));
        assertTrue(output.contains("|   PRICES    |"));
    }

    @Test
    void testExecute_PrintsHeaderCorrectly() {
        // Act
        pricesCommand.execute(hotel);

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains(" ------------- "));
        assertTrue(output.contains("| NUM | PRICE |"));
    }


}