import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * View contains common methods for displaying message dialogs to the user,
 * as well as setting a common layout and background.
 */
public class View extends JPanel {
	/**
	 * Constructor that initializes the view class.
	 */
	public View() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(UI.BG_MAIN);
	}
	
	/**
	 * Displays a dialog box alerting the user if the
	 * hotel was successfully created.
	 * @param message Message to display.
	 */
	public void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Displays a dialog box alerting the user if the
	 * hotel was not successfully created.
	 * @param message Message to display.
	 */
	public void showError(String message) {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
