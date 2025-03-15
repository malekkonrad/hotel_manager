package pl.project.commands;

import pl.project.hotel.Hotel;
import pl.project.hotel.Room;

import java.util.List;

/**
 * The {@code ListCommand} class is used to display a list of all rooms in the hotel along with their availability,
 * guest data, and check-in/check-out dates. It formats and prints this information in a tabular format.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to display a list of all rooms in the hotel.
     * For each room, its status (available or not), guest data, check-in date, and check-out date are displayed.
     *
     * @param hotel the hotel object containing the rooms to list.
     */
    public void execute(Hotel hotel){
        List<Integer> listOfRooms = hotel.list();

        displayHeader();

        for (Integer roomNumber : listOfRooms) {

            Room currentRoom = hotel.getRoom(roomNumber);
            String available = parseAvailable(currentRoom);
            StringBuilder guest12Data = getStringBuilder(currentRoom);
            String dateOfCheckIn =parseDateOfCheckIn(currentRoom);
            String dateOfCheckOut = parseDateOfCheckOut(currentRoom);

            System.out.print("|         " + roomNumber + " | " + available + " | ");
            System.out.print(guest12Data + " | " + dateOfCheckIn + " | " + dateOfCheckOut + " |\n");
        }
        System.out.println("-".repeat(85));
    }

    /**
     * Displays the header for the room list table.
     */
    void displayHeader(){
        String line = "-".repeat(85);
        System.out.println(line);
        System.out.println("|"+" ".repeat(34) + " LIST OF ROOMS "+ " ".repeat(34)+"|");
        System.out.println(line);
        System.out.println("| ROOM NUMBER |    STATUS     |  GUEST DATA  | DATE OF CHECK IN | DATE OF CHECK OUT |");
        System.out.println(line);
    }

    /**
     * Parses the availability of a room and returns a string representation.
     * If the room is available, it returns "Available", otherwise "Not available".
     *
     * @param currentRoom the room whose availability is being checked.
     * @return a string representing the availability status of the room.
     */
    String parseAvailable(Room currentRoom){
        String available = "  Available  ";
        if (!currentRoom.isAvailable()){
            available = "Not available";
        }
        return available;
    }

    /**
     * Builds and returns a formatted string with the guest data (up to 12 characters).
     * If the guest name is longer than 12 characters, it is truncated.
     * If the name is shorter, it is padded with spaces to 12 characters.
     *
     * @param currentRoom the room whose guest data is being formatted.
     * @return a string representing the guest data, padded or truncated to 12 characters.
     */
    StringBuilder getStringBuilder(Room currentRoom) {
        StringBuilder guest12Data = new StringBuilder("      -     ");
        if (currentRoom.getVisitorName() != null){
            if (currentRoom.getVisitorName().length() > 12) {
                guest12Data = new StringBuilder(currentRoom.getVisitorName().substring(0, 12));
            }
            else {
                guest12Data = new StringBuilder(currentRoom.getVisitorName());
                guest12Data.append(" ".repeat(Math.max(0, 12 - currentRoom.getVisitorName().length() )));
            }
        }
        return guest12Data;
    }

    /**
     * Parses the check-in date of a room and returns it as a formatted string.
     * If no check-in date is available, it returns a placeholder value.
     *
     * @param currentRoom the room whose check-in date is being parsed.
     * @return a string representing the check-in date, or a placeholder if not set.
     */
    String parseDateOfCheckIn(Room currentRoom){
        String dateOfCheckIn;
        if (currentRoom.getDateOfCheckIn() != null) {
            dateOfCheckIn = "   ";
            dateOfCheckIn += currentRoom.getDateOfCheckIn().toString();
            dateOfCheckIn += "   ";
        }
        else {
            dateOfCheckIn = "        -       ";
        }
        return dateOfCheckIn;
    }

    /**
     * Parses the check-out date of a room and returns it as a formatted string.
     * If no check-out date is available, it returns a placeholder value.
     *
     * @param currentRoom the room whose check-out date is being parsed.
     * @return a string representing the check-out date, or a placeholder if not set.
     */
    String parseDateOfCheckOut(Room currentRoom){
        String dateOfCheckOut;
        if (currentRoom.getDateOfCheckOut() != null) {
            dateOfCheckOut = "    ";
            dateOfCheckOut += currentRoom.getDateOfCheckOut().toString();
            dateOfCheckOut += "   ";
        }
        else {
            dateOfCheckOut = "        -        ";
        }
        return dateOfCheckOut;
    }
}
