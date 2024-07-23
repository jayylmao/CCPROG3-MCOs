public class InvalidBasePriceException extends Exception {
	public InvalidBasePriceException() {
		super("The base price of a hotel must be greater than or equal to 100.");
	}
}
