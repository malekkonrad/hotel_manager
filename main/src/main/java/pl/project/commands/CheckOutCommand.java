package pl.project.commands;

import pl.project.exceptions.RoomAvailable;
import pl.project.exceptions.RoomNotExist;
import pl.project.exceptions.WrongInput;
import pl.project.hotel.Hotel;
import pl.project.hotel.Room;

import java.util.Scanner;

/**
 * The {@code CheckOutCommand} class represents the command to check a guest out of a room.
 * It checks if the room is currently occupied and calculates the cost of the stay upon checkout.
 */
public class CheckOutCommand extends Command {

    /**
     * Executes the check-out process. It retrieves the room, checks if it's available,
     * and calculates the cost of the guest's stay.
     *
     * @param hotel the hotel object where the check-out occurs.
     * @throws RoomNotExist if the room does not exist.
     * @throws RoomAvailable if the room is not currently occupied.
     * @throws WrongInput if the user input is invalid.
     */
    public void execute(Hotel hotel) throws RoomNotExist, RoomAvailable, WrongInput {
        Room room = getRoom(hotel);

        if (room.isAvailable()){
            throw new RoomAvailable(room.getNumber());
        }
        else{
            String guest = room.getVisitorName();
            double cost = room.checkOut();
            System.out.println(guest + " has been checked out");
            System.out.println("Cost of stay: " + cost);
        }
    }

    /**
     * Retrieves the room based on user input.
     *
     * @param hotel the hotel object containing the rooms.
     * @return the selected {@link Room}.
     * @throws WrongInput if the input is invalid.
     * @throws RoomNotExist if the room number does not exist in the hotel.
     */
    Room getRoom(Hotel hotel) throws WrongInput, RoomNotExist {
        System.out.print("Please enter room number, you want to check out from: ");
        Scanner scanner = new Scanner(System.in);
        return getRoomUpper(hotel, scanner);
    }
}
