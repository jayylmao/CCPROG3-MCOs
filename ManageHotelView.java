import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class ManageHotelView extends View {
	private Hotel currentHotel;
	
	private Header header;
	private JLabel description;
	
	private InputWrapper inputWrapper;
	private JTextField input;
	private JButton searchButton;
	
	private JPanel outputWrapper;

	private InputWrapper changeHotelNameWrapper;
	private SubHeader changeHotelNameLabel;
	private JTextField changeHotelNameInput;
	private JButton changeHotelNameButton;

	private InputWrapper addRoomsWrapper;
	private SubHeader addRoomsLabel;
	private JSpinner addRoomsInput;
	private SpinnerNumberModel roomsInputModel;
	private JButton addRoomsButton;

	private InputWrapper removeRoomsWrapper;
	private SubHeader removeRoomsLabel;
	private JTextField removeRoomsInput;
	private JButton removeRoomsButton;

	private InputWrapper updateBasePriceWrapper;
	private SubHeader updateBasePriceLabel;
	private JTextField updateBasePriceInput;
	private JButton updateBasePriceButton;

	private JButton deleteHotelButton;

	public ManageHotelView() {
		currentHotel = null;
		header = new Header("Manage hotel");
		description = new JLabel("Enter the name of a hotel registered in the system to edit its properties.");
		
		// Add controls for user input.
		inputWrapper = new InputWrapper();

		input = new JTextField();
		input.setPreferredSize(new Dimension(400, 30));
		inputWrapper.add(input);
		inputWrapper.setBackground(UI.BG_MAIN);
		
		searchButton = new JButton("Search for hotel");
		searchButton.setFont(UI.BUTTON_FONT);
		inputWrapper.add(searchButton);

		outputWrapper = new JPanel();
		outputWrapper.setAlignmentX(LEFT_ALIGNMENT);
		outputWrapper.setLayout(new BoxLayout(outputWrapper, BoxLayout.Y_AXIS));

		setupChangeHotelNameView();
		setupAddRoomsView();
		setupRemoveRoomsView();
		setupUpdateBasePriceView();
		setupDeleteHotelButton();

		add(Box.createRigidArea(new Dimension(20, 20)));
		add(header);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(description);
		add(Box.createRigidArea(new Dimension(0, 10)));

		add(inputWrapper);
		add(outputWrapper);
		outputWrapper.setVisible(false);
	}

	/**
	 * Adds the necessary components for changing the hotel name.
	 */
	private void setupChangeHotelNameView() {
		changeHotelNameWrapper = new InputWrapper();

		changeHotelNameLabel = new SubHeader("Change hotel name");

		changeHotelNameInput = new JTextField();
		changeHotelNameInput.setPreferredSize(new Dimension(100, 30));
		changeHotelNameInput.setAlignmentX(LEFT_ALIGNMENT);

		changeHotelNameButton = new JButton("Change hotel name");
		changeHotelNameButton.setAlignmentX(LEFT_ALIGNMENT);

		outputWrapper.add(changeHotelNameWrapper);
		changeHotelNameWrapper.add(changeHotelNameLabel);
		changeHotelNameWrapper.add(changeHotelNameInput);
		changeHotelNameWrapper.add(changeHotelNameButton);
	}

	/**
	 * Adds the necessary components for adding rooms to the hotel.
	 */
	private void setupAddRoomsView() {
		addRoomsWrapper = new InputWrapper();

		addRoomsLabel = new SubHeader("Add rooms");

		roomsInputModel = new SpinnerNumberModel(1, 1, 50, 1);
		addRoomsInput = new JSpinner(roomsInputModel);
		addRoomsInput.setPreferredSize(new Dimension(60, 30));

		addRoomsButton = new JButton("Add rooms");

		outputWrapper.add(addRoomsWrapper);
		addRoomsWrapper.add(addRoomsLabel);
		addRoomsWrapper.add(addRoomsInput);
		addRoomsWrapper.add(addRoomsButton);
	}

	/**
	 * Adds the necessary components for removing rooms for the hotel.
	 */
	private void setupRemoveRoomsView() {
		removeRoomsWrapper = new InputWrapper();

		removeRoomsLabel = new SubHeader("Remove rooms");

		removeRoomsInput = new JTextField();
		removeRoomsInput.setPreferredSize(new Dimension(60, 30));

		removeRoomsButton = new JButton("Remove room");

		outputWrapper.add(removeRoomsWrapper);
		removeRoomsWrapper.add(removeRoomsLabel);
		removeRoomsWrapper.add(removeRoomsInput);
		removeRoomsWrapper.add(removeRoomsButton);
	}

	/**
	 * Adds the necessary components for updating the base price of rooms
	 * in the hotel.
	 */
	private void setupUpdateBasePriceView() {
		updateBasePriceWrapper = new InputWrapper();

		updateBasePriceLabel = new SubHeader("Update base price for rooms");

		updateBasePriceInput = new JTextField();
		updateBasePriceInput.setPreferredSize(new Dimension(60, 30));

		updateBasePriceButton = new JButton("Update base price");

		outputWrapper.add(updateBasePriceWrapper);
		updateBasePriceWrapper.add(updateBasePriceLabel);
		updateBasePriceWrapper.add(updateBasePriceInput);
		updateBasePriceWrapper.add(updateBasePriceButton);
	}

	private void setupDeleteHotelButton() {
		deleteHotelButton = new JButton("Delete hotel");
		deleteHotelButton.setOpaque(true);
		deleteHotelButton.setBorderPainted(false);
		deleteHotelButton.setBackground(UI.ERROR);
		deleteHotelButton.setForeground(UI.BG_MAIN);

		outputWrapper.add(deleteHotelButton);
	}

	/**
	 * Hides the output container for hiding current information
	 * when a hotel is deleted.
	 */
	public void hideOutput() {
		outputWrapper.setVisible(false);
	}

	/**
	 * Returns the button to trigger a hotel name change.
	 * @return Button to trigger hotel name change.
	 */
	public JButton getChangeHotelNameButton() {
		return changeHotelNameButton;
	}

	/**
	 * Returns the input field that lets the user input a new name for the
	 * current hotel.
	 * @return Text field for changing the name of the current hotel.
	 */
	public JTextField getChangeHotelNameInput() {
		return changeHotelNameInput;
	}

	/**
	 * Returns the button to trigger the add room operation for the
	 * current hotel.
	 * @return Button to trigger add room.
	 */
	public JButton getAddRoomsButton() {
		return addRoomsButton;
	}
	
	/**
	 * Returns the spinner for editing the number of rooms in the
	 * current hotel.
	 * @return Spinner to edit number of rooms.
	 */
	public JSpinner getAddRoomsInput() {
		return addRoomsInput;
	}

	/**
	 * Returns the button to trigger the remove room operation for the
	 * current hotel.
	 * @return Button to trigger remove room.
	 */
	public JButton getRemoveRoomsButton() {
		return removeRoomsButton;
	}

	/**
	 * Returns the input field where the name of a room to remove is
	 * entered by the user.
	 * @return Input text field for room name to remove.
	 */
	public JTextField getRemoveRoomsInput() {
		return removeRoomsInput;
	}

	/**
	 * Returns the button to trigger the update base price operation for the
	 * current hotel.
	 * @return Button to trigger update base price.
	 */
	public JButton getUpdateBasePriceButton() {
		return updateBasePriceButton;
	}

	/**
	 * Returns the input field where the new base room price for the
	 * current hotel is entered by the user.
	 * @return Input text field for new base price of room.
	 */
	public JTextField getUpdateBasePriceInput() {
		return updateBasePriceInput;
	}

	/**
	 * Returns the button that triggers deletion of a hotel.
	 * @return Button that triggers deletion of a hotel.
	 */
	public JButton getDeleteHotelButton() {
		return deleteHotelButton;
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
	 * Returns the hotel currently viewed in the interface.
	 * @return Hotel being viewed in the interface.
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
		changeHotelNameInput.setText(currentHotel.getName());

		outputWrapper.setVisible(true);
	}

	public void updateHotelName(Hotel hotel) {
		currentHotel = hotel;
	}
}
