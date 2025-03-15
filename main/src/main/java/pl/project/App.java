package pl.project;

import pl.project.commands.*;
import pl.project.csv.CSVReader;
import pl.project.hotel.Hotel;

import java.io.IOException;
import java.util.Scanner;

/**
 * The {@code App} class is the entry point for the hotel management system application.
 * It handles initializing the hotel from a CSV file, providing user interaction for
 * various hotel operations, and executing commands to manage rooms, guests, and reservations.
 */
public class App {

    /**
     * The main method that starts the application.
     * It reads the hotel data from a CSV file, prints a greeting message,
     * and begins processing commands entered by the user.
     *
     * @param args the command-line arguments (not used in this implementation).
     * @throws IOException if there is an issue reading the CSV file.
     */
    public static void main(String[] args) throws IOException {
        Hotel hotel = CSVReader.read("main/src/main/resources/data.csv");
        greetings();
        executeCommands(hotel);
    }

    /**
     * Prints the greeting message when the application starts.
     */
    public static void greetings(){
        System.out.println("------------- HOTEL -------------");
        System.out.println("Welcome to hotel manager service");
        System.out.println("Type in command to start (use help to see available commands)\n");
    }

    /**
     * Loops to read and execute commands entered by the user.
     * It continuously prompts the user for input and calls the appropriate command.
     *
     * @param hotel the hotel object containing rooms and booking data.
     */
    public static void executeCommands(Hotel hotel)  {
        Command command;
        Scanner scanner = new Scanner(System.in);
        System.out.print("~: ");
        String commandType = scanner.nextLine();

        commandType = commandParser(commandType);

        while(!commandType.equals("exit")){
            command = switch (commandType) {
                case "prices" -> new PricesCommand();
                case "view" -> new ViewCommand();
                case "checkin" -> new CheckInCommand();
                case "checkout" -> new CheckOutCommand();
                case "list" -> new ListCommand();
                case "save" -> new SaveCommand();
                case "help" -> new HelpCommand();
                default -> new InvalidCommand();
            };
            try{
                command.execute(hotel);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }

            System.out.print("~: ");
            commandType = scanner.nextLine();
            commandType = commandParser(commandType);
        }
        scanner.close();

        goodbye();
    }

    /**
     * Normalizes the command input by converting it to lowercase and removing spaces.
     *
     * @param command the raw user input command.
     * @return the formatted command string.
     */
    public static String commandParser(String command){
        command = command.toLowerCase();
        return command.replace(" ", "");
    }

    /**
     * Prints the goodbye message when the application is exiting.
     */
    public static void goodbye(){
        System.out.println("------------- HOTEL -------------");
    }
}
