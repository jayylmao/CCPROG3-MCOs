import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ViewHotelView extends JPanel {
	private JLabel header;
	private JLabel description;
	
	private JPanel inputWrapper;
	private JTextField input;
	private JButton searchButton;
	
	private JPanel outputWrapper;
	private JLabel hotelName;
	private JLabel roomCountHeader;
	private JLabel roomCount;
	private JLabel estimateEarningsHeader;
	private JLabel estimateEarnings;

	private JPanel lowLevelInfoWrapper;
	private JLabel availableRoomsHeader;
	private JPanel availableRoomsInputWrapper;
	private JTextField availableRoomsInput;
	private JButton availableRoomsButton;
	
	private JLabel roomsAvailabilityHeader;
	private JPanel roomsAvailabilityInputWrapper;
	private JTextField roomsAvailabilityInput;
	private JButton roomsAvailabilityButton;

	private JLabel roomInfoHeader;
	private JPanel roomInfoInputWrapper;
	private JTextField roomInfoInput;
	private JButton roomInfoButton;

	public ViewHotelView() {
		header = new JLabel("View hotel");
		header.setFont(UI.HEADER_FONT);
		header.setAlignmentX(LEFT_ALIGNMENT);

		description = new JLabel("Enter the name of a hotel registered in the system to search for it.");
		description.setFont(UI.TEXT_FONT);
		description.setAlignmentX(LEFT_ALIGNMENT);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(UI.BG_MAIN);
		
		// Add controls for user input.
		inputWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
		inputWrapper.setAlignmentX(LEFT_ALIGNMENT);

		input = new JTextField();
		input.setPreferredSize(new Dimension(400, 30));
		inputWrapper.add(input);
		inputWrapper.setBackground(UI.BG_MAIN);
		
		searchButton = new JButton("Search for hotel");
		searchButton.setFont(UI.BUTTON_FONT);
		inputWrapper.add(searchButton);

		// Add labels for displaying output.
		outputWrapper = new JPanel();
		outputWrapper.setBackground(UI.BG_MAIN);
		outputWrapper.setAlignmentX(LEFT_ALIGNMENT);
		outputWrapper.setLayout(new BoxLayout(outputWrapper, BoxLayout.Y_AXIS));

		hotelName = new JLabel();
		hotelName.setFont(UI.HEADER_FONT);
		hotelName.setAlignmentX(LEFT_ALIGNMENT);
		outputWrapper.add(hotelName);

		outputWrapper.add(Box.createRigidArea(new Dimension(20, 20)));

		roomCountHeader = new JLabel();
		roomCountHeader.setAlignmentX(LEFT_ALIGNMENT);
		outputWrapper.add(roomCountHeader);
		
		roomCount = new JLabel("Number of rooms");
		roomCount.setAlignmentX(LEFT_ALIGNMENT);
		outputWrapper.add(roomCount);

		outputWrapper.add(Box.createRigidArea(new Dimension(0, 20)));

		estimateEarningsHeader = new JLabel("Estimate earnings");
		estimateEarningsHeader.setAlignmentX(LEFT_ALIGNMENT);
		outputWrapper.add(estimateEarningsHeader);

		estimateEarnings = new JLabel();
		estimateEarnings.setAlignmentX(LEFT_ALIGNMENT);
		outputWrapper.add(estimateEarnings);

		outputWrapper.setVisible(false);

		// Add low level info.
		lowLevelInfoWrapper = new JPanel();
		lowLevelInfoWrapper.setBackground(UI.BG_MAIN);
		lowLevelInfoWrapper.setAlignmentX(LEFT_ALIGNMENT);
		lowLevelInfoWrapper.setLayout(new BoxLayout(lowLevelInfoWrapper, BoxLayout.Y_AXIS));

		// Add wrapper for checking available rooms on given date and its components.
		availableRoomsInputWrapper = new JPanel();
		availableRoomsInputWrapper.setBackground(UI.BG_MAIN);
		availableRoomsInputWrapper.setAlignmentX(LEFT_ALIGNMENT);
		
		availableRoomsHeader = new JLabel("Check available rooms on date");
		availableRoomsHeader.setAlignmentX(LEFT_ALIGNMENT);
		availableRoomsInputWrapper.add(availableRoomsHeader);
		
		availableRoomsInput = new JTextField();
		availableRoomsInput.setPreferredSize(new Dimension(30, 30));
		availableRoomsInputWrapper.add(availableRoomsInput);

		availableRoomsButton = new JButton("Check available rooms");
		availableRoomsInputWrapper.add(availableRoomsButton);

		lowLevelInfoWrapper.add(availableRoomsInputWrapper);
		
		// Add wrapper for checking specific room availability on all dates and its components.
		roomsAvailabilityInputWrapper = new JPanel();
		roomsAvailabilityInputWrapper.setBackground(UI.BG_MAIN);
		roomsAvailabilityInputWrapper.setAlignmentX(LEFT_ALIGNMENT);

		roomsAvailabilityHeader = new JLabel("Check room availability on date");
		roomsAvailabilityHeader.setAlignmentX(LEFT_ALIGNMENT);
		roomsAvailabilityInputWrapper.add(roomsAvailabilityHeader);

		roomsAvailabilityInput = new JTextField();
		roomsAvailabilityInput.setPreferredSize(new Dimension(30, 30));
		roomsAvailabilityInputWrapper.add(roomsAvailabilityInput);

		roomsAvailabilityButton = new JButton("Check room availability");
		roomsAvailabilityInputWrapper.add(roomsAvailabilityButton);
		
		lowLevelInfoWrapper.add(roomsAvailabilityInputWrapper);
		
		// Add wrapper for getting room info and its components.
		roomInfoInputWrapper = new JPanel();
		roomInfoInputWrapper.setBackground(UI.BG_MAIN);
		roomInfoInputWrapper.setAlignmentX(LEFT_ALIGNMENT);
		
		roomInfoHeader = new JLabel("Get information on room");
		roomInfoHeader.setAlignmentX(LEFT_ALIGNMENT);
		roomInfoInputWrapper.add(roomInfoHeader);
		
		roomInfoInput = new JTextField();
		roomInfoInput.setPreferredSize(new Dimension(60, 30));
		roomInfoInputWrapper.add(roomInfoInput);
		
		roomInfoButton = new JButton("Get room info");
		roomInfoInputWrapper.add(roomInfoButton);

		lowLevelInfoWrapper.add(roomInfoInputWrapper);
		
		lowLevelInfoWrapper.setVisible(false);

		add(Box.createRigidArea(new Dimension(20, 20)));
		add(header);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(description);
		add(Box.createRigidArea(new Dimension(0, 10)));

		add(inputWrapper);
		add(outputWrapper);
		add(lowLevelInfoWrapper);

		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		try {
			hotels.add(new Hotel("test", 1));
		} catch (InvalidRoomCountException e) {
			System.out.println("fail");
		}

		setBackground(UI.BG_MAIN);
	}

	/**
	 * Returns the input field where search queries are entered by the user.
	 * @return Input text field for search queries.
	 */
	public JTextField getInput() {
		return input;
	}

	/**
	 * Returns the button that triggers a search when clicked by the user.
	 * @return Search button for triggering a search.
	 */
	public JButton getSearchButton() {
		return searchButton;
	}

	/**
	 * Updates the output labels with the corresponding values upon
	 * receiving data from the model.
	 * @param hotel Hotel to display information of.
	 */
	public void showResult(Hotel hotel) {
		hotelName.setText(hotel.getName());

		roomCount.setText(String.valueOf(hotel.getRoomCount()));
		estimateEarnings.setText(String.valueOf(String.format("%.2f", hotel.getTotalEarnings())));

		outputWrapper.setVisible(true);
		lowLevelInfoWrapper.setVisible(true);
	}

	/**
	 * Shows an error dialog when a user enters an invalid search query.
	 */
	public void showSearchError() {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(this, "A hotel matching your search query could not be found.", "Error", 2);
	}
}
