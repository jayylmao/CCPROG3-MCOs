/**
 * The Reservation class defines a reservation and its corresponding details.
 */
public class Reservation {
	/** The first name of the guest. */
	private String firstName;

	/** The last name of the guest. */
	private String lastName;

	/** The check-in date. */
	private Date checkIn;

	/** The check-out date. */
	private Date checkOut;

	/** The price reserved by the guest at the time of booking. */
	private double reservedPrice;

	public String getFirstName() {
		return firstName;
	}

	public String getName() {
		return firstName + " " + lastName;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setCheckIn(int month, int date, int year) {
		checkIn.setMonth(month);
		checkIn.setDate(date);
		checkIn.setYear(year);
	}

	public void setCheckOut(int month, int date, int year) {
		checkOut.setMonth(month);
		checkOut.setDate(date);
		checkOut.setYear(year);
	}
}
