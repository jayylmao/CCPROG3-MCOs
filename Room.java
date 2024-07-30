import java.util.ArrayList;
import java.util.HashMap;
/**
 * The Room class defines a Room in a hotel, with its corresponding details.
 */
public abstract class Room {
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
	 * The actual price of the room. Some room types differ.
	 */
	private double roomPrice;

	/**
	 * Regular Constructor that creates a Room instance.
	 * @param roomName The name of the Room. Should be unique compared to other Room instances.
	 * @param roomPrice Base price for a room.
	 */
	public Room(String roomName, double roomPrice) {
		this.name = roomName;
		this.occupied = false;
		this.reservations = new ArrayList<Reservation>();
		this.roomPrice = roomPrice;
	}

	/**
	 * Gets the name of the Room.
	 * @return Name of the Room.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the price of the Room.
	 * @return Price of the Room.
	 */
	public double getRoomPrice() {
		return roomPrice;
	}

	/**
	 * Gets the list of reservations for the Room.
	 * @return List of reservations for the Room.
	 */
	public ArrayList<Reservation> getReservations() {
		return reservations;
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
	 * Sets the price of the Room.
	 * @param roomPrice Price to set Room to.
	 */
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}

	/**
	 * Creates a Reservation instance and stores it in a list of reservations, given that it does not conflict with any prior reservations.
	 * @param guest Guest object that is reserving the room.
	 * @param checkIn Date object describing the check in time.
	 * @param checkOut Date object describing the check out time.
	 * @param reservedPrice Price of the reservation per night.
	 * @param discountCode String that represents the discount code.
	 * @param datePriceModifier Date price modifier hashmap that determines how much more or less a single day will cost compared to the base price.
	 */
	public void reserveRoom(Guest guest, Date checkIn, Date checkOut, double reservedPrice, String discountCode, HashMap<Integer, Double> datePriceModifier) throws InvalidCheckInDateException, RoomIsOccupiedException, InvalidDiscountCodeException {
		if (!occupied && !checkIn.isAfter(checkOut)) {
			this.reservations.add(new Reservation(checkIn, checkOut, reservedPrice, guest, discountCode, datePriceModifier));
			this.setOccupationState(true);
		} else if (checkIn.isAfter(checkOut)) {
			throw new InvalidCheckInDateException();
		}
		else if (occupied) {
			// Check for overlap between checkIn and checkOut times
			if (isOccupied(checkIn, checkOut)) {
				throw new RoomIsOccupiedException();
			}
			// If it passes the list without returning false, it for sure does not overlap with other reservations.
			this.reservations.add(new Reservation(checkIn, checkOut, reservedPrice, guest, discountCode, datePriceModifier));
		}
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
	 * Gets a Reservation instance based on the name of the Guest who
	 * made the Reservation.
	 * @param name Name of the Guest who made the Reservation.
	 * @return Reservation instance. Returns null if no Reservation is found.
	 */
	public Reservation getReservation(String name) {
		for (Reservation reservation : reservations) {
			if (reservation.getGuests().get(0).getName().equals(name)) {
				return reservation;
			}
		}

		return null;
	}

	/**
	 * Removes a Reservation instance using an index.
	 * @param n Index of reservation to be removed.
	 * @throws ReservationNotFoundException Exception when a reservation is not found.
	 */
	public void removeReservation(int n) throws ReservationNotFoundException {
		if (n < this.reservations.size() && n >= 0) {
			this.reservations.remove(n);
			if(this.reservations.size() == 0) {
				setOccupationState(false);
			}
		}
		else {
			throw new ReservationNotFoundException();
		}
	}

	/**
	 * Removes a Reservation instance using the name of the first guest.
	 * @param reservationName Name of the guest who made the reservation.
	 * @throws ReservationNotFoundException Exception when a Reservation instance with that name was not found.
	 */
	public void removeReservation(String reservationName) throws ReservationNotFoundException {
		for (Reservation reservation : reservations) {
			if (reservation.getGuests().get(0).getName().equals(reservationName)) {
				this.reservations.remove(reservation);

				if (this.reservations.size() == 0) {
					setOccupationState(false);
				}

				return;
			}
		}

		throw new ReservationNotFoundException();
	}
}
