package pl.project.exceptions;

/**
 * The {@code WrongInput} class represents an exception thrown
 * when an invalid input is entered.
 */
public class WrongInput extends Exception {
    private final String errorInput;

    /**
     * Constructs the exception with the invalid input.
     *
     * @param command the invalid input.
     */
    public WrongInput(String command) {
        this.errorInput = command;
    }

    /**
     * Returns the error message.
     *
     * @return the message indicating that the given input is not correct.
     */
    @Override
    public String getMessage() {
        return "Invalid input: " + errorInput;
    }
}
