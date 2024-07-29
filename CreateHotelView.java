import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * CreateHotelView contains the layout for the "Create Hotel" screen.
 */
public class CreateHotelView extends View {
	private Header header;
	private Text description;
	private InputWrapper inputWrapper;
	private JTextField hotelNameInput;
	
	private InputWrapper standardRoomWrapper;
	private SubHeader standardRoomHeader;
	private JSpinner standardRoomInput;
	private SpinnerNumberModel roomCountBounds;
	
	private InputWrapper deluxeRoomWrapper;
	private SubHeader deluxeRoomHeader;
	private JSpinner deluxeRoomInput;
	private SpinnerNumberModel deluxeRoomBounds;
	
	private InputWrapper executiveRoomWrapper;
	private SubHeader executiveRoomHeader;
	private JSpinner executiveRoomInput;
	private SpinnerNumberModel executiveRoomBounds;
	
	private JButton addButton;

	/**
	 * Constructor creates the layout of the "Create hotel" screen.
	 */
	public CreateHotelView() {
		header = new Header("Create hotel");

		description = new Text("Enter a name and a room count to add a hotel to the system. You cannot create a hotel if its name has already been taken.");

		inputWrapper = new InputWrapper();

		hotelNameInput = new JTextField();
		hotelNameInput.setPreferredSize(new Dimension(400, 30));
		
		roomCountBounds = new SpinnerNumberModel(0, 0, 50, 1);
		deluxeRoomBounds = new SpinnerNumberModel(0, 0, 50, 1);
		executiveRoomBounds = new SpinnerNumberModel(0, 0, 50, 1);
		
		inputWrapper.add(hotelNameInput);
		
		standardRoomWrapper = new InputWrapper();
		standardRoomInput = new JSpinner(roomCountBounds);
		standardRoomInput.setPreferredSize(new Dimension(50, 30));
		standardRoomHeader = new SubHeader("Standard rooms");
		standardRoomWrapper.add(standardRoomHeader);
		standardRoomWrapper.add(standardRoomInput);

		deluxeRoomWrapper = new InputWrapper();
		deluxeRoomInput = new JSpinner(deluxeRoomBounds);
		deluxeRoomInput.setPreferredSize(new Dimension(50, 30));
		deluxeRoomHeader = new SubHeader("Deluxe rooms");
		deluxeRoomWrapper.add(deluxeRoomHeader);
		deluxeRoomWrapper.add(deluxeRoomInput);
		
		executiveRoomWrapper = new InputWrapper();
		executiveRoomInput = new JSpinner(executiveRoomBounds);
		executiveRoomInput.setPreferredSize(new Dimension(50, 30));
		executiveRoomHeader = new SubHeader("Executive rooms");
		executiveRoomWrapper.add(executiveRoomHeader);
		executiveRoomWrapper.add(executiveRoomInput);

		addButton = new JButton("Create hotel");
		addButton.setFont(UI.BUTTON_FONT);
		addButton.setAlignmentX(LEFT_ALIGNMENT);
		inputWrapper.add(addButton);
		
		add(Box.createRigidArea(new Dimension(40, 20)));
		add(header);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(description);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(inputWrapper);
		add(standardRoomWrapper);
		add(deluxeRoomWrapper);
		add(executiveRoomWrapper);
	}

	/**
	 * Resets the input fields when a hotel is created.
	 */
	public void resetFields() {
		hotelNameInput.setText("");
		standardRoomInput.setValue((int) 1);
	}

	/**
	 * Returns the button that the user clicks to add a hotel.
	 * @return Button to add a hotel.
	 */
	public JButton getAddButton() {
		return addButton;
	}

	/**
	 * Returns the text field that the user inputs a hotel name into.
	 * @return Text field that accepts hotel name input.
	 */
	public JTextField getHotelNameInput() {
		return hotelNameInput;
	}

	/**
	 * Returns the spinner that the user inputs the number of standard rooms into.
	 * @return Spinner that accepts room count input.
	 */
	public JSpinner getStandardRoomInput() {
		return standardRoomInput;
	}

	/**
	 * Returns the spinner that the user inputs the number of deluxe rooms into.
	 * @return Spinner that accepts room count input.
	 */
	public JSpinner getDeluxeRoomInput() {
		return deluxeRoomInput;
	}

	/**
	 * Returns the spinner that the user inputs the number of executive rooms into.
	 * @return Spinner that accepts room count input.
	 */
	public JSpinner getExecutiveRoomInput() {
		return executiveRoomInput;
	}
}
