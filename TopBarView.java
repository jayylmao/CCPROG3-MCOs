import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopBarView extends JPanel {
	private ArrayList<JButton> buttons;
	private ArrayList<JLabel> labels;

	private UI ui = new UI();

	public TopBarView() {
		buttons = new ArrayList<JButton>();
		labels = new ArrayList<JLabel>();

		buttons.add(new JButton("Create Hotel"));
		buttons.add(new JButton("View Hotel"));
		buttons.add(new JButton("Manage Hotel"));
		buttons.add(new JButton("Book Room"));

		labels.add(new JLabel("Hotel Reservation System"));

		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBackground(ui.BG_SECONDARY);

		for (JLabel label : labels) {
			label.setFont(ui.HEADER_FONT);
			this.add(label);
		}

		for (JButton button : buttons) {
			button.setFont(ui.TEXT_FONT);
			this.add(button);
		}
	}

	public ArrayList<JButton> getButtons() {
		return buttons;
	}

	public JButton getButton(int buttonNumber) {
		return buttons.get(buttonNumber);
	}
}
