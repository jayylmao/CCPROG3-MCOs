
public class InvalidCheckInDateException extends Exception {
	public InvalidCheckInDateException() {
		super("Your check out date must come after your check in date.");
	}
}
