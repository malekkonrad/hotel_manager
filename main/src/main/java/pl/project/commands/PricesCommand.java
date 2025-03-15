package pl.project.commands;

import pl.project.hotel.Hotel;
import pl.project.util.Map;

/**
 * The {@code PricesCommand} class is used to display the prices of all rooms in the hotel.
 * It retrieves the price for each room and displays it in a formatted table.
 */
public class PricesCommand extends Command {

    /**
     * Executes the command to display the prices of all rooms in the hotel.
     * For each room, its number and price are displayed in a tabular format.
     *
     * @param hotel the hotel object containing the rooms and their prices.
     */
    public void execute(Hotel hotel) {
        Map<Integer, Double> prices = hotel.prices();
        String line = " ------------- ";

        System.out.println(line);
        System.out.println("|   PRICES    |");
        System.out.println("| NUM | PRICE |");
        System.out.println(line);

        for (Integer key : prices.keys()){
            System.out.println("| " + key + " | " + prices.get(key) + " |");
        }
        System.out.println(line);
    }
}
