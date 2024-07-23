
public class RoomHasBookingsException extends Exception {
	public RoomHasBookingsException() {
		super("This room has bookings and cannot be modified.");
	}
}
