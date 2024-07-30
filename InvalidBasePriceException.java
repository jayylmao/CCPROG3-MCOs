/**
 * InvalidBasePriceException is thrown when attempting to set the base price of
 * a hotel to a value less than 100 or some other invalid value.
 */
public class InvalidBasePriceException extends Exception {
	public InvalidBasePriceException() {
		super("The base price of a hotel must be greater than or equal to 100.");
	}
}
