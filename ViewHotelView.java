import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class ViewHotelView extends View {
	private Header header;
	private Text description;
	
	private InputWrapper inputWrapper;
	private JTextField input;
	private JButton searchButton;
	
	private JPanel outputWrapper;
	private Header hotelName;
	private Text roomCount;
	private Text estimateEarnings;

	private JPanel lowLevelInfoWrapper;

	private SubHeader availableRoomsHeader;
	private InputWrapper availableRoomsInputWrapper;
	private JTextField availableRoomsInput;
	private JButton availableRoomsButton;
	private Text availableRoomsResult;
	
	private SubHeader reservationInfoHeader;
	private InputWrapper reservationInfoInputWrapper;
	private JTextField reservationInfoInput;
	private JButton reservationInfoButton;

	private SubHeader roomInfoHeader;
	private InputWrapper roomInfoInputWrapper;
	private JComboBox<String> roomInfoInput;
	private JButton roomInfoButton;
	private JPanel roomInfoOutputWrapper;
	private SubHeader roomInfoResult;
	private Calendar roomInfoCalendar;

	private Hotel currentHotel;

	public ViewHotelView() {
		currentHotel = null;

		header = new Header("View hotel");

		description = new Text("Enter the name of a hotel registered in the system to search for it.");
		
		// Add controls for user input.
		inputWrapper = new InputWrapper();

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

		hotelName = new Header();
		outputWrapper.add(hotelName);

		outputWrapper.add(Box.createRigidArea(new Dimension(20, 20)));
		
		roomCount = new Text("Number of rooms");
		outputWrapper.add(roomCount);

		outputWrapper.add(Box.createRigidArea(new Dimension(0, 20)));

		estimateEarnings = new Text();
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
		availableRoomsInputWrapper = new InputWrapper();
		
		availableRoomsHeader = new SubHeader("Check available rooms on date");
		availableRoomsInputWrapper.add(availableRoomsHeader);
		
		availableRoomsInput = new JTextField();
		availableRoomsInput.setPreferredSize(new Dimension(30, 30));
		availableRoomsInputWrapper.add(availableRoomsInput);

		availableRoomsButton = new JButton("Check available rooms");
		availableRoomsInputWrapper.add(availableRoomsButton);

		availableRoomsResult = new Text();
		availableRoomsInputWrapper.add(availableRoomsResult);

		// Add wrapper for getting room info and its components.
		roomInfoInputWrapper = new InputWrapper();
		
		roomInfoHeader = new SubHeader("Get information on room");
		roomInfoInputWrapper.add(roomInfoHeader);
		
		roomInfoInput = new JComboBox<String>();
		roomInfoInput.addItem("Select a room");
		roomInfoInput.setPreferredSize(new Dimension(150, 30));
		roomInfoInputWrapper.add(roomInfoInput);
		
		roomInfoButton = new JButton("Get room info");
		roomInfoInputWrapper.add(roomInfoButton);

		// Add wrapper for displaying room info.
		roomInfoOutputWrapper = new JPanel();
		roomInfoOutputWrapper.setLayout(new BoxLayout(roomInfoOutputWrapper, BoxLayout.Y_AXIS));
		roomInfoOutputWrapper.setBackground(UI.BG_MAIN);
		
		roomInfoResult = new SubHeader();
		roomInfoOutputWrapper.add(roomInfoResult);

		roomInfoCalendar = new Calendar();
		roomInfoOutputWrapper.add(roomInfoCalendar);
		
		roomInfoOutputWrapper.setVisible(false);
		
		// Add wrapper for checking reservation info.
		reservationInfoInputWrapper = new InputWrapper();
		
		reservationInfoHeader = new SubHeader("Check reservation info");
		reservationInfoInputWrapper.add(reservationInfoHeader);
		
		lowLevelInfoWrapper.add(availableRoomsInputWrapper);
		lowLevelInfoWrapper.add(roomInfoInputWrapper);
		lowLevelInfoWrapper.add(roomInfoOutputWrapper);
		lowLevelInfoWrapper.add(reservationInfoInputWrapper);

		lowLevelInfoWrapper.setVisible(false);
	}

	/**
	 * Hides the output container for hiding current information
	 * when a hotel is deleted.
	 */
	public void hideOutput() {
		outputWrapper.setVisible(false);
		lowLevelInfoWrapper.setVisible(false);
		roomInfoCalendar.setVisible(false);
		roomInfoResult.setVisible(false);

		input.setText("");
		availableRoomsInput.setText("");
		roomInfoInput.removeAllItems();
		roomInfoInput.addItem("Select a room");
		// reservationInfoInput.setText("");
		roomInfoInput.setSelectedItem("Select a room");
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
		availableRoomsResult.setText("Available rooms: " + String.valueOf(availableRooms) + " Booked rooms: " + String.valueOf(bookedRooms));
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
	public JComboBox<String> getCheckRoomInfoInput() {
		return roomInfoInput;
	}

	/**
	 * Marks the room information as being visible.
	 */
	public void showRoomInfo() {
		roomInfoOutputWrapper.setVisible(true);
		roomInfoCalendar.setVisible(true);
		roomInfoResult.setVisible(true);
	}

	/**
	 * Returns the button that checks for a room's info.
	 * @return Button for checking a room's info.
	 */
	public JButton getCheckRoomInfoButton() {
		return roomInfoButton;
	}

	public Calendar getRoomInfoCalendar() {
		return roomInfoCalendar;
	}

	/**
	 * Sets the label for the room name under the "Get room info" section.
	 * @param roomName Name to update label with.
	 */
	public void setRoomInfoName(String roomName) {
		roomInfoResult.setText(roomName);
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
		hideOutput();
		hotelName.setText(hotel.getName());

		roomCount.setText("Rooms in hotel: " + String.valueOf(hotel.getRoomCount()));
		estimateEarnings.setText("Estimate earnings: " + String.valueOf(String.format("%.2f", hotel.getTotalEarnings())));

		outputWrapper.setVisible(true);
		for(int i = 0; i < hotel.getRoomCount(); i++) {
			roomInfoInput.addItem(hotel.getRoom(i).getName());
		}

		lowLevelInfoWrapper.setVisible(true);
	}
}
