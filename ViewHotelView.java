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
	private JLabel roomCount;
	private JLabel estimateEarnings;

	private JPanel lowLevelInfoWrapper;

	private JLabel availableRoomsHeader;
	private JPanel availableRoomsInputWrapper;
	private JTextField availableRoomsInput;
	private JButton availableRoomsButton;
	private JLabel availableRoomsResult;
	
	private JLabel reservationInfoHeader;
	private JPanel reservationInfoInputWrapper;
	private JTextField reservationInfoInput;
	private JButton reservationInfoButton;

	private JLabel roomInfoHeader;
	private JPanel roomInfoInputWrapper;
	private JTextField roomInfoInput;
	private JButton roomInfoButton;
	private JLabel roomInfoResult;

	private Hotel currentHotel;

	public ViewHotelView() {
		currentHotel = null;

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
		
		roomCount = new JLabel("Number of rooms");
		roomCount.setAlignmentX(LEFT_ALIGNMENT);
		outputWrapper.add(roomCount);

		outputWrapper.add(Box.createRigidArea(new Dimension(0, 20)));

		estimateEarnings = new JLabel();
		estimateEarnings.setAlignmentX(LEFT_ALIGNMENT);
		outputWrapper.add(estimateEarnings);

		outputWrapper.setVisible(false);

		setupLowLevelView();

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
	 * Sets up the components needed to view a hotel's low-level information.
	 */
	private void setupLowLevelView() {
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

		availableRoomsResult = new JLabel();
		availableRoomsInputWrapper.add(availableRoomsResult);

		lowLevelInfoWrapper.add(availableRoomsInputWrapper);
		
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

		roomInfoResult = new JLabel();
		roomInfoInputWrapper.add(roomInfoResult);

		// Add wrapper for checking reservation info.
		reservationInfoInputWrapper = new JPanel();
		reservationInfoInputWrapper.setBackground(UI.BG_MAIN);
		reservationInfoInputWrapper.setAlignmentX(LEFT_ALIGNMENT);

		reservationInfoHeader = new JLabel("Check reservation info");
		reservationInfoHeader.setAlignmentX(LEFT_ALIGNMENT);
		reservationInfoInputWrapper.add(reservationInfoHeader);
		
		lowLevelInfoWrapper.add(roomInfoInputWrapper);
		lowLevelInfoWrapper.add(reservationInfoInputWrapper);
		
		lowLevelInfoWrapper.setVisible(false);
	}

	/**
	 * Returns the input field where search queries are entered by the user.
	 * @return Input text field for search queries.
	 */
	public JTextField getSearchInput() {
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
	 * Returns the input field where a date is entered by the user 
	 * to check what rooms are available on that date.
	 * @return Input field for date.
	 */
	public JTextField getCheckAvailableRoomsInput() {
		return availableRoomsInput;
	}

	/**
	 * Returns the button that checks for the available rooms on a date
	 * specified by the user.
	 * @return Button for checking available rooms on a date.
	 */
	public JButton getCheckAvailableRoomsButton() {
		return availableRoomsButton;
	}

	/**
	 * Updates the available rooms label with the given value.
	 * @param availableRooms Value to set available rooms part of label to.
	 * @param bookedRooms Value to set booked rooms part of label to.
	 */
	public void setAvailableRooms(int availableRooms, int bookedRooms) {
		availableRoomsResult.setText("Available rooms: " + String.valueOf(availableRooms) + "Booked rooms: " + String.valueOf(bookedRooms));
	}

	/**
	 * Returns the input field where a room name is entered by the user
	 * to check when the room is available throughout the month.
	 * @return Input field for a room name.
	 */
	public JTextField getCheckRoomsAvailabilityInput() {
		return reservationInfoInput;
	}

	/**
	 * Returns the button that checks for a room's availability throughout
	 * the month.
	 * @return Button for checking a room's availability on a date.
	 */
	public JButton getCheckRoomsAvailabilityButton() {
		return reservationInfoButton;
	}

	/**
	 * Updates the label with the dates when the room is available.
	 */
	public void setRoomAvailability() {
		availableRoomsResult.setText("");
	}

	/**
	 * Returns the input field where a room name is entered by the user
	 * to check the room's information.
	 * @return Input field for a room name.
	 */
	public JTextField getCheckRoomInfoInput() {
		return roomInfoInput;
	}

	/**
	 * Returns the button that checks for a room's info.
	 * @return Button for checking a room's info.
	 */
	public JButton getCheckRoomInfoButton() {
		return roomInfoButton;
	}

	/**
	 * Returns the hotel whose information is being viewed in the interface.
	 * @return Hotel instance being viewed in the interface.
	 */
	public Hotel getCurrentHotel() {
		return currentHotel;
	}

	/**
	 * Updates the output labels with the corresponding values upon
	 * receiving data from the model.
	 * @param hotel Hotel to display information of.
	 */
	public void showResult(Hotel hotel) {
		currentHotel = hotel;

		hotelName.setText(hotel.getName());

		roomCount.setText("Rooms in hotel: " + String.valueOf(hotel.getRoomCount()));
		estimateEarnings.setText("Estimate earnings: " + String.valueOf(String.format("%.2f", hotel.getTotalEarnings())));

		outputWrapper.setVisible(true);
		lowLevelInfoWrapper.setVisible(true);
	}

	/**
	 * Shows an error dialog when a user enters an invalid search query.
	 */
	public void showError(String message) {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
