package pl.project.exceptions;

/**
 * The {@code RoomNotExist} class represents an exception thrown
 * when trying to access a room that does not exist.
 */
public class RoomNotExist extends Exception {

    private final int errorNumber;

    /**
     * Constructs the exception with the room number that does not exist.
     *
     * @param roomNumber the number of the room that does not exist.
     */
    public RoomNotExist(Integer roomNumber) {
        this.errorNumber = roomNumber;
    }

    /**
     * Returns the error message.
     *
     * @return the message indicating that the room with the given number does not exist.
     */
    @Override
    public String getMessage() {
        return "Room number: " + errorNumber + " does not exist, try again with correct room number. (To see available rooms use list command) ";
    }
}