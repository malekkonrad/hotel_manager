package pl.project.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.project.exceptions.RoomNotExist;
import pl.project.exceptions.WrongInput;
import pl.project.hotel.Hotel;
import pl.project.hotel.Room;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ViewCommandTest {

    private Hotel hotel;
    private ViewCommand viewCommand;

    @BeforeEach
    void setUp() {
        hotel = mock(Hotel.class);  // Mockowanie hotelu
        viewCommand = new ViewCommand();  // Tworzenie instancji ViewCommand
    }

    @Test
    void testExecute_SuccessfulRoomDisplay() throws RoomNotExist, WrongInput {
        // Arrange: Przygotowanie danych do testu
        int roomNumber = 101;
        Room room = new Room(roomNumber, 100, "Standard Room", 2, null, null, null);
        when(hotel.getRoom(roomNumber)).thenReturn(room);

        // Symulacja wejścia użytkownika
        String input = roomNumber + "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act: Uruchamiamy metodę execute
        viewCommand.execute(hotel);

        // Assert: Sprawdzamy, czy metoda działa bez błędów i wyświetla informacje o pokoju
        // Ponieważ metoda wypisuje na standardowe wyjście, można tutaj użyć innego podejścia,
        // np. przekierowania System.out, ale na razie zakładamy, że test sprawdza, czy
        // nie wystąpiły żadne błędy.
    }

    @Test
    void testGetRoom_InvalidInput_ThrowsWrongInput() {
        // Arrange: Niepoprawne dane wejściowe (np. tekst zamiast liczby)
        String invalidInput = "abc\n";
        InputStream in = new ByteArrayInputStream(invalidInput.getBytes());
        System.setIn(in);

        // Act & Assert: Sprawdzamy, czy zostanie rzucony wyjątek WrongInput
        assertThrows(WrongInput.class, () -> {
            viewCommand.execute(hotel);
        });
    }

    @Test
    void testGetRoom_RoomNotExist_ThrowsRoomNotExist() {
        // Arrange: Pokój, który nie istnieje
        int nonExistentRoomNumber = 999;
        when(hotel.getRoom(nonExistentRoomNumber)).thenReturn(null);

        // Symulacja wejścia użytkownika
        String input = nonExistentRoomNumber + "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act & Assert: Sprawdzamy, czy zostanie rzucony wyjątek RoomNotExist
        assertThrows(RoomNotExist.class, () -> {
            viewCommand.execute(hotel);
        });
    }

    @Test
    void testGetRoom_SuccessfulRoomFound() throws RoomNotExist, WrongInput {
        // Arrange: Pokój, który istnieje
        int roomNumber = 101;
        Room room = new Room(roomNumber, 101, "Standard Room", 2, null, null, null);
        when(hotel.getRoom(roomNumber)).thenReturn(room);

        // Symulacja wejścia użytkownika
        String input = roomNumber + "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act: Uruchamiamy metodę execute
        viewCommand.execute(hotel);

        // Assert: Sprawdzamy, czy metoda działa poprawnie, tutaj raczej testujemy, czy
        // nie występują błędy, ponieważ metoda wypisuje wynik na konsolę.
    }

    @Test
    void testParseGuestName_GuestIsNull_ReturnsHyphen() {
        // Arrange: Pokój bez gościa
        Room room = new Room(101, 100, "Standard Room", 2, null, null, null);

        // Act: Wywołujemy metodę parseGuestName
        String guest = viewCommand.parseGuestName(room);

        // Assert: Sprawdzamy, czy metoda zwróciła "-"
        assertEquals("-", guest);
    }

    @Test
    void testParseGuestName_GuestIsNotNull() {
        // Arrange: Pokój bez gościa
        Room room = new Room(101, 100, "Standard Room", 2, "Konrad", null, null);

        // Act: Wywołujemy metodę parseGuestName
        String guest = viewCommand.parseGuestName(room);

        // Assert: Sprawdzamy, czy metoda zwróciła "-"
        assertEquals("Konrad", guest);
    }

    @Test
    void testParseDateOfCheckIn_DateIsNull_ReturnsHyphen() {
        // Arrange: Pokój bez daty zameldowania
        Room room = new Room(101, 100, "Standard Room", 2, null, null, null);

        // Act: Wywołujemy metodę parseDateOfCheckIn
        String date = viewCommand.parseDateOfCheckIn(room);

        // Assert: Sprawdzamy, czy metoda zwróciła "-"
        assertEquals("-", date);
    }


    @Test
    void testParseDateOfCheckIn_DateIsNotNull() {
        // Arrange: Pokój bez daty zameldowania
        Room room = new Room(101, 100, "Standard Room", 2, null, LocalDate.now(), null);

        // Act: Wywołujemy metodę parseDateOfCheckIn
        String date = viewCommand.parseDateOfCheckIn(room);

        String today = LocalDate.now().toString();
        // Assert: Sprawdzamy, czy metoda zwróciła "-"
        assertEquals(today, date);
    }




    @Test
    void testParseDateOfCheckOut_DateIsNull_ReturnsHyphen() {
        // Arrange: Pokój bez daty wymeldowania
        Room room = new Room(101, 100, "Standard Room", 2, null, null, null);

        // Act: Wywołujemy metodę parseDateOfCheckOut
        String date = viewCommand.parseDateOfCheckOut(room);

        // Assert: Sprawdzamy, czy metoda zwróciła "-"
        assertEquals("-", date);
    }


    @Test
    void testParseDateOfCheckOut_DateIsNotNull() {
        // Arrange: Pokój bez daty wymeldowania
        Room room = new Room(101, 100, "Standard Room", 2, null, null, LocalDate.now());

        // Act: Wywołujemy metodę parseDateOfCheckOut
        String date = viewCommand.parseDateOfCheckOut(room);

        String today = LocalDate.now().toString();
        // Assert: Sprawdzamy, czy metoda zwróciła "-"
        assertEquals(today, date);
    }



}