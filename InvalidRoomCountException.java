public class InvalidRoomCountException extends Exception {
	public InvalidRoomCountException() {
		super("Rooms must be between 1 and 50.");
	}
}