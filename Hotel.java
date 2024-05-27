import java.util.ArrayList;

/**
 * The Hotel class defines a hotel with a name, an array of rooms, and a base room price.
 */
public class Hotel {
	/**
	 * Name of the hotel.
	 */
	private String name;

	/**
	 * List of rooms in the hotel.
	 * Constraints: Must be between 1 - 50.
	 */
	private ArrayList<Room> rooms = new ArrayList<Room>();

	private ArrayList<Room> reservations = new ArrayList<Room>();

	/**
	 * How much a room costs per night.
	 * All rooms in the hotel cost the same.
	 * Constraints: Must be >= 100.
	 */
	private float basePrice;

	private int roomCount;

	/**
	 * Constructor that creates an empty hotel with only its name.
	 * @param name Name of hotel.
	 * @param roomCount How many rooms are in the hotel.
	 */
	public Hotel(String name, int roomCount, float basePrice) {
		this.name = name;
		this.roomCount = roomCount;
		this.basePrice = basePrice;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public Room getRoom(String inputRoomName) {
		Room blankRoom = new Room();

		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getName() == inputRoomName) {
				return rooms.get(i);
			}
		}

		return blankRoom;
	}

	public int getRoomCount() {
		return roomCount;
	}

	public int getReservationCount() {
		return reservations.size();
	}

	public float getBasePrice() {
		return basePrice;
	}

	public boolean addRoom(Room room) {
		if (rooms.size() < roomCount) {
			rooms.add(room);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Initialize all rooms in the hotel's list as blank rooms.
	 */
	public void initializeRooms() {
		for (int i = 0; i < roomCount; i++) {
			rooms.set(i, new Room(String.format("%03d", i + 1)));
		}
	}
}