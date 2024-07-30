/**
 * InvalidDiscountCodeException is thrown when an invalid discount code
 * is provided.
 */
public class InvalidDiscountCodeException extends Exception {
	public InvalidDiscountCodeException() {
		super("Invalid discount code entered.");
	}
}
