package pl.project.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.project.csv.CSVWriter;
import pl.project.hotel.Hotel;
import pl.project.hotel.Room;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;

class SaveCommandTest {


    private SaveCommand saveCommand;
    private Hotel hotel;
    private ByteArrayOutputStream outputStream;


    @BeforeEach
    void setUp() {
        saveCommand = new SaveCommand();
        hotel = new Hotel();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream)); // Przechwytywanie System.out

        // Dodajemy przykładowe pokoje z cenami
        hotel.addRoom(new Room(101, 200, "Standard Room", 2, null, null, null));
        hotel.addRoom(new Room(102, 300, "Standard Room", 2, null, null, null));
    }


    @Test
    void testSuccesful() throws IOException {

        saveCommand.execute(hotel);

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Current hotel data has been successfully saved into file!"));
    }

}