/**
 * The Executive Room class defines a executive room in a hotel, with its corresponding details and increased price compared to all other room types.
 */
public class ExecutiveRoom extends Room {
	public ExecutiveRoom(String roomName, double roomPrice) {
		super(roomName, roomPrice * 1.35);
	}

	@Override
	public void setRoomPrice(double roomPrice) {
		super.setRoomPrice(roomPrice * 1.35);
	}
}
