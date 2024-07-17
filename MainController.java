import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * MainController controls the logic of the main menu.
 */
public class MainController {
	private MainView view;
	private HotelReservationSystem rSystem;
	private TopBarView topBar;
	private JPanel contentPanel;
	private CardLayout mainLayout;

	ArrayList<String> viewNames = new ArrayList<String>();

	public MainController(MainView view, HotelReservationSystem rSystem) {
		this.view = view;
		this.rSystem = rSystem;
		this.topBar = view.getTopBarView();
		this.contentPanel = view.getContentPanel();
		this.mainLayout = view.getMainLayout();

		viewNames.add("CreateHotel");
		viewNames.add("ViewHotel");
		viewNames.add("ManageHotel");
		viewNames.add("BookRoom");

		// Add ActionListener to each button that switches
		// views for each view name in the list.
		for (int i = 0; i < viewNames.size(); i++) {
			// Fixes error: Local variable i defined in an enclosing scope must
			// be final or effectively final
			final int idx = i;
			topBar.getButton(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainLayout.show(contentPanel, viewNames.get(idx));
				}
			});
		}

		addCreateHotelListener();
		addSearchHotelListener();
		addManageHotelListener();
		addBookRoomListener();
	}

	/**
	 * Connects the "Create hotel" button in the Create Hotel screen to the model.
	 */
	private void addCreateHotelListener() {
		CreateHotelView createHotelView = (CreateHotelView) view.getViews().get(0);

		createHotelView.getAddButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hotelName = createHotelView.getHotelNameInput().getText();
				int roomCount = (int) createHotelView.getRoomCountInput().getValue();
				boolean success = rSystem.addHotel(hotelName, roomCount);
				
				ArrayList<Hotel> hotels = rSystem.getHotels();

				for (Hotel hotel : hotels) {
					System.out.println(hotel.getName());
				}

				createHotelView.showResult(success);
			}
		});
	}

	/**
	 * Connects the "Search hotel" button found in the "View hotel"
	 * screen to the model.
	 */
	private void addSearchHotelListener() {
		ViewHotelView viewHotelView = (ViewHotelView) view.getViews().get(1);

		viewHotelView.getSearchButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchQuery = viewHotelView.getInput().getText();
				Hotel foundHotel = rSystem.getHotel(searchQuery);

				viewHotelView.showResult(foundHotel);
			}
		});
	}

	/**
	 * Connects the "Search hotel" button found in the "Manage hotel"
	 * screen to the model.
	 */
	private void addManageHotelListener() {
		ManageHotelView manageHotelView = (ManageHotelView) view.getViews().get(2);

		manageHotelView.getSearchButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchQuery = manageHotelView.getInput().getText();
				Hotel foundHotel = rSystem.getHotel(searchQuery);

				manageHotelView.showResult(foundHotel);
			}
		});
	}

	/**
	 * Connects the "Search hotel" button found in the "Book room"
	 * screen to the model.
	 */
	private void addBookRoomListener() {
		BookRoomView bookRoomView = (BookRoomView) view.getViews().get(3);

		bookRoomView.getSearchButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchQuery = bookRoomView.getInput().getText();
				Hotel foundHotel = rSystem.getHotel(searchQuery);
				ArrayList<Room> rooms;

				if (foundHotel != null) {
					rooms = foundHotel.getRooms();
				} else {
					rooms = null;
				}

				bookRoomView.showResult(rooms);
			}
		});
	}
}
