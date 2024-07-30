/**
 * ReservationNotFoundException is thrown when attempting
 * to access a reservation that does not exist.
 */
public class ReservationNotFoundException extends Exception {
    /**
	 * Constructor that makes an ReservationNotFoundException.
	 */
    public ReservationNotFoundException() {
        super("Reservation not found.");
    }
}
