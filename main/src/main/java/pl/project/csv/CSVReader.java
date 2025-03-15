package pl.project.csv;

import pl.project.hotel.Hotel;
import pl.project.hotel.Room;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;

/**
 * The {@code CSVReader} class provides functionality for reading room and hotel data
 * from a CSV file and converting it into {@link Hotel} and {@link Room} objects.
 */
public class CSVReader {

    /**
     * Enum representing the headers used in the CSV file.
     */
    enum Header{
        ROOM_NUMBER, PRICE, DESCRIPTION, CAPACITY, GUEST, DATE_OF_CHECK_IN, DATE_OF_CHECK_OUT
    }

    /**
     * The default CSV format with headers specified by the {@code Header} enum.
     */
    static final CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(Header.class).setSkipHeaderRecord(true).build();

    /**
     * Reads data from the specified CSV file and creates a {@link Hotel} object.
     *
     * @param filePath the path to the CSV file.
     * @return a {@link Hotel} object containing data from the CSV file.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    public static Hotel read(String filePath) throws IOException {

        Hotel hotel = new Hotel();

        try (CSVParser csvParser = new CSVParser(new FileReader(filePath), csvFormat)) {

            for (CSVRecord csvRecord : csvParser) {
                int roomNumber = Integer.parseInt(csvRecord.get(Header.ROOM_NUMBER));

                double price = Double.parseDouble(csvRecord.get(Header.PRICE));

                String description = csvRecord.get(Header.DESCRIPTION);

                int capacity = Integer.parseInt(csvRecord.get(Header.CAPACITY));

                String guest = parseNullGuestData(csvRecord.get(Header.GUEST));

                LocalDate dateOfCheckIn = parseNullDateData(csvRecord.get(Header.DATE_OF_CHECK_IN));

                LocalDate dateOfCheckOut = parseNullDateData(csvRecord.get(Header.DATE_OF_CHECK_OUT));

                Room room = new Room(roomNumber, price, description, capacity, guest, dateOfCheckIn, dateOfCheckOut);
                hotel.addRoom(room);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return hotel;
    }

    /**
     * Parses the guest data from a CSV record, returning {@code null} if the data is empty.
     *
     * @param data the guest data as a string.
     * @return the guest name, or {@code null} if the data is empty.
     */
    private static String parseNullGuestData(String data){
        if(data.isEmpty()){
            return null;
        }
        return data;
    }

    /**
     * Parses the date data from a CSV record, returning {@code null} if the data is empty.
     *
     * @param data the date as a string.
     * @return a {@link LocalDate} object, or {@code null} if the data is empty.
     */
    private static LocalDate parseNullDateData(String data){
        if(data.isEmpty()){
            return null;
        }
        return LocalDate.parse(data);
    }
}
