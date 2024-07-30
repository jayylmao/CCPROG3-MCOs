/**
 * InvalidCheckInDateException is thrown when an invalid date range for
 * checking in to a room is provided, such as 14 - 12.
 */
public class InvalidCheckInDateException extends Exception {
	/**
	 * Constructor that makes an InvalidCheckInDateException.
	 */
	public InvalidCheckInDateException() {
		super("Your check out date must come after your check in date.");
	}
}
