package pl.project.commands;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.project.commands.CheckInCommand;
import pl.project.exceptions.RoomNotAvailable;
import pl.project.exceptions.RoomNotExist;
import pl.project.exceptions.WrongInput;
import pl.project.hotel.Hotel;
import pl.project.hotel.Room;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CheckInCommandTest {


    private CheckInCommand checkInCommand;
    private Hotel hotel;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream)); // Przechwytywanie System.out

        // Dodajemy przykładowe pokoje
        hotel.addRoom(new Room(101, 200, "Room 101", 2, null, null, null)); // Dostępny pokój
        hotel.addRoom(new Room(102, 300, "Room 102", 2, "Konrad", LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 5))); // Zajęty pokój
    }

    @Test
    void testExecute_SuccessfulCheckIn() throws RoomNotExist, RoomNotAvailable, WrongInput {
        // Przygotowujemy dane wejściowe
        System.setIn(new ByteArrayInputStream("101\nJohn Doe\n2023-11-01\n3\n".getBytes()));

        // Tworzymy CheckInCommand z jednym Scannerem
        checkInCommand = new CheckInCommand();

        // Wykonanie testu
        assertDoesNotThrow(() -> checkInCommand.execute(hotel));

        // Sprawdzanie wyników
        Room room = hotel.getRoom(101);
        assertNotNull(room.getVisitorName());
        assertEquals("John Doe", room.getVisitorName());
        assertEquals(LocalDate.parse("2023-11-01"), room.getDateOfCheckIn());
//        assertEquals(3, room.getLengthOfStay()); // Sprawdzenie długości pobytu
        assertTrue(outputStream.toString().contains("Your reservation is completed!"));
    }

    @Test
    void testExecute_RoomNotAvailableException() {
        // Ustawiamy dane wejściowe dla zajętego pokoju
        System.setIn(new ByteArrayInputStream("102\n".getBytes()));

        // Tworzymy CheckInCommand z jednym Scannerem
        checkInCommand = new CheckInCommand();

        // Act & Assert
        RoomNotAvailable exception = assertThrows(RoomNotAvailable.class, () -> checkInCommand.execute(hotel));
        assertEquals("Room number: 102 is not available, try again with correct room number. (To see available rooms use list command) ", exception.getMessage());
    }

    @Test
    void testExecute_RoomNotExistException() {
        // Ustawiamy dane wejściowe dla pokoju, który nie istnieje
        System.setIn(new ByteArrayInputStream("999\n".getBytes()));

        // Tworzymy CheckInCommand z jednym Scannerem
        checkInCommand = new CheckInCommand();

        // Act & Assert
        RoomNotExist exception = assertThrows(RoomNotExist.class, () -> checkInCommand.execute(hotel));
        assertEquals("Room number: 999 does not exist, try again with correct room number. (To see available rooms use list command) ", exception.getMessage());
    }

    @Test
    void testGetRoom_InvalidInput() {
        // Ustawiamy niepoprawne dane wejściowe (tekst zamiast liczby)
        System.setIn(new ByteArrayInputStream("abc\n".getBytes()));

        // Tworzymy CheckInCommand z jednym Scannerem
        checkInCommand = new CheckInCommand();

        // Act & Assert
        WrongInput exception = assertThrows(WrongInput.class, () -> checkInCommand.getRoom(hotel));
        assertEquals("Invalid input: Expected a number.", exception.getMessage());
    }

    @Test
    void testGetGuestName_EmptyInput() {
        // Ustawiamy puste dane wejściowe
        System.setIn(new ByteArrayInputStream("\n".getBytes()));

        // Tworzymy CheckInCommand z jednym Scannerem
        checkInCommand = new CheckInCommand();

        // Act & Assert
        WrongInput exception = assertThrows(WrongInput.class, () -> checkInCommand.getGuestName());
        assertEquals("Invalid input: Please enter guest name.", exception.getMessage());
    }

    @Test
    void testGetDateCheckIn_InvalidDateFormat() {
        // Ustawiamy niepoprawny format daty
        System.setIn(new ByteArrayInputStream("2023/12/01\n".getBytes()));

        // Tworzymy CheckInCommand z jednym Scannerem
        checkInCommand = new CheckInCommand();

        // Act & Assert
        WrongInput exception = assertThrows(WrongInput.class, () -> checkInCommand.getDateCheckIn());
        assertEquals("Invalid input: Please enter date in correct format.", exception.getMessage());
    }

    @Test
    void testGetLengthCheckIn_InvalidInput() {
        // Ustawiamy niepoprawne dane wejściowe dla długości pobytu
        System.setIn(new ByteArrayInputStream("abc\n".getBytes()));

        // Tworzymy CheckInCommand z jednym Scannerem
        checkInCommand = new CheckInCommand();

        // Act & Assert
        WrongInput exception = assertThrows(WrongInput.class, () -> checkInCommand.getLengthCheckIn());
        assertEquals("Invalid input: Expected a number.", exception.getMessage());
    }

    @Test
    void testGetLengthCheckIn_ValidInput() {
        // Ustawiamy poprawne dane wejściowe dla długości pobytu
        System.setIn(new ByteArrayInputStream("5\n".getBytes()));

        // Tworzymy CheckInCommand z jednym Scannerem
        checkInCommand = new CheckInCommand();

        // Act
        int lengthOfStay = assertDoesNotThrow(() -> checkInCommand.getLengthCheckIn());

        // Assert
        assertEquals(5, lengthOfStay);
    }




}