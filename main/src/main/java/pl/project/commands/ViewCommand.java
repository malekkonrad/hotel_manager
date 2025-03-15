package pl.project.commands;

import pl.project.exceptions.RoomNotExist;
import pl.project.exceptions.WrongInput;
import pl.project.hotel.Hotel;
import pl.project.hotel.Room;

import java.util.Scanner;

/**
 * The {@code ViewCommand} class is responsible for displaying detailed information about a specific room in the hotel.
 * It fetches the room by its number and prints details such as room price, capacity, description, guest data,
 * and check-in/check-out dates.
 */
public class ViewCommand extends Command {

    /**
     * Executes the command to view details of a specific room in the hotel.
     * The details include room number, price, capacity, description, guest name,
     * check-in and check-out dates.
     *
     * @param hotel the hotel object from which the room data will be fetched.
     * @throws WrongInput   if the input data is invalid.
     * @throws RoomNotExist if the room with the specified number does not exist.
     */
    public void execute(Hotel hotel) throws WrongInput, RoomNotExist {

        Room viewRoom = getRoom(hotel);
        String guest = parseGuestName(viewRoom);
        String dateOfCheckIn = parseDateOfCheckIn(viewRoom);
        String dateOfCheckOut = parseDateOfCheckOut(viewRoom);

        System.out.println(" ----------------- ");
        System.out.println("| Number:         | " + viewRoom.getNumber());
        System.out.println("| Price per day:  | " + viewRoom.getPricePerDay());
        System.out.println("| Capacity:       | " + viewRoom.getCapacity());
        System.out.println("| Description:    | " + viewRoom.getDescription());
        System.out.println("| Guest:          | " + guest);
        System.out.println("| Check In Date:  | " + dateOfCheckIn);
        System.out.println("| Check Out Date: | " + dateOfCheckOut);
        System.out.println(" ----------------- ");
    }

    /**
     * Prompts the user to enter a room number and fetches the corresponding room from the hotel.
     *
     * @param hotel the hotel object containing rooms.
     * @return the room object corresponding to the entered room number.
     * @throws WrongInput   if the user provides invalid input.
     * @throws RoomNotExist if the room does not exist.
     */
    private Room getRoom(Hotel hotel) throws WrongInput, RoomNotExist {
        System.out.print("Please enter room number, you want to view: ");
        Scanner scanner = new Scanner(System.in);
        return getRoomUpper(hotel, scanner);
    }

    /**
     * Parses the guest's name associated with the room. If the room is unoccupied, it returns "-".
     *
     * @param viewRoom the room for which the guest name is to be fetched.
     * @return the guest's name or "-" if the room is unoccupied.
     */
    String parseGuestName(Room viewRoom) {
        String guest = viewRoom.getVisitorName();
        if (guest == null) {
            guest = "-";
        }
        return guest;
    }

    /**
     * Parses the check-in date of the room. If the room is not occupied, it returns "-".
     *
     * @param viewRoom the room for which the check-in date is to be fetched.
     * @return the check-in date as a string or "-" if the room is not occupied.
     */
    String parseDateOfCheckIn(Room viewRoom) {
        String dateOfCheckIn;
        if (viewRoom.getDateOfCheckIn() != null) {
            dateOfCheckIn = viewRoom.getDateOfCheckIn().toString();
        }
        else {
            dateOfCheckIn = "-";
        }
        return dateOfCheckIn;
    }

    /**
     * Parses the check-out date of the room. If the room is not checked out, it returns "-".
     *
     * @param viewRoom the room for which the check-out date is to be fetched.
     * @return the check-out date as a string or "-" if the room is not checked out.
     */
    String parseDateOfCheckOut(Room viewRoom) {
        String dateOfCheckOut;
        if (viewRoom.getDateOfCheckOut() != null) {
            dateOfCheckOut = viewRoom.getDateOfCheckOut().toString();
        }
        else {
            dateOfCheckOut = "-";
        }
        return dateOfCheckOut;
    }

}
