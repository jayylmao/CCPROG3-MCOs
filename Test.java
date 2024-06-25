public class Test {
	public static void main(String[] args) {
		Reservation reservation = new Reservation(new Date(1), new Date(3), 1299, new Guest("Jay", "Carlos"));

		System.out.println(reservation.getCheckIn().getFormattedDate());
		System.out.println(reservation.getCheckOut().getFormattedDate());
		System.out.println(reservation.getReservedPrice());
		System.out.println(reservation.getGuests().get(0).getName());
	}
}
