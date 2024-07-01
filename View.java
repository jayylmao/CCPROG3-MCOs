import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;

/**
 * View contains the UI code for the main menu.
 */
public class View {
	JFrame mainFrame;
	UI ui = new UI();

	/**
	 * This constructor initializes the window with defaults,
	 * and calls the main menu method.
	 */
	public View() {
		this.mainFrame = new JFrame("Hotel Reservation System");

		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setSize(600, 900);

		initializeMenu();

		this.mainFrame.setVisible(true);
	}

	private void initializeMenu() {
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(ui.BG_MAIN);
		menuPanel.setPreferredSize(new Dimension(600, 900));
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

		JLabel welcome = new JLabel();
		welcome.setText("Welcome.");
		welcome.setForeground(ui.FG_MAIN);
		welcome.setFont(ui.HEADER_FONT);

		JButton createHotelButton = new JButton();
		createHotelButton.setText("Create Hotel");
		createHotelButton.setForeground(ui.FG_MAIN);
		createHotelButton.setBackground(ui.BUTTON_BG);
		createHotelButton.setBorder(null);

		JButton viewHotelButton = new JButton();
		viewHotelButton.setText("View Hotel");
		viewHotelButton.setForeground(ui.FG_MAIN);
		viewHotelButton.setBackground(ui.BUTTON_BG);
		viewHotelButton.setBorder(null);

		JButton manageHotelButton = new JButton();
		manageHotelButton.setText("Manage Hotel");
		manageHotelButton.setForeground(ui.FG_MAIN);
		manageHotelButton.setBackground(ui.BUTTON_BG);
		manageHotelButton.setBorder(null);

		JButton listHotelsButton = new JButton();
		listHotelsButton.setText("List Hotels");
		listHotelsButton.setForeground(ui.FG_MAIN);
		listHotelsButton.setBackground(ui.BUTTON_BG);
		listHotelsButton.setBorder(null);

		JButton bookRoomButton = new JButton();
		bookRoomButton.setText("Book a Room");
		bookRoomButton.setForeground(ui.FG_MAIN);
		bookRoomButton.setBackground(ui.BUTTON_BG);
		bookRoomButton.setBorder(null);

		menuPanel.add(welcome);
		menuPanel.add(createHotelButton);
		menuPanel.add(viewHotelButton);
		menuPanel.add(manageHotelButton);
		menuPanel.add(listHotelsButton);
		menuPanel.add(bookRoomButton);

		this.mainFrame.add(menuPanel, BorderLayout.CENTER);
	}
}
