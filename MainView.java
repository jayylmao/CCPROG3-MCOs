import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

/**
 * MainView contains the UI code for the main menu.
 */
public class MainView extends JPanel {
	private TopBarView topBar;
	private JPanel contentPanel;
	private CardLayout mainLayout;
	private UI ui = new UI();

	public MainView() {
		topBar = new TopBarView();
		contentPanel = new JPanel();
		mainLayout = new CardLayout(10, 10);

		contentPanel.setLayout(mainLayout);

		setBackground(ui.BG_MAIN);
		setLayout(new BorderLayout());
		add(topBar, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);

		contentPanel.add(new CreateHotelView(), "CreateHotel");
		contentPanel.add(new ViewHotelView(), "ViewHotel");
		contentPanel.add(new ManageHotelView(), "ManageHotel");
		contentPanel.add(new BookRoomView(), "BookRoom");
	}

	public TopBarView getTopBarView() {
		return topBar;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public CardLayout getMainLayout() {
		return mainLayout;
	}
}