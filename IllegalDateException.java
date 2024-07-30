/**
 * IllegalDateException is thrown when attempting to create a Date instance
 * with an invalid day value, such as 0 or 32.
 */
public class IllegalDateException extends Exception {
	public IllegalDateException() {
		super("A date must range from 1 - 31.");
	}
}
