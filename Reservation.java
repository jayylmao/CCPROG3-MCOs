import java.util.ArrayList;
import java.util.HashMap;
/**
 * The Reservation class defines a reservation and its corresponding details.
 */
public class Reservation {
	/** The check-in date. */
	private Date checkIn;

	/** The check-out date. */
	private Date checkOut;

	/** The price per night reserved by the guest at the time of booking. */
	private double reservedPrice;

	/** The total price for the booking. */
	private double totalPrice;

	/** The list of Guests that have reserved a room (it may be a group of people that want to reserve a room together) */
	private ArrayList<Guest> guests;

	private HashMap<Integer, Double> datePriceModifier;

	/**
	 * Constructor that creates a Reservation instance.
	 * @param checkIn Date object describing the check in time.
	 * @param checkOut Date object describing the check out time.
	 * @param reservedPrice Price of the reservation per night.
	 * @param guest Guest object that is reserving the room.
	 */
	public Reservation(Date checkIn, Date checkOut, double reservedPrice, Guest guest, String discountCode, HashMap<Integer, Double> datePriceModifier) throws InvalidDiscountCodeException {
		if (checkOut.isBefore(checkIn)) {
			throw new IllegalArgumentException("Check-out date must be later than check-in date.");
		} else {
			guests = new ArrayList<Guest>();
			this.checkIn = checkIn;
			this.checkOut = checkOut;
			this.reservedPrice = reservedPrice;
			this.guests.add(guest);
			this.totalPrice = calculateTotalPrice(checkIn, checkOut, discountCode);
			this.datePriceModifier = datePriceModifier;
		}
	}

	/**
	 * Calculates the total price of the booking
	 * @param checkIn Date object describing the check in time.
	 * @param checkOut Date object describing the check out time.
	 * @return Total price of the booking.
	 */
	public double calculateTotalPrice(Date checkIn, Date checkOut, String discountCode) throws InvalidDiscountCodeException {
		// double reservePrice = this.reservedPrice * checkIn.getDayDifference(checkOut);
		double reservePrice = 0;
		Date payday1;
		Date payday2;
		
		if (checkOut.getDay() == checkIn.getDay() || checkOut.isBefore(checkIn)) {
			throw new IllegalArgumentException("Check-out date must be later than check-in date.");
		}
		
		for(int i = 0; i < checkIn.getDayDifference(checkOut); i++) {
			reservePrice += this.reservedPrice * datePriceModifier.get(i+1);
		}

		try {
			payday1 = new Date(15);
			payday2 = new Date(30);

			switch (discountCode) {
			case "I_WORK_HERE":
				reservePrice -= reservePrice * 0.1;
				break;
			case "STAY4_GET1":
				if (checkIn.getDayDifference(checkOut) >= 5) {
					reservePrice -= this.reservedPrice;
				}
				break;
			case "PAYDAY":
				if (payday1.isBetween(checkIn, checkOut) || payday2.isBetween(checkIn, checkOut)) {
					reservePrice -= reservePrice * 0.07;
				}
				break;
			case "":
				break;
			default:
				throw new InvalidDiscountCodeException();
			}
		} catch (IllegalDateException e) {
			System.out.println("Invalid date.");
		}

		return reservePrice;
	}

	/**
	 * Adds a guest to the Reservation instance via the guestList.
	 * @param guest Guest object to be added in the reservation.
	 */
	public void addGuest(Guest guest) {
		this.guests.add(guest);
	}

	/**
	 * Attempts to remove a guest from the reservation by their names.
	 * @param first firstName String key that the Guest list will try to find and remove.
	 * @param last lastName String key that the Guest list will try to find and remove.
	 * @return True if guest was successfully removed from the list, false if there is no match.
	 */
	public boolean removeGuest(String first, String last) {
		for(int i = 0; i < this.guests.size(); i++) {
			if(this.guests.get(i).getFirstName().equals(first) && this.guests.get(i).getLastName().equals(last)) {
				this.guests.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the price per night.
	 * @return Price per night.
	 */
	public double getReservedPrice() {
		return this.reservedPrice;
	}

	/**
	 * Gets the total price for the booking.
	 * @return Total price.
	 */
	public double getTotalPrice() {
		return this.totalPrice;
	}

	/**
	 * Gets the total price as a formatted String (real number with 2 decimal places) for the Reservation.
	 * @return Total price as a String.
	 */
	public String getFormattedTotalPrice() {
		return String.format("%.2f", this.totalPrice);
	}

	/**
	 * Gets the guest list that is linked to the reservation.
	 * @return Guest list as an ArrayList.
	 */
	public ArrayList<Guest> getGuests() {
		return guests;
	}

	/**
	 * Gets the check in time.
	 * @return Date object representing the check in time.
	 */
	public Date getCheckIn() {
		return checkIn;
	}

	/**
	 * Gets the check out time.
	 * @return Date object representing the check out time.
	 */
	public Date getCheckOut() {
		return checkOut;
	}
}
