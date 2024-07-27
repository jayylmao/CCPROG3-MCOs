import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

public class BookRoomView extends View {

	private Header header;
	private Text description;
	
	private InputWrapper inputWrapper;

	private SubHeader firstNameHeader;
	private JTextField firstName;

	private SubHeader lastNameHeader;
	private JTextField lastName;

	private JButton submitNameButton;
	
	private JPanel outputWrapper;

	private SubHeader checkInDateHeader;
	private JSpinner checkInDate;

	private SubHeader checkOutDateHeader;
	private JSpinner checkOutDate;
	
	JScrollPane listContainer;
	JList<String> hotelList;

	public BookRoomView() {
		header = new Header("Book a room");

		description = new Text("Enter the name of the customer to get started.");
		
		// Add controls for user input.
		inputWrapper = new InputWrapper();

		firstNameHeader = new SubHeader("First name");
		firstName = new JTextField();
		firstName.setPreferredSize(new Dimension(120, 30));

		lastNameHeader = new SubHeader("Last name");
		lastName = new JTextField();
		lastName.setPreferredSize(new Dimension(120, 30));

		submitNameButton = new JButton("Submit");

		inputWrapper.add(firstNameHeader);
		inputWrapper.add(firstName);
		inputWrapper.add(lastNameHeader);
		inputWrapper.add(lastName);
		inputWrapper.add(submitNameButton);

		// Add labels for displaying output.
		outputWrapper = new JPanel();
		outputWrapper.setLayout(new BoxLayout(outputWrapper, BoxLayout.X_AXIS));
		outputWrapper.setBackground(UI.BG_MAIN);
		outputWrapper.setAlignmentX(LEFT_ALIGNMENT);
		outputWrapper.setVisible(false);

		add(Box.createRigidArea(new Dimension(10, 20)));
		add(header);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(description);
		add(Box.createRigidArea(new Dimension(0, 10)));

		add(inputWrapper);
		add(outputWrapper);

		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		try {
			hotels.add(new Hotel("test", 1));
		} catch (InvalidRoomCountException e) {
			System.out.println("fail");
		}
	}

	/**
	 * Returns the button to submit a customer's name.
	 * @return Button to submit a customer's name.
	 */
	public JButton getSubmitNameButton() {
		return submitNameButton;
	}

	/**
	 * Updates the output labels with the corresponding values upon
	 * receiving data from the model.
	 * @param hotel Hotel to display information of.
	 */
	public void showResult(ArrayList<Room> rooms) {
		DefaultListModel<String> listModel = new DefaultListModel<>();

		if (rooms != null) {
			for (Room room : rooms) {
				listModel.addElement(room.getName());
			}

			hotelList.setModel(listModel);
		} else {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "A hotel matching your search query could not be found.", "Error", 2);
		}

		outputWrapper.revalidate();
		outputWrapper.repaint();
	}
}
