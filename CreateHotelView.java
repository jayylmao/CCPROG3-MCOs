import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateHotelView extends JPanel {
	private UI ui = new UI();
	private JLabel header;
	private JLabel description;
	private JPanel inputWrapper;
	private JTextField input;
	private JButton addButton;

	public CreateHotelView() {
		header = new JLabel("Create Hotel");
		header.setFont(ui.HEADER_FONT);

		description = new JLabel("Enter a name to add a hotel to the system. You cannot create a hotel if its name has already been taken.");
		description.setFont(ui.TEXT_FONT);

		inputWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));

		input = new JTextField();
		input.setPreferredSize(new Dimension(400, 30));
		
		inputWrapper.setBackground(ui.BG_MAIN);
		inputWrapper.add(input);

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

	public JButton getAddButton() {
		return addButton;
	}
}
