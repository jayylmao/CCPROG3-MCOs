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
	private boolean occupied;

	public String getName() {
		return name;
	}

	public boolean isOccupied() {
		return occupied;
	}
}
