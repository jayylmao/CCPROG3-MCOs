import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * TopBarView contains the layout for the persistent top navigation bar.
 */
public class TopBarView extends JPanel {
	private ArrayList<JButton> buttons;
	private ArrayList<JLabel> labels;

	/**
	 * Constructor that initializes the TopBarView containing the buttons to switch to the major functionalities
	 */
	public TopBarView() {
		buttons = new ArrayList<JButton>();
		labels = new ArrayList<JLabel>();

		buttons.add(new JButton("Create Hotel"));
		buttons.add(new JButton("View Hotel"));
		buttons.add(new JButton("Manage Hotel"));
		buttons.add(new JButton("Book Room"));

		labels.add(new JLabel("Hotel Reservation System"));

		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBackground(UI.BG_SECONDARY);

		for (JLabel label : labels) {
			label.setFont(UI.HEADER_FONT);
			this.add(label);
		}

		for (JButton button : buttons) {
			button.setFont(UI.BUTTON_FONT);
			this.add(button);
		}
	}

	/**
	 * Gets the array of buttons that switch to major functionalities.
	 * @return Array of JButtons.
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}

	/**
	 * Gets a specific JButton based on an index.
	 * @param buttonNumber index of the button.
	 * @return JButton representing a major functionality.
	 */
	public JButton getButton(int buttonNumber) {
		return buttons.get(buttonNumber);
	}
}
