/**
 * ReservationNotFoundException is thrown when attempting
 * to access a reservation that does not exist.
 */
public class ReservationNotFoundException extends Exception {
    public ReservationNotFoundException() {
        super("Reservation not found.");
    }
}
