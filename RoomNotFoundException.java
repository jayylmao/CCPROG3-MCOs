/**
 * RoomNotFoundException is thrown when a room with a given name
 * is not found.
 */
public class RoomNotFoundException extends Exception {
	/**
	 * Constructor that makes an RoomNotFoundException.
	 * @param message Room name that is not found.
	 */
	public RoomNotFoundException(String message) {
		super(message);
	}
}
