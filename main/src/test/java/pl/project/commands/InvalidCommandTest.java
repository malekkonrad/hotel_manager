package pl.project.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.project.commands.InvalidCommand;
import pl.project.hotel.Hotel;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class InvalidCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testExecute() {
        // Mock hotel object
        Hotel hotelMock = mock(Hotel.class);

        // Execute the command
        InvalidCommand invalidCommand = new InvalidCommand();
        invalidCommand.execute(hotelMock);

        // Verify the output
        assertEquals("Invalid command\r\n", outContent.toString());
    }

}