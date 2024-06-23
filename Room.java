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
	private boolean occupied = false;

	/**
	 * We have a couple options for how to design this. Should we:
	 * a.) Put the reservation instance in the room instance when a reservation is made
	 * b.) Have a string roomName in each reservation instance that gets updated when a reservation is made
	 * c.) Do both
	 * ?
	 * ans: We should probably do option a. This is since a reservation would be useless if a room gets destroyed, so it's easier to let the Reservation class
	 * 		be a composition of the Room class. A customer should just make a new reservation if such a case happens
	 */
	private ArrayList<Reservation> reservations;

	public Room() {
		name = "";
	}

	public Room(String roomName) {
		name = roomName;
		occupied = false;
		this.reservations = new ArrayList<Reservation>();
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

	public boolean reserveRoom(Customer customer, Date checkIn, Date checkOut, double reservedPrice) {
		if(!occupied) {
			this.reservations.add(new Reservation(checkIn, checkOut, reservedPrice, customer));
			return true;
		}
		else if(occupied) {
			for(int i = 0; i < this.reservations.size(); i++) {
				if(checkIn.isBefore(this.reservations.get(i).getCheckOut()) && checkIn.isAfter(this.reservations.get(i).getCheckIn())) {
					return false;
				}
				else if(checkOut.isBefore(this.reservations.get(i).getCheckOut()) && checkOut.isAfter(this.reservations.get(i).getCheckIn())) {
					return false;
				}
			}
			this.reservations.add(new Reservation(checkIn, checkOut, reservedPrice, customer));
		}
		return true;
	}

	public boolean reserveRoom(ArrayList<Customer> customers, Date checkIn, Date checkOut, double reservedPrice) {
		if(!occupied) {
			this.reservations.add(new Reservation(checkIn, checkOut, reservedPrice, customers));
			return true;
		}
		else if(occupied) {
			for(int i = 0; i < this.reservations.size(); i++) {
				if(checkIn.isBefore(this.reservations.get(i).getCheckOut()) && checkIn.isAfter(this.reservations.get(i).getCheckIn())) {
					return false;
				}
				else if(checkOut.isBefore(this.reservations.get(i).getCheckOut()) && checkOut.isAfter(this.reservations.get(i).getCheckIn())) {
					return false;
				}
			}
			this.reservations.add(new Reservation(checkIn, checkOut, reservedPrice, customers));
		}
		return true;
	}

	public ArrayList<Reservation> getReservations() {
		return this.reservations;
	}
}
