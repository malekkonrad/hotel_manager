package pl.project.exceptions;

/**
 * The {@code RoomNotAvailable} class represents an exception thrown
 * when trying to perform an operation on a room that is not available (already checked in).
 */
public class RoomNotAvailable extends Exception {
    private final int errorNumber;

    /**
     * Constructs the exception with the room number that is not available.
     *
     * @param roomNumber the number of the unavailable room.
     */
    public RoomNotAvailable(Integer roomNumber) {
        this.errorNumber = roomNumber;
    }

    /**
     * Returns the error message.
     *
     * @return the message indicating that the room with the given number is not available.
     */
    @Override
    public String getMessage() {
        return "Room number: " + errorNumber + " is not available, try again with correct room number. (To see available rooms use list command) ";
    }
}
