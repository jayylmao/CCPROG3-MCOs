import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * CreateHotelView contains the layout for the "Create Hotel" screen.
 */
public class CreateHotelView extends JPanel {
	private UI ui = new UI();
	private JLabel header;
	private JLabel description;
	private JPanel inputWrapper;
	private JTextField hotelNameInput;
	private JSpinner roomCountInput;
	private SpinnerNumberModel roomCountBounds;
	private JButton addButton;

	/**
	 * Constructor creates the layout of the "Create hotel" screen.
	 */
	public CreateHotelView() {
		header = new JLabel("Create Hotel");
		header.setFont(ui.HEADER_FONT);

		description = new JLabel("Enter a name and a room count to add a hotel to the system. You cannot create a hotel if its name has already been taken.");
		description.setFont(ui.TEXT_FONT);

		inputWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));

		hotelNameInput = new JTextField();
		hotelNameInput.setPreferredSize(new Dimension(400, 30));
		
		roomCountBounds = new SpinnerNumberModel(1, 1, 50, 1);
		roomCountInput = new JSpinner(roomCountBounds);
		roomCountInput.setPreferredSize(new Dimension(50, 30));

		inputWrapper.setBackground(ui.BG_MAIN);
		inputWrapper.add(hotelNameInput);
		inputWrapper.add(roomCountInput);

		addButton = new JButton("Create hotel");

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(ui.BG_MAIN);

		for (Component component : this.getComponents()) {
			((JComponent) component).setAlignmentX(LEFT_ALIGNMENT);
		}
		
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(header);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(description);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(inputWrapper);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(addButton);
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

	/**
	 * Displays a dialog box alerting the user whether the
	 * hotel was successfully created or not.
	 * @param success Success condition from hotel model.
	 */
	public void showResult(boolean success) {
		if (success) {
			JOptionPane.showMessageDialog(this, "Your hotel has been added to the system.", "Hotel successfully added", 3);
		} else {
			JOptionPane.showMessageDialog(this, "Your hotel could not be created.\nCheck that there are no duplicates in the system and that you have entered a room count from 1 - 50.", "Error", 2);
		}
	}
}
