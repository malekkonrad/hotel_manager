package pl.project.hotel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


/**
 * The {@code Room} class represents a hotel room that can be rented by guests.
 * It contains information about the room number, price per day, availability, description, capacity,
 * and reservation details.
 */
public class Room {

    private final int number;
    private final double pricePerDay;
    private boolean available;
    private final String description;
    private final int capacity;
    private String visitorName;
    private LocalDate dateOfCheckIn;
    private LocalDate  dateOfCheckOut;


    /**
     * Constructs a {@code Room} object.
     *
     * @param number the room number.
     * @param pricePerDay the price per day.
     * @param description a description of the room.
     * @param capacity the maximum number of guests.
     * @param visitorName the name of the guest, or {@code null} if the room is available.
     * @param dateOfCheckIn the check-in date of the guest, or {@code null} if the room is available.
     * @param dateOfCheckOut the check-out date of the guest, or {@code null} if the room is available.
     */
    public Room(int number, double pricePerDay, String description, int capacity, String visitorName, LocalDate dateOfCheckIn, LocalDate dateOfCheckOut) {
        this.number = number;
        this.pricePerDay = pricePerDay;
        this.description = description;
        this.capacity = capacity;
        this.available = visitorName == null;
        this.visitorName = visitorName;
        this.dateOfCheckIn = dateOfCheckIn;
        this.dateOfCheckOut = dateOfCheckOut;
    }

    public int getNumber() {
        return number;
    }
    public double getPricePerDay() {
        return pricePerDay;
    }
    public boolean isAvailable() {
        return available;
    }
    public String getDescription() {
        return description;
    }
    public int getCapacity() {
        return capacity;
    }
    public String getVisitorName() {
        return visitorName;
    }
    public LocalDate getDateOfCheckIn() {
        return dateOfCheckIn;
    }
    public LocalDate getDateOfCheckOut() {
        return dateOfCheckOut;
    }

    /**
     * Checks in a guest to the room.
     *
     * @param visitorName the name of the guest.
     * @param dateOfCheckIn the check-in date.
     * @param lengthOfStay the length of stay in days.
     */
    public void checkIn(String visitorName, LocalDate  dateOfCheckIn, int lengthOfStay) {
        this.visitorName = visitorName;
        this.dateOfCheckIn = dateOfCheckIn;
        this.dateOfCheckOut = dateOfCheckIn.plusDays(lengthOfStay);     // magic
        this.available = false;
    }

    /**
     * Checks out a guest from the room and calculates the cost of the stay.
     *
     * @return the cost of the stay, based on the number of days between check-in and the current date.
     */
    public double checkOut() {
        LocalDate today = LocalDate.now();
        double cost;
        if (today.isAfter(dateOfCheckIn)) {
            cost =  (ChronoUnit.DAYS.between(dateOfCheckIn, today ) * this.pricePerDay);
        } else if (today.isBefore(dateOfCheckIn)) {
            cost = 0;

        } else{
            cost = 0;
        }
        this.available = true;
        this.dateOfCheckIn = null;
        this.dateOfCheckOut = null;
        this.visitorName = null;
        return cost;
    }
}
