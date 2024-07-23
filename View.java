import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class View extends JPanel {
	public View() {
		super();
	}
	
	/**
	 * Displays a dialog box alerting the user if the
	 * hotel was successfully created.
	 */
	public void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Hotel successfully added", JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Displays a dialog box alerting the user if the
	 * hotel was not successfully created.
	 */
	public void showError(String message) {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
