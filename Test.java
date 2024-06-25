public class Test {
	public static void main(String[] args) {
		Reservation reservation = new Reservation(new Date(6), new Date(8), 1299, new Guest("Jay", "Carlos"));

		System.out.println(reservation.getCheckIn().getFormattedDate());
		System.out.println(reservation.getCheckOut().getFormattedDate());
		System.out.println(reservation.getReservedPrice());
		System.out.println(reservation.getGuests().get(0).getName());
		System.out.println(reservation.calculateTotalPrice(new Date(6), new Date(4)));
	}
}
