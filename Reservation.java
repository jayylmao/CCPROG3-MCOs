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

	/** The list of Customers that have reserved a room (it may be a group of people that want to reserve a room together) */
	private ArrayList<Customer> customers = new ArrayList<Customer>();;

	public Reservation(Date checkIn, Date checkOut, double reservedPrice, Customer customer) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.reservedPrice = reservedPrice;
		this.customers.add(customer);
	}

	public Reservation(Date checkIn, Date checkOut, double reservedPrice, ArrayList<Customer> customerList) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.reservedPrice = reservedPrice;
		
		for(int i = 0; i < customerList.size(); i++) {
			this.customers.add(customerList.get(i));
		}
	}

	public void addCustomer(Customer customer) {
		this.customers.add(customer);
	}

	public boolean removeCustomer(String first, String last) {
		for(int i = 0; i < this.customers.size(); i++) {
			if(this.customers.get(i).getFirstName().equals(first) && this.customers.get(i).getLastName().equals(last)) {
				this.customers.remove(i);
				return true;
			}
		}
		return false;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
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
