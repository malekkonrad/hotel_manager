package pl.project.hotel;

import pl.project.util.Map;
import pl.project.util.MyMap;

import java.util.List;

/**
 * The {@code Hotel} class represents a hotel containing rooms.
 * The rooms are stored in a map, where the key is the room number
 * and the value is a {@link Room} object.
 */
public class Hotel {

    /**
     * A map that stores the hotel rooms.
     * The key is the room number, and the value is a {@link Room} object.
     */
    Map<Integer, Room> rooms =  new MyMap<>();


    /**
     * Adds a new room to the map of rooms.
     *
     * @param room an initialized {@link Room} object.
     */
    public void addRoom(Room room){
        rooms.put(room.getNumber(), room);
    }

    /**
     * Creates a map of room prices, where the key is the room number
     * and the value is the price per day.
     *
     * @return a map with room numbers as keys and corresponding prices as values.
     */
    public Map<Integer, Double> prices(){
        Map<Integer, Double> prices = new MyMap<>();
        for (Room room : rooms.values()) {
            prices.put(room.getNumber(), room.getPricePerDay());
        }
        return prices;
    }

    /**
     * Returns the room with the specified room number.
     *
     * @param number the room number.
     * @return the {@link Room} object with the specified number,
     *         or {@code null} if the room does not exist.
     */
    public Room getRoom(int number){
        return rooms.get(number);
    }

    /**
     * Returns a list of all room numbers in the hotel.
     *
     * @return a list of room numbers.
     */
    public List<Integer> list(){
        return rooms.keys();
    }

}
