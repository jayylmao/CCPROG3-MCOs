/**
 * The Executive Room class defines a executive room in a hotel, with its corresponding details and increased price compared to all other room types.
 */
public class ExecutiveRoom extends Room {
	/**
	 * Regular Constructor that creates a Deluxe Room instance.
	 * @param roomName The name of the Room. Should be unique compared to other Room instances.
	 * @param roomPrice Base price for a room.
	 */
	public ExecutiveRoom(String roomName, double roomPrice) {
		super(roomName, roomPrice * 1.35);
	}

	/**
	 * Overriding method to set the room price of an executive room to account for the 35% increase in price
	 * @param roomPrice Base price for a room.
	 */
	@Override
	public void setRoomPrice(double roomPrice) {
		super.setRoomPrice(roomPrice * 1.35);
	}
}
