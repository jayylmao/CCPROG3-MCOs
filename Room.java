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
 	 * A flag that checks if the Room is occupied or not.
	 */
	private boolean occupied;

	/**
	 * The list of reservations for the room.
	 */
	private ArrayList<Reservation> reservations;

	/**
	 * Constructor that creates a blank Room instance. Used for determining whether or not a room was found when searching by name.
	 */
	public Room() {
		name = "";
	}

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
		return reservations.size();
	}

	/**
	 * Checks if the room is occupied, which is another way of saying if it is already booked.
	 * @return True if the room is occupied/booked. False otherwise.
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * isOccupied() checks if the room is occupied on a given date.
	 * @param date Given date to check for occupancy status.
	 * @return True if the given date has an existing booking. False otherwise.
	 */
	public boolean isOccupied(Date date) {
		for (int i = 0; i < reservations.size(); i++) {
			if (date.isBetween(reservations.get(i).getCheckIn(), reservations.get(i).getCheckOut())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Sets the name of the Room. It is assumed that the new name will be unique compared to the other room objects prior to calling this function.
	 * @param name New name for the room.
	 */
	public void setName(String name) {
		this.name = name;
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
		if(!occupied && !checkIn.isAfter(checkOut)) {
			this.reservations.add(new Reservation(checkIn, checkOut, reservedPrice, guest));
			this.setOccupationState(true);
			return true;
		}
		else if(checkIn.isAfter(checkOut)) {
			return false;
		}
		else if(occupied) {
			for(int i = 0; i < this.reservations.size(); i++) {
				// Check for overlap between checkIn and checkOut times
				if(checkIn.isBefore(this.reservations.get(i).getCheckOut()) && checkIn.isAfter(this.reservations.get(i).getCheckIn())) {
					return false;
				}
				else if(checkOut.isBefore(this.reservations.get(i).getCheckOut()) && checkOut.isAfter(this.reservations.get(i).getCheckIn())) {
					return false;
				}
			}
			// If it passes the list without returning true, it for sure does not overlap with other reservations
			this.reservations.add(new Reservation(checkIn, checkOut, reservedPrice, guest));
			sortReservations();
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
			for(int i = 0; i < this.reservations.size(); i++) {
				// Check for overlap between checkIn and checkOut times
				if(checkIn.isBefore(this.reservations.get(i).getCheckOut()) && checkIn.isAfter(this.reservations.get(i).getCheckIn())) {
					return false;
				}
				else if(checkOut.isBefore(this.reservations.get(i).getCheckOut()) && checkOut.isAfter(this.reservations.get(i).getCheckIn())) {
					return false;
				}
			}
			this.reservations.add(new Reservation(checkIn, checkOut, reservedPrice, guests));
			sortReservations();
		}
		return true;
	}

	public Reservation getReservation(int i) {
		return reservations.get(i);
	}

	/**
	 * Gets the list of Reservations that the Room has.
	 * @return List of Reservations.
	 */
	public ArrayList<Reservation> getReservations() {
		return this.reservations;
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
		if(n < this.reservations.size() && n >= 0) {
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
