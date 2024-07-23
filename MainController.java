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
		addGetAvailableRoomsListener();

		addManageHotelListener();
		addChangeHotelNameListener();
		addAddRoomsListener();
		addRemoveRoomsListener();

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
				try {
					rSystem.addHotel(hotelName, roomCount);
					createHotelView.showMessageDialog("Your hotel was created successfully.");
				} catch (InvalidRoomCountException exception) {
					createHotelView.showError("There must be 1 - 50 rooms.\nYour hotel could not be created.");
				} catch (DuplicateNameException exception) {
					createHotelView.showError("Another hotel with the same name was found.\nYour hotel could not be created.");
				} catch (InvalidHotelNameException exception) {
					createHotelView.showError("You must enter a name for the hotel.\nYour hotel could not be created.");
				}
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
				String searchQuery = viewHotelView.getSearchInput().getText();
				Hotel foundHotel = rSystem.getHotel(searchQuery);

				if (foundHotel != null) {
					viewHotelView.showResult(foundHotel);
				} else {
					viewHotelView.showError("A hotel matching your search query could not be found.");
				}
			}
		});
	}

	/**
	 * Connects the "Check available rooms on date" button found in the
	 * "View hotel" screen to the model.
	 */
	private void addGetAvailableRoomsListener() {
		ViewHotelView viewHotelView = (ViewHotelView) view.getViews().get(1);

		viewHotelView.getCheckAvailableRoomsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hotel currentHotel = viewHotelView.getCurrentHotel();
				Date checkInDate;
				Date checkOutDate;

				int availableRoomCount;
				int bookedRoomCount;

				try {
					checkInDate = new Date(Integer.parseInt(viewHotelView.getCheckAvailableRoomsInput().getText()));
					checkOutDate = new Date(Integer.parseInt(viewHotelView.getCheckAvailableRoomsInput().getText()) + 1);

					if (currentHotel != null) {
						availableRoomCount = currentHotel.getAvailableRoomCount(checkInDate, checkOutDate);
						bookedRoomCount = currentHotel.getBookedRoomCount(checkInDate, checkOutDate);
						viewHotelView.setAvailableRooms(availableRoomCount, bookedRoomCount);
					}
				} catch (IllegalDateException exception) {
					viewHotelView.showError("Invalid date entered. Enter a date from 1 - 30.");
				}
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

				if (foundHotel != null) {
					manageHotelView.showResult(foundHotel);
				} else {
					manageHotelView.showError("A hotel matching your search query could not be found.");
				}
			}
		});
	}

	/**
	 * Connects the "Change hotel name" button found in the "Manage hotel"
	 * screen to the model.
	 */
	private void addChangeHotelNameListener() {
		ManageHotelView manageHotelView = (ManageHotelView) view.getViews().get(2);

		manageHotelView.getChangeHotelNameButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hotel currentHotel = manageHotelView.getCurrentHotel();
				String name = manageHotelView.getChangeHotelNameInput().getText();

				try {
					rSystem.changeHotelName(currentHotel, name);
					manageHotelView.showResult(rSystem.getHotel(name));
					manageHotelView.showMessageDialog("Your hotel name was changed successfully.");
				} catch (DuplicateNameException exception) {
					manageHotelView.showError("Duplicate hotel name. Your hotel name was not changed.");
				}
			}
		});
	}

	/**
	 * Connects the "Add rooms" button found in the "Manage hotel"
	 * screen to the model.
	 */
	private void addAddRoomsListener() {
		ManageHotelView manageHotelView = (ManageHotelView) view.getViews().get(2);

		manageHotelView.getAddRoomsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hotel currentHotel = manageHotelView.getCurrentHotel();
				int roomCount = (int) manageHotelView.getAddRoomsInput().getValue();

				try {
					currentHotel.addRoom(roomCount);
				} catch (InvalidRoomCountException exception) {
					manageHotelView.showError("The maximum number of rooms is 50.");
				}
			}
		});
	}

	/**
	 * Connects the "Remove rooms" button found in the "Manage hotel"
	 * screen to the model.
	 */
	private void addRemoveRoomsListener() {
		ManageHotelView manageHotelView = (ManageHotelView) view.getViews().get(2);
		
		manageHotelView.getRemoveRoomsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hotel currentHotel = manageHotelView.getCurrentHotel();
				String roomName = manageHotelView.getRemoveRoomsInput().getText();

				try {
					currentHotel.removeRoom(roomName);
				} catch (RoomNotFoundException exception) {
					manageHotelView.showError("A room with that name was not found.");
				} catch (InvalidRoomCountException exception) {
					manageHotelView.showError("You cannot remove the last room in a hotel.");
				} catch (RoomHasBookingsException exception) {
					manageHotelView.showError("This room has bookings and cannot be removed.");
				}
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
