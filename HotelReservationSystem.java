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
				return true;
			}
		}

		return false;
	}

	/**
	 * changeHotelName() changes a given hotel's name.
	 * @param hotel Hotel to change name of.
	 * @param name Name to set.
	 */
	public void changeHotelName(Hotel hotel, String name) {
		hotel.setName(name);
	}

	/**
	 * addRooms() adds a given number of rooms in a given hotel, given a valid input.
	 * @param hotel Hotel to add rooms to.
	 * @param count Number of rooms to add to hotel.
	 * @return Success condition.
	 */
	public boolean addRooms(Hotel hotel, int count) {
		if (count > 0) {
			hotel.addRoom(count);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * removeRoom() removes a room given the name of a room.
	 * @param hotel Hotel to remove room from.
	 * @param name Name of room to remove.
	 * @return Success condition.
	 */
	public boolean removeRoom(Hotel hotel, String name) {
		boolean successfullyRemovedRoom;

		do {
			successfullyRemovedRoom = hotel.removeRoom(name);
		} while (!successfullyRemovedRoom);

		return successfullyRemovedRoom;
	}

	/**
	 * updateBasePrice() changes the base price of a given hotel,
	 * so long as there are no reservations in the hotel and that the new price is >= 100.
	 * @param hotel Hotel to change base price of.
	 * @param newBasePrice New price to set base price of hotel to.
	 * @return Success condition.
	 */
	public boolean updateBasePrice(Hotel hotel, double newBasePrice) {
		if (hotel.getReservationCount() != 0) {
			return false;
		}

		if (newBasePrice >= 100.0) {
			hotel.setBasePrice(newBasePrice);
			return true;
		}

		return false;
	}
}