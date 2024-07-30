/**
 * InvalidHotelNameException is thrown when attempting to create a hotel
 * with an invalid name (usually a blank name).
 */
public class InvalidHotelNameException extends Exception {
	/**
	 * Constructor that makes an InvalidHotelNameException.
	 */
	public InvalidHotelNameException() {
		super("The given hotel name is invalid.");
	}
}
