import java.util.ArrayList;

import javax.swing.JButton;

/**
 * MenuModel contains the data needed for the main menu.
 */
public class MenuModel {
	private ArrayList<JButton> menuButtons = new ArrayList<JButton>();
	
	public MenuModel() {
		menuButtons.add(new JButton("Create Hotel"));
		menuButtons.add(new JButton("View Hotel"));
		menuButtons.add(new JButton("Manage Hotel"));
		menuButtons.add(new JButton("Simulate Booking"));
	}

	public ArrayList<JButton> getMenuButtons() {
		return menuButtons;
	}
}
