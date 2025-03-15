package pl.project.csv;

import pl.project.hotel.Hotel;
import pl.project.hotel.Room;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * The {@code CSVWriter} class provides methods for writing hotel room data into a CSV file.
 * It writes the details of the rooms in the given {@code Hotel} object to a CSV file at the specified file path.
 */
public class CSVWriter {

    static final  String[] headers = {"ROOM_NUMBER", "PRICE", "DESCRIPTION", "CAPACITY", "GUEST", "DATE_OF_CHECK_IN", "DATE_OF_CHECK_OUT"};

    static final CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(headers).build();

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private CSVWriter() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Writes the hotel room data to a CSV file.
     * Each room's data (number, price per day, description, capacity, guest name, check-in, and check-out dates)
     * is written to a new record in the CSV file.
     *
     * @param hotel the {@code Hotel} object containing the room data.
     * @param filePath the path of the CSV file to write to.
     */
    public static void write(Hotel hotel, String filePath){

        List<Integer> listOfRooms = hotel.list();

        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(filePath), csvFormat)) {

            for (Integer roomNumber : listOfRooms) {
                Room currentRoom = hotel.getRoom(roomNumber);
                csvPrinter.printRecord(currentRoom.getNumber(),
                        currentRoom.getPricePerDay(), currentRoom.getDescription(),
                        currentRoom.getCapacity(), currentRoom.getVisitorName(),
                        currentRoom.getDateOfCheckIn(), currentRoom.getDateOfCheckOut());
            }
            csvPrinter.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
