import java.util.ArrayList;

public class HotelReservationSystem {
	private ArrayList<Hotel> hotels = new ArrayList<Hotel>();

	public void createHotel(Hotel hotel) {
		hotels.add(hotel);
	}

	public ArrayList<Hotel> getHotels() {
		return hotels;
	}

	public boolean isDuplicate(String name) {
		String currentHotelName;

		for (int i = 0; i < hotels.size(); i++) {
			currentHotelName = hotels.get(i).getName();

			if (currentHotelName == name) {
				return true;
			}
		}

		return false;
	}
}