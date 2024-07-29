import java.util.HashMap;

public class ExecutiveRoom extends Room {
	public ExecutiveRoom(String roomName, double roomPrice, HashMap<Integer, Double> datePriceModifier) {
		super(roomName, roomPrice * 1.35, datePriceModifier);
	}

	@Override
	public void setRoomPrice(double roomPrice) {
		super.setRoomPrice(roomPrice * 1.35);
	}
}
