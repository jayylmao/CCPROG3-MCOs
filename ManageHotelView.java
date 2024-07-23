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

	public ManageHotelView() {
		currentHotel = null;
		header = new Header("Manage hotel");
		description = new JLabel("Enter the name of a hotel registered in the system to edit its properties.");

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(UI.BG_MAIN);
		
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

		add(Box.createRigidArea(new Dimension(20, 20)));
		add(header);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(description);
		add(Box.createRigidArea(new Dimension(0, 10)));

		add(inputWrapper);
		add(outputWrapper);
		outputWrapper.setVisible(false);

		setBackground(UI.BG_MAIN);
	}

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
	 * Returns the button to trigger a hotel name change.
	 * @return Button to trigger hotel name change.
	 */
	public JButton getChangeHotelNameButton() {
		return changeHotelNameButton;
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
	 * Returns the input field that lets the user input a new name for the
	 * current hotel.
	 * @return Text field for changing the name of the current hotel.
	 */
	public JTextField getChangeHotelNameInput() {
		return changeHotelNameInput;
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
