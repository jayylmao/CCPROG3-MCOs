import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BookRoomView extends View {

	private JLabel header;
	private JLabel description;
	
	private JPanel inputWrapper;
	private JTextField input;
	private JButton searchButton;
	
	private JPanel outputWrapper;
	private JLabel hotelName;
	private JLabel roomCount;
	private JLabel estimateEarnings;

	JScrollPane listContainer;
	JList<String> hotelList;

	public BookRoomView() {
		header = new JLabel("Book a room");
		header.setFont(UI.HEADER_FONT);
		header.setAlignmentX(LEFT_ALIGNMENT);

		description = new JLabel("Enter the name of a hotel to book a room in.");
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
		outputWrapper.setLayout(new BoxLayout(outputWrapper, BoxLayout.X_AXIS));
		outputWrapper.setBackground(UI.BG_MAIN);
		outputWrapper.setAlignmentX(LEFT_ALIGNMENT);

		hotelName = new JLabel();
		hotelName.setAlignmentX(LEFT_ALIGNMENT);
		outputWrapper.add(hotelName);
		
		roomCount = new JLabel();
		roomCount.setAlignmentX(LEFT_ALIGNMENT);
		outputWrapper.add(roomCount);

		estimateEarnings = new JLabel();
		estimateEarnings.setAlignmentX(LEFT_ALIGNMENT);
		outputWrapper.add(estimateEarnings);

		// Add list of rooms to view.
		hotelList = new JList<>();

		listContainer = new JScrollPane(hotelList);
		listContainer.setAlignmentX(LEFT_ALIGNMENT);
		outputWrapper.add(listContainer);

		for (Component component : outputWrapper.getComponents()) {
			component.setFont(UI.TEXT_FONT);
		}

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
