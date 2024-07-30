/**
 * The Deluxe Room class defines a deluxe room in a hotel, with its corresponding details and increased price.
 */
public class DeluxeRoom extends Room {
	public DeluxeRoom(String roomName, double roomPrice) {
		super(roomName, roomPrice * 1.2);
	}

	@Override
	public void setRoomPrice(double roomPrice) {
		super.setRoomPrice(roomPrice * 1.2);
	}
}
