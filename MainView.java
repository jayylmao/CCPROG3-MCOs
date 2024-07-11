import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 * MainView contains the UI code for the main menu.
 */
public class MainView {
	private JFrame mainFrame;
	private JLabel title;
	UI ui = new UI();

	/**
	 * This constructor initializes the window with defaults,
	 * and calls the main menu method.
	 */
	public MainView() {
		this.mainFrame = new JFrame("Hotel Reservation System");

		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.mainFrame.setSize(1280, 720);

		this.title = new JLabel("Hotel Reservation System");
		this.title.setPreferredSize(new Dimension(220, 30));

		this.mainFrame.add(title);
		this.mainFrame.setVisible(true);
	}

	public void setMenuButton(JButton button) {
		this.mainFrame.add(button);
		this.mainFrame.revalidate();
		this.mainFrame.repaint();
	}
}
