package pl.project.exceptions;

/**
 * The {@code RoomAvailable} class represents an exception thrown
 * when trying to perform an operation on a room that is available (not checked in).
 */
public class RoomAvailable extends Exception{
    private final int errorNumber;

    /**
     * Constructs the exception with the room number that is available.
     *
     * @param roomNumber the number of the available room.
     */
    public RoomAvailable(Integer roomNumber) {
        this.errorNumber = roomNumber;
    }

    /**
     * Returns the error message.
     *
     * @return the message indicating that the room with the given number is available.
     */
    @Override
    public String getMessage() {
        return "Room number: " + errorNumber + " is available - nobody checked into it, try again with correct room number. (To see checked in rooms use list command) ";
    }
}
