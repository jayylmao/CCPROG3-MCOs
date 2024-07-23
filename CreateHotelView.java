import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * CreateHotelView contains the layout for the "Create Hotel" screen.
 */
public class CreateHotelView extends View {
	private Header header;
	private Text description;
	private JPanel inputWrapper;
	private JTextField hotelNameInput;
	private JSpinner roomCountInput;
	private SpinnerNumberModel roomCountBounds;
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
		
		roomCountBounds = new SpinnerNumberModel(1, 1, 50, 1);
		roomCountInput = new JSpinner(roomCountBounds);
		roomCountInput.setPreferredSize(new Dimension(50, 30));

		inputWrapper.setBackground(UI.BG_MAIN);
		inputWrapper.add(hotelNameInput);
		inputWrapper.add(roomCountInput);

		addButton = new JButton("Create hotel");
		addButton.setFont(UI.BUTTON_FONT);
		addButton.setAlignmentX(LEFT_ALIGNMENT);
		inputWrapper.add(addButton);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(UI.BG_MAIN);
		
		add(Box.createRigidArea(new Dimension(40, 20)));
		add(header);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(description);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(inputWrapper);
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
	 * Returns the spinner that the user inputs a room count into.
	 * @return Spinner that accepts room count input.
	 */
	public JSpinner getRoomCountInput() {
		return roomCountInput;
	}
}
