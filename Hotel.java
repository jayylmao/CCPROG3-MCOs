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

	private ArrayList<Reservation> reservations = new ArrayList<Reservation>();

	/**
	 * How much a room costs per night.
	 * All rooms in the hotel cost the same.
	 * Constraints: Must be >= 100.
	 */
	private double basePrice;

	private int roomCount;

	/**
	 * Constructor that creates an empty hotel with only its name.
	 * @param name Name of hotel.
	 * @param roomCount How many rooms are in the hotel.
	 */
	public Hotel(String name, int roomCount) {
		this.name = name;
		this.roomCount = roomCount;

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
		Room blankRoom = new Room();

		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getName() == inputRoomName) {
				return rooms.get(i);
			}
		}

		return blankRoom;
	}

	public Room getRoom(int i) {
		if (i >= 0 && i < getRoomCount()) {
			return rooms.get(i);
		} else {
			return new Room();
		}

	}

	public int getRoomCount() {
		return roomCount;
	}

	/**
	 * getFreeRoomCount() returns the number of rooms in the hotel that are not occupied.
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

	/**
	 * getReservation() returns the nth reservation from the list of reservations in the hotel.
	 * @param n Number in the reservation list.
	 * @return nth reservation in the list.
	 */
	public Reservation getReservation(int n) {
		return reservations.get(n);
	}

	public int getReservationCount() {
		return reservations.size();
	}

	public double getBasePrice() {
		return basePrice;
	}

	/**
	 * addRoom() adds a room to the hotel.
	 */
	public void addRoom(int count) {
		for (int i = 0; i < count; i++) {
			roomCount += 1;
			rooms.add(new Room(String.format("%03d", roomCount)));
		}
	}

	/**
	 * removeRoom() removes a room from the hotel.
	 * @param name Name of room to remove.
	 * @return Success condition.
	 */
	public boolean removeRoom(String name) {
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getName().equals(name)) {
				rooms.remove(i);
				roomCount -= 1;
				return true;
			}
		}

		return false;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
}