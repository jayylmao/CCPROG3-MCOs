import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

/**
 * MainView contains the UI code for the main menu.
 */
public class MainView {
	private JFrame mainFrame;
	private JPanel contentPanel;
	private JPanel topBar;
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
		this.mainFrame.setSize(870, 720);
		this.mainFrame.setBackground(ui.BG_MAIN);

		this.topBar = new JPanel();
		this.topBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.topBar.setBackground(ui.BG_SECONDARY);

		this.title = new JLabel("Hotel Reservation System");
		this.title.setFont(ui.HEADER_FONT);

		this.mainFrame.add(topBar);
		this.topBar.add(title);

		this.contentPanel = new JPanel();
		this.contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.contentPanel.setBackground(ui.BG_MAIN);
		this.contentPanel.setSize(900, 600);
		this.mainFrame.add(contentPanel);

		this.mainFrame.setVisible(true);
	}

	/**
	 * Adds a given button to the main menu and repaints the screen to display
	 * the new button without having to resize the window.
	 * @param button Button to add to main menu.
	 */
	public void setMenuButton(JButton button, ActionListener al) {
		this.topBar.add(button, al);
		this.topBar.revalidate();
		this.topBar.repaint();
	}

	/**
	 * Displays a view in the main window where content is displayed, such as
	 * for creating a hotel, booking a room, etc.
	 * @param model Menu model to display.
	 */
	public void displaySubView(MenuModel model) {

	}
}
