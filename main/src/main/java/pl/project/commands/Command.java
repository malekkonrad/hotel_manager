package pl.project.commands;

import pl.project.exceptions.RoomAvailable;
import pl.project.exceptions.RoomNotAvailable;
import pl.project.exceptions.RoomNotExist;
import pl.project.exceptions.WrongInput;
import pl.project.hotel.Hotel;
import pl.project.hotel.Room;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The {@code Command} class is an abstract base class for all command classes in the system.
 * It defines the structure for executing commands related to hotel management,
 * such as checking in, checking out, viewing rooms, and more.
 * Specific commands extend this class and implement the {@code execute} method.
 */
public abstract class Command {

    /**
     * Executes the specific command logic.
     * Each subclass must provide its own implementation of this method.
     *
     * @param hotel the hotel object on which the command will be executed.
     * @throws RoomNotExist    if the room does not exist.
     * @throws RoomNotAvailable if the room is not available for check-in or booking.
     * @throws WrongInput      if the input provided by the user is invalid.
     * @throws RoomAvailable   if the room is already available (e.g., during check-out).
     */
    public abstract void execute(Hotel hotel) throws RoomNotExist, RoomNotAvailable, WrongInput, RoomAvailable;

    /**
     * Prompts the user to enter a room number and fetches the corresponding room from the hotel.
     *
     * @param hotel   the hotel object containing rooms.
     * @param scanner the scanner used to capture user input.
     * @return the room object corresponding to the entered room number.
     * @throws RoomNotExist if the room does not exist.
     * @throws WrongInput   if the user input is invalid (e.g., not a number).
     */
    public Room getRoomUpper(Hotel hotel, Scanner scanner) throws RoomNotExist, WrongInput {
        int number = 0;

        try {
            number = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            throw new WrongInput("Expected a number.");
        }

        Room viewRoom = hotel.getRoom(number);
        if (viewRoom == null) {
            throw new RoomNotExist(number); // Jeśli pokój nie istnieje
        }
        return viewRoom;
    }
}