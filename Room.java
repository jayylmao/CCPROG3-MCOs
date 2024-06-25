import java.util.ArrayList;
/**
 * The Room class defines a Room in a hotel, with its corresponding details.
 */
public class Room {
	/**
	 * The room number/name.
	 */
	private String name;

	/**
 	 * A flag that checks if the Room is occupied or not. This is a general flag to see if a Room has any Reservation instance at all throughout the month
	 */
	private boolean occupied;

	/**
	 * The list of reservations for the room.
	 */
	private ArrayList<Reservation> reservations;

	/**
	 * Regular Constructor that creates a Room instance.
	 * @param roomName The name of the Room. Should be unique compared to other Room instances.
	 */
	public Room(String roomName) {
		this.name = roomName;
		this.occupied = false;
		this.reservations = new ArrayList<Reservation>();
	}

	/**
	 * Gets the name of the Room.
	 * @return Name of the Room.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the number of reservations for the room.
	 * @return Number of reservations for the room.
	 */
	public int getReservationCount() {
		return this.reservations.size();
	}

	/**
	 * Checks if the room is occupied at all, which is another way of saying if it has at least 1 booking/reservation.
	 * @return True if the room is occupied/booked. False otherwise.
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * isOccupied() checks if the room is occupied on a given date.
	 * @param checkIn Given check in date to check for occupancy status.
	 * @param checkOut Given check out date to check for occupancy status.
	 * @return True if the given date has an existing booking. False otherwise.
	 */
	public boolean isOccupied(Date checkIn, Date checkOut) {
		for (int i = 0; i < reservations.size(); i++) {
			if (checkIn.isBetween(reservations.get(i).getCheckIn(), reservations.get(i).getCheckOut()) ||
				checkOut.isBetween(reservations.get(i).getCheckIn(), reservations.get(i).getCheckOut()) ||
				reservations.get(i).getCheckIn().isBetween(checkIn, checkOut) ||
				reservations.get(i).getCheckOut().isBetween(checkIn, checkOut)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * isFull() checks if the room is completely booked (from day 1 - 31).
	 * @return True if the room is booked for all days. False otherwise.
	 */
	public boolean isFull() {
		Date checkIn;
		Date checkOut;
		int dayDifference = 0;

		for (int i = 0; i < reservations.size(); i++) {
			checkIn = getReservation(i).getCheckIn();
			checkOut = getReservation(i).getCheckOut();

			dayDifference += checkIn.getDayDifference(checkOut);
		}

		if (dayDifference == 30) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sets the occupation state of the Room. Happens when the reservation list contains Reservation instances.
	 * @param occupied True or false depending on whether or not the room is occupied.
	 */
	public void setOccupationState(boolean occupied) {
		this.occupied = occupied;
	}

	/**
	 * Creates a Reservation instance and stores it in a list of reservations, given that it does not conflict with any prior reservations.
	 * @param guest Guest object that is reserving the room.
	 * @param checkIn Date object describing the check in time.
	 * @param checkOut Date object describing the check out time.
	 * @param reservedPrice Price of the reservation per night.
	 * @return True if a reservation is successful (i.e no overlap with previous reservations or the room is unoccupied). False otherwise.
	 */
	public boolean reserveRoom(Guest guest, Date checkIn, Date checkOut, double reservedPrice) {
		if (!occupied && !checkIn.isAfter(checkOut)) {
			this.reservations.add(new Reservation(checkIn, checkOut, reservedPrice, guest));
			this.setOccupationState(true);
			return true;
		}
		else if (checkIn.isAfter(checkOut)) {
			return false;
		}
		else if (occupied) {
			// Check for overlap between checkIn and checkOut times
			if (isOccupied(checkIn, checkOut)) {
				return false;
			}
			// If it passes the list without returning false, it for sure does not overlap with other reservations.
			this.reservations.add(new Reservation(checkIn, checkOut, reservedPrice, guest));
		}

		return true;
	}

	/**
	 * Secondary function that creates a Reservation instance for a group of guests and stores it in a list of reservations, given that it does not conflict with any prior reservations.
	 * @param guests ArrayList of Guest objects that are reserving the room together.
	 * @param checkIn Date object describing the check in time.
	 * @param checkOut Date object describing the check out time.
	 * @param reservedPrice Price of the reservation per night.
	 * @return True if a reservation is successful (i.e no overlap with previous reservations or the room is unoccupied). False otherwise.
	 */
	public boolean reserveRoom(ArrayList<Guest> guests, Date checkIn, Date checkOut, double reservedPrice) {
		if(!occupied && !checkIn.isAfter(checkOut)) {
			this.reservations.add(new Reservation(checkIn, checkOut, reservedPrice, guests));
			this.setOccupationState(true);
			return true;
		}
		else if(checkIn.isAfter(checkOut)) {
			return false;
		}
		else if(occupied) {
			// Check for overlap between checkIn and checkOut times
			if (isOccupied(checkIn, checkOut)) {
				return false;
			}
			this.reservations.add(new Reservation(checkIn, checkOut, reservedPrice, guests));
		}
		return true;
	}

	/**
	 * Gets a Reservation instance based on an index.
	 * @param i Index of the Reservation instance.
	 * @return Reservation instance. Returns null if index is out of bounds.
	 */
	public Reservation getReservation(int i) {
		if(i < this.reservations.size() && i >= 0) {
			return reservations.get(i);
		}
		else {
			return null;
		}
	}

	/**
	 * Sorts the list of Reservations in chronological order.
	 */
	public void sortReservations() {
		int minIndex;
		Reservation temp;

		for(int i = 0; i < this.reservations.size(); i++) {
			minIndex = i;
			for(int j = i + 1; j < this.reservations.size(); j++) {
				if(this.reservations.get(j).getCheckIn().isBefore(this.reservations.get(minIndex).getCheckIn())) {
					minIndex = j;
				}
			}
			if(minIndex != i) {
				temp = this.reservations.get(minIndex);
				this.reservations.set(minIndex, this.reservations.get(i));
				this.reservations.set(i, temp);
			}
		}
	}

	/**
	 * Removes a Reservation instance using an index.
	 * @param n Index of reservation to be removed.
	 * @return True if a reservation is successfully removed. False otherwise.
	 */
	public boolean removeReservation(int n) {
		if (n < this.reservations.size() && n >= 0) {
			this.reservations.remove(n);
			if(this.reservations.size() == 0) {
				setOccupationState(false);
			}
			return true;
		}
		else {
			return false;
		}
	}
}
