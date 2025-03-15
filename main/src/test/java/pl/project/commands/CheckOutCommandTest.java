package pl.project.commands;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.project.commands.CheckOutCommand;
import pl.project.exceptions.RoomAvailable;
import pl.project.exceptions.RoomNotExist;
import pl.project.exceptions.WrongInput;
import pl.project.hotel.Hotel;
import pl.project.hotel.Room;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CheckOutCommandTest {


    private CheckOutCommand checkOutCommand;
    private Hotel hotel;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        checkOutCommand = new CheckOutCommand();
        hotel = new Hotel();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream)); // Przechwytywanie System.out

        // Dodajemy przykładowe pokoje
        Room availableRoom = new Room(101, 100, "Room 102", 2, null, null, null); // Pokój dostępny
        Room occupiedRoom = new Room(102, 300, "Room 102", 2, "Konrad", LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 5)); // Pokój zajęty

        occupiedRoom.checkIn("John Doe", LocalDate.now().minusDays(5), 5); // Ustawienie gościa i czasu pobytu

        hotel.addRoom(availableRoom);
        hotel.addRoom(occupiedRoom);
    }

    @Test
    void testExecute_SuccessfulCheckOut() {
        // Ustawiamy dane wejściowe do metody execute
        System.setIn(new ByteArrayInputStream("102\n".getBytes()));

        // Act
        assertDoesNotThrow(() -> checkOutCommand.execute(hotel));

        // Assert
        Room room = hotel.getRoom(102);
        assertTrue(room.isAvailable()); // Sprawdzamy, czy pokój jest teraz dostępny
        assertTrue(outputStream.toString().contains("John Doe has been checked out"));
        assertTrue(outputStream.toString().contains("Cost of stay:"));
    }

    @Test
    void testExecute_RoomAvailableException() {
        // Ustawiamy dane wejściowe dla pokoju, który jest dostępny
        System.setIn(new ByteArrayInputStream("101\n".getBytes()));

        // Act & Assert
        RoomAvailable exception = assertThrows(RoomAvailable.class, () -> checkOutCommand.execute(hotel));
        assertEquals("Room number: 101 is available - nobody checked into it, try again with correct room number. (To see checked in rooms use list command) ", exception.getMessage());
    }

    @Test
    void testExecute_RoomNotExistException() {
        // Ustawiamy dane wejściowe dla pokoju, który nie istnieje
        System.setIn(new ByteArrayInputStream("999\n".getBytes()));

        // Act & Assert
        RoomNotExist exception = assertThrows(RoomNotExist.class, () -> checkOutCommand.execute(hotel));
        assertEquals("Room number: 999 does not exist, try again with correct room number. (To see available rooms use list command) ", exception.getMessage());
    }

    @Test
    void testGetRoom_InvalidInput() {
        // Ustawiamy niepoprawne dane wejściowe (tekst zamiast liczby)
        System.setIn(new ByteArrayInputStream("abc\n".getBytes()));

        // Act & Assert
        WrongInput exception = assertThrows(WrongInput.class, () -> checkOutCommand.getRoom(hotel));
        assertEquals("Invalid input: Expected a number.", exception.getMessage());
    }






}