package pl.project.commands;

import pl.project.hotel.Hotel;

/**
 * The {@code InvalidCommand} class represents the command that is executed when an invalid command is entered
 * by the user. It informs the user that the command they entered is not recognized or is incorrect.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the invalid command by printing an error message to the console.
     *
     * @param hotel the hotel object (not used in this method but passed for consistency with other commands).
     */
    public void execute(Hotel hotel) {
        System.out.println("Invalid command");
    }
}
