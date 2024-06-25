import java.util.ArrayList;

/**
 * The HotelReservationSystem is where the various hotels are stored.
 * The frontend interacts with this to get info on hotels.
 */
public class HotelReservationSystem {
	private ArrayList<Hotel> hotels;

	/**
	 * Contructor that initializes the hotels ArrayList.
	 */
	public HotelReservationSystem() {
		hotels = new ArrayList<Hotel>();
	}

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

			if (currentHotelName.equals(name)) {
				return true; // Duplicate was found if this is returned
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
			return hotel.addRoom(count);
		} else {
			return false;
		}
	}

	/**
	 * Gets a Hotel based on a given name.
	 * @param name Name of Hotel to search.
	 * @return Hotel with matching name. Null if there is no Hotel with matching name.
	 */
	public Hotel getHotel(String name) {
		for (int i = 0; i < getHotels().size(); i++) {
			if (getHotels().get(i).getName().equals(name)) {
				return getHotels().get(i);
			}
		}

		return null;
	}

	/**
	 * removeRoom() removes a room given the name of a room.
	 * @param hotel Hotel to remove room from.
	 * @param name Name of room to remove.
	 * @return Success condition.
	 */
	public boolean removeRoom(Hotel hotel, String name) {
		boolean successfullyRemovedRoom;
		successfullyRemovedRoom = hotel.removeRoom(name);

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
		for (int i = 0; i < hotel.getRoomCount(); i++) {
			if (hotel.getRoom(i).getReservationCount() != 0) {
				return false;
			}
		}

		if (newBasePrice >= 100.0) {
			hotel.setBasePrice(newBasePrice);
			return true;
		}

		return false;
	}

	/**
	 * Removes a Hotel instance using a name.
	 * @param name Name of the Hotel to be removed.
	 * @return True if a Hotel is successfully removed. False otherwise.
	 */
	public boolean removeHotel(String name) {
		for(int i = 0; i < getHotelCount(); i++) {
			if(getHotels().get(i).getName().equals(name)) {
				this.hotels.remove(i);
				return true;
			}
		}
		return false;
	}
}