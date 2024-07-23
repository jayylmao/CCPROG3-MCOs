public class InvalidHotelNameException extends Exception {
	public InvalidHotelNameException() {
		super("The given hotel name is invalid.");
	}
}
