package pl.project.commands;

import pl.project.csv.CSVWriter;
import pl.project.hotel.Hotel;

/**
 * The {@code SaveCommand} class is responsible for saving the current hotel data to a CSV file.
 * It overwrites the file data.csv and uses the {@link CSVWriter} to write the data into it.
 */
public class SaveCommand extends Command {

    /**
     * Executes the command to save the current hotel data into a CSV file.
     *
     * @param hotel the hotel object containing the current data to be saved.
     */
    public void execute(Hotel hotel) {
        String filePath = "main/src/main/resources/data.csv";
        CSVWriter.write(hotel, filePath);
        System.out.println("Current hotel data has been successfully saved into file!");
    }
}
