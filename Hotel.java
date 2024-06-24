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
	 */
	public Hotel(String name, int roomCount) {
		this.name = name;

		// Create new ArrayList instance rooms.
		rooms = new ArrayList<Room>(roomCount);

		// Default room base price is 1299 as per spec.
		this.basePrice = 1299;

		// Initialize rooms when creating the hotel.
		for (int i = 0; i < roomCount; i++) {
			rooms.add(new Room(String.format("%03d", i + 1)));
		}
	}

	public String getName() {
		return name;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	/**
	 * getRoom() takes a room name and searches the hotel for a room with that name.
	 * @param inputRoomName Name to search for.
	 * @return Room object with matching name.
	 */
	public Room getRoom(String inputRoomName) {
		for (int i = 0; i < rooms.size(); i++) {
			if (inputRoomName.equals(rooms.get(i).getName())) {
				return rooms.get(i);
			}
		}

		return null;
	}

	public Room getRoom(int i) {
		if (i >= 0 && i < getRoomCount()) {
			return rooms.get(i);
		} else {
			return null;
		}

	}

	public int getRoomCount() {
		return rooms.size();
	}

	/**
	 * getAvailableRoomCount() returns the number of rooms in the hotel that are not occupied.
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
	 * getOccupiedRoomCount() returns the number of rooms in the hotel that are occupied.
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

	public double getBasePrice() {
		return basePrice;
	}

	public String getFormattedBasePrice() {
		return String.format("%.2f", basePrice);
	}

	/**
	 * addRoom() adds a room to the hotel.
	 */
	public void addRoom(int count) {
		int lastRoomName = Integer.parseInt(getRoom(getRoomCount() - 1).getName());

		for (int i = 0; i < count; i++) {
			rooms.add(new Room(String.format("%03d", lastRoomName + 1)));
		}
	}

	/**
	 * removeRoom() removes a room from the hotel.
	 * @param name Name of room to remove.
	 * @return Success condition.
	 */
	public boolean removeRoom(String name) {
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getName().equals(name) &&
				rooms.get(i).getReservationCount() == 0) {
				rooms.remove(i);
				return true;
			}
		}

		return false;
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

		for (int j = 0; j < room.getReservationCount(); j++) {
			if (room.getReservations().get(j).getCheckIn().isBetween(checkInDate, checkOutDate) ||
				room.getReservations().get(j).getCheckOut().isBetween(checkInDate, checkOutDate) ||
				checkInDate.isBetween(room.getReservations().get(j).getCheckIn(), room.getReservations().get(j).getCheckOut()) ||
				checkOutDate.isBetween(room.getReservations().get(j).getCheckIn(), room.getReservations().get(j).getCheckOut())) {
				roomAvailable = false;
			}
		}

		return roomAvailable;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
}