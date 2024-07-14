import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * MenuModel contains the data needed for the main menu.
 */
public class MenuModel {
	private ArrayList<JButton> menuButtons;
	private ArrayList<JLabel> menuLabels;
	private ArrayList<JTextField> menuTextFields;
	
	/**
	 * Constructor creates the main menu.
	 */
	public MenuModel() {
		menuButtons = new ArrayList<JButton>();
		menuButtons.add(new JButton("Create Hotel"));
		menuButtons.add(new JButton("View Hotel"));
		menuButtons.add(new JButton("Manage Hotel"));
		menuButtons.add(new JButton("Book Room"));
	}

	/**
	 * Returns the list of buttons.
	 * @return List of buttons.
	 */
	public ArrayList<JButton> getMenuButtons() {
		return menuButtons;
	}

	/**
	 * Returns the list of text labels.
	 * @return List of text labels.
	 */
	public ArrayList<JLabel> getMenuLabels() {
		return menuLabels;
	}
	
	/**
	 * Returns the list of text fields.
	 * @return List of text fields.
	 */
	public ArrayList<JTextField> getMenuTextFields() {
		return menuTextFields;
	}
	
	public void setMenuButtons(ArrayList<JButton> buttonList) {
		this.menuButtons = buttonList;
	}
	
	public void setMenuLabels(ArrayList<JLabel> labelList) {
		this.menuLabels = labelList;
	}
	
	public void setMenuTextFields(ArrayList<JTextField> textFieldList) {
		this.menuTextFields = textFieldList;
	}
}
