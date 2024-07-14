import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CreateHotelModel extends MenuModel {
	public CreateHotelModel() {
		setMenuButtons(new ArrayList<JButton>());
		setMenuLabels(new ArrayList<JLabel>());
		setMenuTextFields(new ArrayList<JTextField>());

		getMenuButtons().add(new JButton("Create Hotel"));
		getMenuLabels().add(new JLabel("Create Hotel"));
		getMenuTextFields().add(new JTextField());
	}
}
