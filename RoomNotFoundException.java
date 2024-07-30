/**
 * RoomNotFoundException is thrown when a room with a given name
 * is not found.
 */
public class RoomNotFoundException extends Exception {
	public RoomNotFoundException(String message) {
		super(message);
	}
}
