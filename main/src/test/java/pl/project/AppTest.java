package pl.project;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import pl.project.commands.*;
import pl.project.exceptions.RoomNotExist;
import pl.project.hotel.Hotel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Mock
    private Hotel hotelMock;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testGreetings() {
        App.greetings();
        String expectedOutput = """
                ------------- HOTEL -------------\r\nWelcome to hotel manager service\r\nType in command to start (use help to see available commands)\n\r\n""";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testGoodbye() {
        App.goodbye();
        String expectedOutput = "------------- HOTEL -------------\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testCommandParser() {
        assertEquals("help", App.commandParser("Help "));
        assertEquals("view", App.commandParser(" VIEW"));
        assertEquals("save", App.commandParser(" SaVe "));
        assertEquals("exit", App.commandParser("exit"));
    }

    @Test
    void testExecuteCommandsWithSimulatedInput() {
        // Symulacja wejścia użytkownika: komendy "help" i "exit"
        String simulatedInput = "help\nexit\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8)));

        App.executeCommands(hotelMock);

        String output = outContent.toString();

        // Sprawdzamy, czy w output pojawił się tekst z metody help
        assertTrue(output.contains("Available commands:")); // Zakładamy, że HelpCommand wypisuje takie dane
        assertTrue(output.contains("HOTEL")); // Potwierdzamy zakończenie
    }

    // Metoda pomocnicza do symulowania wejścia użytkownika
    private void simulateUserInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void testInvalidCommandExecution() {
        simulateUserInput("unknown\nexit\n");


        // Uruchomienie App.executeCommands() z zamockowaną komendą
        App.executeCommands(hotelMock);

        String output = outContent.toString();
        assertTrue(output.contains("Invalid command"));
    }


}
