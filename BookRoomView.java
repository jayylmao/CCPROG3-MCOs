import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

public class BookRoomView extends View {

	private Header header;
	private Text description;
	
	private InputWrapper nameWrapper;

	private SubHeader firstNameHeader;
	private JTextField firstName;

	private SubHeader lastNameHeader;
	private JTextField lastName;

	private InputWrapper dateWrapper;
	private SubHeader checkInDateHeader;
	private SpinnerNumberModel checkInDateBounds;
	private JSpinner checkInDate;
	
	private SubHeader checkOutDateHeader;
	private SpinnerNumberModel checkOutDateBounds;
	private JSpinner checkOutDate;
	
	private InputWrapper hotelsWrapper;
	private SubHeader hotelsHeader;
	private JComboBox<String> hotelsInput;
	
	private InputWrapper checkRoomsWrapper;
	private SubHeader checkRoomsHeader;
	private JComboBox<String> checkRoomsInput;

	private InputWrapper discountWrapper;
	private SubHeader discountHeader;
	private JTextField discountInput;
	
	JScrollPane listContainer;
	JList<String> hotelList;
	
	private JButton submitButton;

	public BookRoomView() {
		header = new Header("Book a room");

		description = new Text("Enter the name of the customer to get started.");
		
		// Add controls for user input.
		nameWrapper = new InputWrapper();

		firstNameHeader = new SubHeader("First name");
		firstName = new JTextField();
		firstName.setPreferredSize(new Dimension(120, 30));

		lastNameHeader = new SubHeader("Last name");
		lastName = new JTextField();
		lastName.setPreferredSize(new Dimension(120, 30));

		nameWrapper.add(firstNameHeader);
		nameWrapper.add(firstName);
		nameWrapper.add(lastNameHeader);
		nameWrapper.add(lastName);
		
		dateWrapper = new InputWrapper();
		checkInDateHeader = new SubHeader("Check-in date");
		checkInDateBounds = new SpinnerNumberModel(1, 1, 30, 1);
		checkInDate = new JSpinner(checkInDateBounds);
		checkInDate.setPreferredSize(new Dimension(50, 30));
		
		dateWrapper = new InputWrapper();
		checkOutDateHeader = new SubHeader("Check-out date");
		checkOutDateBounds = new SpinnerNumberModel(2, 2, 31, 1);
		checkOutDate = new JSpinner(checkOutDateBounds);
		checkOutDate.setPreferredSize(new Dimension(50, 30));
		
		dateWrapper.add(checkInDateHeader);
		dateWrapper.add(checkInDate);
		dateWrapper.add(checkOutDateHeader);
		dateWrapper.add(checkOutDate);
		
		hotelsWrapper = new InputWrapper();
		hotelsHeader = new SubHeader("Choose a hotel");
		hotelsInput = new JComboBox<>();
		hotelsInput.addItem("Select a hotel");
		hotelsInput.setPreferredSize(new Dimension(150, 30));
		
		hotelsWrapper.add(hotelsHeader);
		hotelsWrapper.add(hotelsInput);
		
		checkRoomsWrapper = new InputWrapper();
		checkRoomsHeader = new SubHeader("Choose a room");
		checkRoomsInput = new JComboBox<String>();
		checkRoomsInput.addItem("Select a room");
		checkRoomsInput.setPreferredSize(new Dimension(150, 30));
		
		checkRoomsWrapper.add(checkRoomsHeader);
		checkRoomsWrapper.add(checkRoomsInput);
		
		discountWrapper = new InputWrapper();
		discountHeader = new SubHeader("Enter discount code");
		discountInput = new JTextField();
		discountInput.setPreferredSize(new Dimension(150, 30));
		
		discountWrapper.add(discountHeader);
		discountWrapper.add(discountInput);
		
		submitButton = new JButton("Submit");
		
		add(Box.createRigidArea(new Dimension(10, 20)));
		add(header);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(description);
		add(Box.createRigidArea(new Dimension(0, 10)));
		
		add(nameWrapper);
		add(dateWrapper);
		add(hotelsWrapper);
		add(checkRoomsWrapper);
		add(discountWrapper);
		add(submitButton);
	}

	/**
	 * Returns the button to submit a customer's name.
	 * @return Button to submit a customer's name.
	 */
	public JButton getSubmitButton() {
		return submitButton;
	}

	public JTextField getFirstNameField() {
		return firstName;
	}

	public JTextField getLastNameField() {
		return lastName;
	}

	public JComboBox<String> getHotelsInput() {
		return hotelsInput;
	}

	public JComboBox<String> getRoomsInput() {
		return checkRoomsInput;
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
	}
}
