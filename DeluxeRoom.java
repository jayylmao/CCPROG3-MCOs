/**
 * The Deluxe Room class defines a deluxe room in a hotel, with its corresponding details and increased price.
 */
public class DeluxeRoom extends Room {
	/**
	 * Regular Constructor that creates a Deluxe Room instance.
	 * @param roomName The name of the Room. Should be unique compared to other Room instances.
	 * @param roomPrice Base price for a room.
	 */
	public DeluxeRoom(String roomName, double roomPrice) {
		super(roomName, roomPrice * 1.2);
	}

	/**
	 * Overriding method to set the room price of a deluxe room to account for the 20% increase in price
	 * @param roomPrice Base price for a room.
	 */
	@Override
	public void setRoomPrice(double roomPrice) {
		super.setRoomPrice(roomPrice * 1.2);
	}
}
