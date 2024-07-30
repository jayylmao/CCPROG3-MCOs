/**
 * InvalidRoomCountException is thrown when attempting to create a hotel
 * with an invalid number of rooms.
 */
public class InvalidRoomCountException extends Exception {
	/**
	 * Constructor that makes an InvalidRoomCountException.
	 */
	public InvalidRoomCountException() {
		super("Rooms must be between 1 and 50.");
	}
}