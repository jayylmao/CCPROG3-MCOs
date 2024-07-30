import java.util.HashMap;

public class ExecutiveRoom extends Room {
	public ExecutiveRoom(String roomName, double roomPrice) {
		super(roomName, roomPrice * 1.35);
	}

	@Override
	public void setRoomPrice(double roomPrice) {
		super.setRoomPrice(roomPrice * 1.35);
	}
}
