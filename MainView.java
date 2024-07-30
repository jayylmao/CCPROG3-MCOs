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

	/**
	 * Constructor that initializes the MainView class.
	 */
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

	/**
	 * Gets the topBar View class.
	 * @return TopBarView instance.
	 */
	public TopBarView getTopBarView() {
		return topBar;
	}

	/**
	 * Gets the contentPanel JPanel.
	 * @return JPanel.
	 */
	public JPanel getContentPanel() {
		return contentPanel;
	}

	/**
	 * Gets the MainLayout to show content in.
	 * @return Main layout.
	 */
	public CardLayout getMainLayout() {
		return mainLayout;
	}

	/**
	 * Gets the View list containing different Views for each major function in the hotel reservation system.
	 * @return ArrayList of Jpanels.
	 */
	public ArrayList<JPanel> getViews() {
		return views;
	}
}