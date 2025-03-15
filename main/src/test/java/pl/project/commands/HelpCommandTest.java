package pl.project.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.project.hotel.Hotel;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class HelpCommandTest {


    private HelpCommand helpCommand;
    private Hotel hotel;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        hotel = new Hotel(); // Można utworzyć pusty hotel, ponieważ HelpCommand nie wymaga danych hotelu
        helpCommand = new HelpCommand();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream)); // Przechwytywanie System.out
    }

    @Test
    void testExecute() {
        // Wykonanie testu
        helpCommand.execute(hotel);

        // Sprawdzamy, czy wypisane komendy zawierają wszystkie oczekiwane komendy
        String output = outputStream.toString();
        assertTrue(output.contains(" -> prices"));
        assertTrue(output.contains(" -> view"));
        assertTrue(output.contains(" -> checkin"));
        assertTrue(output.contains(" -> checkout"));
        assertTrue(output.contains(" -> list"));
        assertTrue(output.contains(" -> save"));
        assertTrue(output.contains(" -> exit"));
    }
}