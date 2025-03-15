package pl.project.commands;

import pl.project.exceptions.RoomNotAvailable;
import pl.project.exceptions.RoomNotExist;
import pl.project.exceptions.WrongInput;
import pl.project.hotel.Hotel;
import pl.project.hotel.Room;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The {@code CheckInCommand} class represents the command to check a guest into a room.
 * It interacts with the user to gather the required information and updates the room's status.
 */
public class CheckInCommand extends Command {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Executes the check-in process. It retrieves the room, gathers the guest information,
     * and updates the room's availability based on the check-in data.
     *
     * @param hotel the hotel object where the check-in occurs.
     * @throws RoomNotExist if the room does not exist.
     * @throws RoomNotAvailable if the room is not available for check-in.
     * @throws WrongInput if the user input is invalid.
     */
    public void execute(Hotel hotel) throws RoomNotExist, RoomNotAvailable, WrongInput {

        Room room = getRoom(hotel);

        if (!room.isAvailable()){
            throw new RoomNotAvailable(room.getNumber());
        }
        else{
            String guestData = getGuestName();
            LocalDate dateCheckIn = getDateCheckIn();
            int lengthOfStay = getLengthCheckIn();

            room.checkIn(guestData, dateCheckIn, lengthOfStay);
            System.out.println("Your reservation is completed!");
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
        System.out.print("Please enter room number, you want to check in: ");
        return getRoomUpper(hotel, scanner);
    }

    /**
     * Retrieves the guest's name from user input.
     *
     * @return the guest's name.
     * @throws WrongInput if the input is empty.
     */
    String getGuestName() throws WrongInput {
        System.out.print("Enter guest's data: ");

        String guestName = scanner.nextLine();
        if (guestName.isEmpty()){
            throw new WrongInput("Please enter guest name.");
        }
        return guestName;
    }

    /**
     * Retrieves the check-in date from user input.
     *
     * @return the check-in date.
     * @throws WrongInput if the date format is incorrect.
     */
    LocalDate getDateCheckIn() throws WrongInput {
        System.out.print("Enter checkin data (in format: YYYY-MM-DD): ");

        String date = scanner.nextLine();
        date = date.replace(" ", "");
        LocalDate dateCheckIn;

        if (date.isEmpty()){
            dateCheckIn = LocalDate.now();
        }else{
            try{
                dateCheckIn = LocalDate.parse(date);
            }
            catch (Exception e){
                throw new WrongInput("Please enter date in correct format.");
            }
        }
        return dateCheckIn;
    }

    /**
     * Retrieves the length of stay (in days) from user input.
     *
     * @return the length of stay in days.
     * @throws WrongInput if the input is not a valid number.
     */
    int getLengthCheckIn() throws WrongInput {
        System.out.print("Enter length of your stay: ");

        int lengthOfStay = 0;
        try {
            lengthOfStay = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            throw new WrongInput("Expected a number.");
        }

        return lengthOfStay;
    }
}
