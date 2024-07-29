import java.util.HashMap;

public class DeluxeRoom extends Room {
	public DeluxeRoom(String roomName, double roomPrice, HashMap<Integer, Double> datePriceModifier) {
		super(roomName, roomPrice * 1.2, datePriceModifier);
	}

	@Override
	public void setRoomPrice(double roomPrice) {
		super.setRoomPrice(roomPrice * 1.2);
	}
}
