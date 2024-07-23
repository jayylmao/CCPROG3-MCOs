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
	 * @param name Name of hotel.
	 * @param roomCount Number of rooms.
	 * @throws InvalidRoomCountException Exception when a hotel is initialized with an invalid number of rooms.
	 * @throws DuplicateNameException Exception when a hotel is initialized with a duplicate name.
	 * @throws InvalidHotelNameException Exception when a hotel is initialized with a blank string as a name.
	 */
	public void addHotel(String name, int roomCount) throws InvalidRoomCountException, DuplicateNameException, InvalidHotelNameException {
		Hotel newHotel;
		newHotel = new Hotel(name, roomCount);

		if (name.equals("")) {
			throw new InvalidHotelNameException();
		} else if (isDuplicate(name)) {
			throw new DuplicateNameException();
		} else {
			hotels.add(newHotel);
		}
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
		return hotels.size();
	}

	/**
	 * Checks each hotel in the list to see if there are any duplicate names.
	 * @param name String to check against list of hotel names to check for duplicate.
	 * @return Boolean that indicates if a duplicate was found or not.
	 */
	private boolean isDuplicate(String name) {
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
	 * @throws DuplicateNameException Exception when a duplicate name is found.
	 */
	public void changeHotelName(Hotel hotel, String name) throws DuplicateNameException {
		if (!isDuplicate(name)) {
			hotel.setName(name);
		} else {
			throw new DuplicateNameException();
		}
	}

	/**
	 * Gets a Hotel based on a given name.
	 * @param name Name of Hotel to search.
	 * @return Hotel with matching name. Null if there is no Hotel with matching name.
	 */
	public Hotel getHotel(String name) {
		for (int i = 0; i < getHotelCount(); i++) {
			if (getHotels().get(i).getName().equals(name)) {
				return getHotels().get(i);
			}
		}

		return null;
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