import java.util.ArrayList;

/**
 * The HotelReservationSystem is where the various hotels are stored.
 * The frontend interacts with this to get info on hotels.
 */
public class HotelReservationSystem {
	private ArrayList<Hotel> hotels = new ArrayList<Hotel>();

	/**
	 * Creates a hotel and stores it in the list.
	 * @param hotel Hotel object to add to the list.
	 */
	public void createHotel(Hotel hotel) {
		hotels.add(hotel);
	}

	/**
	 * Returns the list of hotels.
	 * @return List of hotels.
	 */
	public ArrayList<Hotel> getHotels() {
		return hotels;
	}

	/**
	 * Checks each hotel in the list to see if there are any duplicate names.
	 */
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