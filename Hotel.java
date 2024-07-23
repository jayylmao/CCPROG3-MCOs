import java.util.ArrayList;

/**
 * The Hotel class defines a hotel with a name, an array of rooms, and a base room price.
 */
public class Hotel {
	/** Name of the hotel. */
	private String name;

	/**
	 * List of rooms in the hotel.
	 * Constraints: Must be between 1 - 50.
	 */
	private ArrayList<Room> rooms;

	/**
	 * How much a room costs per night.
	 * All rooms in the hotel cost the same.
	 * Constraints: Must be >= 100.
	 */
	private double basePrice;

	/**
	 * Constructor that creates an empty hotel with only its name.
	 * @param name Name of hotel.
	 * @param roomCount How many rooms are in the hotel.
	 * @throws InvalidRoomCountException 
	 */
	public Hotel(String name, int roomCount) throws InvalidRoomCountException {
		if (roomCount > 0 && roomCount < 51) {
			this.name = name;

			// Create new ArrayList instance rooms.
			rooms = new ArrayList<Room>(roomCount);

			// Default room base price is 1299 as per spec.
			this.basePrice = 1299;

			// Initialize rooms when creating the hotel. Names go from 001, 002, ... 00n
			for (int i = 0; i < roomCount; i++) {
				rooms.add(new StandardRoom(String.format("%03d", i + 1), basePrice));
			}
		} else {
			throw new InvalidRoomCountException();
		}
	}

	/**
	 * Gets the name of the Hotel.
	 * @return Hotel name.
	 */
	public String getName() {
		return name;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	/**
	 * getRoom() takes a room name and searches the hotel for a room with that name.
	 * @param inputRoomName Name to search for.
	 * @return Room object with matching name. Returns null if no Room with a matching name was found.
	 */
	public Room getRoom(String inputRoomName) {
		for (int i = 0; i < rooms.size(); i++) {
			if (inputRoomName.equals(rooms.get(i).getName())) {
				return rooms.get(i);
			}
		}

		return null;
	}

	/**
	 * Secondary getRoom function that uses an index instead of a name to return a Room object.
	 * @param i index of the Room.
	 * @return Room object with matching index. Returns null if out of bounds.
	 */
	public Room getRoom(int i) {
		if (i >= 0 && i < getRoomCount()) {
			return rooms.get(i);
		} else {
			return null;
		}

	}

	/**
	 * Gets the total count of Rooms in the Hotel.
	 * @return Number of Rooms that the Hotel has.
	 */
	public int getRoomCount() {
		return rooms.size();
	}

	/**
	 * getAvailableRoomCount() returns the number of rooms in the hotel that are not occupied at all throughout the entire month.
	 * @return Number of unoccupied (free) rooms.
	 */
	public int getAvailableRoomCount() {
		int count = 0;
		for (int i = 0; i < rooms.size(); i++) {
			if (!rooms.get(i).isOccupied()) {
				count += 1;
			}
		}

		return count;
	}

	/**
	 * getOccupiedRoomCount() returns the number of rooms in the hotel that are occupied or have at least one reservation throughout the month.
	 * @return Number of occupied rooms.
	 */
	public int getBookedRoomCount() {
		int count = 0;
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).isOccupied()) {
				count += 1;
			}
		}

		return count;
	}

	/**
	 * Returns the number of rooms in the hotel that are occupied in a specific timeframe.
	 * @param checkIn Date object describing the check in time.
	 * @param checkOut Date object describing the check out time. These will determine timeframe for checking.
	 * @return Number of occupied rooms.
	 */
	public int getBookedRoomCount(Date checkIn, Date checkOut) {
		if (checkOut.isBefore(checkIn)) {
			throw new IllegalArgumentException("Check-out date must be after check-in date.");
		}

		int count = 0;
		for (int i = 0; i < this.rooms.size(); i++) {
			if (!isRoomAvailable(getRoom(i).getName(), checkIn, checkOut)) {
				count += 1;
			}
		}

		return count;
	}

	/**
	 * Returns the number of rooms in the hotel that are available in a specific timeframe.
	 * @param checkIn Date object describing the check in time.
	 * @param checkOut Date object describing the check out time. These will determine timeframe for checking.
	 * @return Number of unoccupied (free) rooms.
	 */
	public int getAvailableRoomCount(Date checkIn, Date checkOut) {
		if (checkOut.isBefore(checkIn)) {
			throw new IllegalArgumentException("Check-out date must be after check-in date.");
		}

		int count = 0;
		for (int i = 0; i < this.rooms.size(); i++) {
			if (isRoomAvailable(getRoom(i).getName(), checkIn, checkOut)) {
				count += 1;
			}
		}

		return count;
	}

	/**
	 * Gets the base price per night for all Rooms in the Hotel.
	 * @return Base price per night. It is defaulted to 1299
	 */
	public double getBasePrice() {
		return basePrice;
	}

	/**
	 * Gets the base price as a formatted String (real number with 2 decimal places) for all Rooms in the Hotel.
	 * @return Base price per night as a String.
	 */
	public String getFormattedBasePrice() {
		return String.format("%.2f", basePrice);
	}

	/**
	 * addRoom() adds a specified number of rooms to the hotel.
	 * @param count Number of rooms to add.
	 * @throws InvalidRoomCountException Exception for invalid number of rooms. Hotels can have a maximum of 50 rooms as per specifications.
	 */
	public void addRoom(int count) throws InvalidRoomCountException {
		if (getRoomCount() + count > 50 || count < 1) {
			throw new InvalidRoomCountException();
		} else {
			int lastRoomName;

			for (int i = 0; i < count; i++) {
				lastRoomName = Integer.parseInt(getRoom(getRoomCount() - 1).getName());
				rooms.add(new StandardRoom(String.format("%03d", lastRoomName + 1), basePrice));
			}
		}
	}

	/**
	 * removeRoom() removes a room from the hotel.
	 * @param name Name of room to remove.
	 * @throws RoomNotFoundException Exception when a room with a given name is not found in the hotel.
	 * @throws InvalidRoomCountException Exceprtion when the room being removed is the last in a hotel.
	 */
	public void removeRoom(String name) throws RoomNotFoundException, InvalidRoomCountException, RoomHasBookingsException {
		if (rooms.size() == 1) {
			throw new InvalidRoomCountException();
		}
		
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getName().equals(name)) {
				if (rooms.get(i).getReservationCount() == 0) {
					rooms.remove(i);
				} else {
					throw new RoomHasBookingsException();
				}
			}
		}

		throw new RoomNotFoundException("A room with that name was not found.");
	}

	/**
	 * isRoomAvailable() checks if a given room is available between the specified dates.
	 * @param roomName Name of room to check in to.
	 * @param checkInDate Check-in date of user.
	 * @param checkOutDate Check-out date of user.
	 * @return True if room is available. False otherwise.
	 */
	public boolean isRoomAvailable(String roomName, Date checkInDate, Date checkOutDate) {
		boolean roomAvailable = true;
		Room room = getRoom(roomName);

		if(room.isOccupied(checkInDate, checkOutDate)) {
			roomAvailable = false;
		}

		return roomAvailable;
	}

	/**
	 * Sets the name of the Hotel.
	 * @param name New name of Hotel.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the new base price of the Hotel.
	 * @param basePrice New base price of the Hotel.
	 * @throws RoomHasBookingsException Exception when attempting to modify a hotel with bookings.
	 * @throws InvalidBasePriceException Exception when attempting tot set a base price of less than 100.
	 */
	public void setBasePrice(double basePrice) throws RoomHasBookingsException, InvalidBasePriceException {
		for (int i = 0; i < getRoomCount(); i++) {
			if (getRoom(i).getReservationCount() != 0) {
				throw new RoomHasBookingsException();
			}
		}

		if (basePrice >= 100.0) {
			this.basePrice = basePrice;

			for (Room room : rooms) {
				room.setRoomPrice(basePrice);
			}
		} else {
			throw new InvalidBasePriceException();
		}
	}

	/**
	 * Gets the total amount of earnings from all reservations in the Hotel.
	 * @return Total amount of Earnings based on the number of reservations and how long each reservation lasts.
	 */
	public double getTotalEarnings() {
		double total = 0;

		for(int i = 0; i < this.rooms.size(); i++) {
			for(int j = 0; j < getRoom(i).getReservationCount(); j++) {
				total += getRoom(i).getReservation(j).getTotalPrice();
			}
		}

		return total;
	}

	/**
	 * isFull() checks if all of the rooms in the hotel are completely booked (from day 1 - 31).
	 * @return True if all rooms are completely booked. False otherwise.
	 */
	public boolean isFull() {
		for (int i = 0; i < getRoomCount(); i++) {
			if (!getRoom(i).isFull()) {
				return false;
			}
		}

		return true;
	}
}