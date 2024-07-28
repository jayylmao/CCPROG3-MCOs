import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayList;

/**
 * MainView contains the UI code for the main menu.
 */
public class MainView extends View {
	private TopBarView topBar;
	private JPanel contentPanel;
	private CardLayout mainLayout;

	private ArrayList<JPanel> views;
	private ArrayList<String> viewNames;

	public MainView() {
		// Initialize top bar.
		topBar = new TopBarView();

		// Initialize main parent view and layout.
		contentPanel = new JPanel();
		mainLayout = new CardLayout(10, 10);

		// Initialize view objects.
		views = new ArrayList<JPanel>();
		views.add(new CreateHotelView());
		views.add(new ViewHotelView());
		views.add(new ManageHotelView());
		views.add(new BookRoomView());

		viewNames = new ArrayList<String>();
		viewNames.add("CreateHotel");
		viewNames.add("ViewHotel");
		viewNames.add("ManageHotel");
		viewNames.add("BookRoom");

		// Add layout to main parent.
		contentPanel.setLayout(mainLayout);

		// Move top bar to the top and content panel to the center of the window.
		setLayout(new BorderLayout());
		add(topBar, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);

		// Add views to content panel with names.
		for (int i = 0; i < views.size(); i++) {
			contentPanel.add(views.get(i), viewNames.get(i));
		}
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

	public ArrayList<JPanel> getViews() {
		return views;
	}
}