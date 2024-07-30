/**
 * The Standard Room class defines a standard room in a hotel, with its corresponding details and base price.
 */
public class StandardRoom extends Room {
	/**
	 * Regular Constructor that creates a Standard Room instance.
	 * @param roomName The name of the Room. Should be unique compared to other Room instances.
	 * @param roomPrice Base price for a room.
	 */
	public StandardRoom(String roomName, double roomPrice) {
		super(roomName, roomPrice);
	}
}
