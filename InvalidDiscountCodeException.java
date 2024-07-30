/**
 * InvalidDiscountCodeException is thrown when an invalid discount code
 * is provided.
 */
public class InvalidDiscountCodeException extends Exception {
	/**
	 * Constructor that makes an InvalidDiscountCodeException.
	 */
	public InvalidDiscountCodeException() {
		super("Invalid discount code entered.");
	}
}
