package pl.project.commands;

import pl.project.hotel.Hotel;

/**
 * The {@code HelpCommand} class represents the command that displays a list of available commands
 * to the user. It helps the user understand what actions they can perform within the hotel management system.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command by printing the list of available commands to the console.
     *
     * @param hotel the hotel object (not used in this method but passed for consistency with other commands).
     */
    public void execute(Hotel hotel){
        System.out.println("Available commands:");
        System.out.println(" -> prices");
        System.out.println(" -> view");
        System.out.println(" -> checkin");
        System.out.println(" -> checkout");
        System.out.println(" -> list");
        System.out.println(" -> save");
        System.out.println(" -> exit");
    }
}
