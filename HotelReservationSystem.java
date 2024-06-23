import java.util.ArrayList;

/**
 * The HotelReservationSystem is where the various hotels are stored.
 * The frontend interacts with this to get info on hotels.
 */
public class HotelReservationSystem {
	private ArrayList<Hotel> hotels = new ArrayList<Hotel>();

	/**
	 * Adds a hotel and stores it in the list.
	 * @param hotel Hotel object to add to the list.
	 */
	public void addHotel(Hotel hotel) {
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
	 * Returns the number of hotels in the list.
	 * @return Number of hotels in the list.
	 */
	public int getHotelCount() {
		return getHotels().size();
	}

	/**
	 * Checks each hotel in the list to see if there are any duplicate names.
	 * @param name String to check against list of hotel names to check for duplicate.
	 * @return Boolean that indicates if a duplicate was found or not.
	 */
	public boolean isDuplicate(String name) {
		String currentHotelName;

		for (int i = 0; i < hotels.size(); i++) {
			currentHotelName = hotels.get(i).getName();

			if (currentHotelName == name) {
				return true; // Duplicate was found if this is returned
			}
		}

		return false;
	}
}