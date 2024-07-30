import javax.swing.JFrame;

/**
 * Main is the driver class of the program.
 */
public class Main {
	/**
	 * Main function that runs the program
	 * @param args .
	 */
	public static void main(String[] args) {
		JFrame mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("Hotel Reservation System");
		mainFrame.setSize(970, 900);
		
		MainView view = new MainView();
		HotelReservationSystem rSystem = new HotelReservationSystem();

		MainController controller = new MainController(view, rSystem);

		mainFrame.add(view);
		mainFrame.setVisible(true);
	}
}
