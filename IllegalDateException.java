public class IllegalDateException extends Exception {
	public IllegalDateException() {
		super("A date must range from 0 - 31.");
	}
}
