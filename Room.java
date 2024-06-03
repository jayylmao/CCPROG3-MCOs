/**
 * The Room class defines a Room in a hotel, with its corresponding details.
 */
public class Room {
	/**
	 * The room number/name.
	 */
	private String name;

	/**
	 * A flag that checks if the Room is occupied or not.
	 */
	private boolean occupied = false;

	/**
	 * We have a couple options for how to design this. Should we:
	 * a.) Put the reservation instance in the room instance when a reservation is made
	 * b.) Have a string roomName in each reservation instance that gets updated when a reservation is made
	 * c.) Do both
	 * ?
	 */
	private Reservation reservation;

	public Room() {
		name = "";
	}

	public Room(String roomName) {
		name = roomName;
		occupied = false;
	}

	public String getName() {
		return name;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOccupationState(boolean occupied) {
		this.occupied = occupied;
	}
}
