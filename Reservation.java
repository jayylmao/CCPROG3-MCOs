import java.util.ArrayList;
/**
 * The Reservation class defines a reservation and its corresponding details.
 */
public class Reservation {
	/** The check-in date. */
	private Date checkIn;

	/** The check-out date. */
	private Date checkOut;

	/** The price reserved by the guest at the time of booking. */
	private double reservedPrice;

	/** The total price for the booking. */
	private double totalPrice;

	/** The list of Guests that have reserved a room (it may be a group of people that want to reserve a room together) */
	private ArrayList<Guest> guests = new ArrayList<Guest>();;

	public Reservation(Date checkIn, Date checkOut, double reservedPrice, Guest guest) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.reservedPrice = reservedPrice;
		this.guests.add(guest);
		this.totalPrice = calculateTotalPrice(checkIn, checkOut);
	}

	public Reservation(Date checkIn, Date checkOut, double reservedPrice, ArrayList<Guest> guestList) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.reservedPrice = reservedPrice;
		
		for(int i = 0; i < guestList.size(); i++) {
			this.guests.add(guestList.get(i));
		}
		this.totalPrice = calculateTotalPrice(checkIn, checkOut);
	}

	public double calculateTotalPrice(Date checkIn, Date checkOut) {
		if(checkOut.getDay() == checkIn.getDay()) {
			return this.reservedPrice;
		}
		return this.reservedPrice * (checkOut.getDay() - checkIn.getDay());
	}

	public void addGuest(Guest guest) {
		this.guests.add(guest);
	}

	public boolean removeGuest(String first, String last) {
		for(int i = 0; i < this.guests.size(); i++) {
			if(this.guests.get(i).getFirstName().equals(first) && this.guests.get(i).getLastName().equals(last)) {
				this.guests.remove(i);
				return true;
			}
		}
		return false;
	}

	public void setReservedPrice(double reservedPrice) {
		this.reservedPrice = reservedPrice;
		this.totalPrice = calculateTotalPrice(this.checkIn, this.checkOut);
	}

	public double getReservedPrice() {
		return this.reservedPrice;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public ArrayList<Guest> getGuests() {
		return guests;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckIn(int month, int date, int year) {
		checkIn.setMonth(month);
		checkIn.setDay(date);
		checkIn.setYear(year);
	}

	public void setCheckOut(int month, int date, int year) {
		checkOut.setMonth(month);
		checkOut.setDay(date);
		checkOut.setYear(year);
	}
}
