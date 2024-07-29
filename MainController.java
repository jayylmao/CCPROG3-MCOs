import javax.swing.JButton;
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
			
			JButton currentButton = topBar.getButton(i);

			switch (i) {
			case 0:
				currentButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mainLayout.show(contentPanel, viewNames.get(idx));
					}
				});
				break;
			case 1:
				ViewHotelView viewHotelView = (ViewHotelView) view.getViews().get(i);
				currentButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (rSystem.getHotels().size() > 0) {
							viewHotelView.hideOutput();
							mainLayout.show(contentPanel, viewNames.get(idx));
						} else {
							view.showError("No hotels have been created yet.");
						}
					}
				});
				break;
			case 2:
				ManageHotelView manageHotelView = (ManageHotelView) view.getViews().get(i);
				currentButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (rSystem.getHotels().size() > 0) {
							manageHotelView.hideOutput();
							mainLayout.show(contentPanel, viewNames.get(idx));
						} else {
							view.showError("No hotels have been created yet.");
						}
					}
				});
				break;
			case 3:
				BookRoomView bookRoomView = (BookRoomView) view.getViews().get(i);
				currentButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						bookRoomView.getHotelsInput().removeAllItems();
						bookRoomView.getHotelsInput().addItem("Select a hotel");

						for (Hotel hotel : rSystem.getHotels()) {
							bookRoomView.getHotelsInput().addItem(hotel.getName());
						}
						
						if (rSystem.getHotels().size() > 0) {
							mainLayout.show(contentPanel, viewNames.get(idx));
						} else {
							view.showError("No hotels have been created yet.");
						}
					}
				});
				break;
			}
		}

		addCreateHotelListener();
		
		addSearchHotelListener();
		addGetAvailableRoomsListener();
		addGetCheckRoomInfoListener();
		
		addManageHotelListener();
		addChangeHotelNameListener();
		addAddRoomsListener();
		addRemoveRoomsListener();
		addUpdateBasePriceListener();
		addDeleteHotelListener();
		
		addBookRoomReserveListener();
		addGetHotelRoomsListener();
	}

	/**
	 * Connects the "Create hotel" button in the Create Hotel screen to the model.
	 */
	private void addCreateHotelListener() {
		CreateHotelView createHotelView = (CreateHotelView) view.getViews().get(0);

		createHotelView.getAddButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hotelName = createHotelView.getHotelNameInput().getText();
				int standardRoomCount = (int) createHotelView.getStandardRoomInput().getValue();
				int deluxeRoomCount = (int) createHotelView.getDeluxeRoomInput().getValue();
				int executiveRoomCount = (int) createHotelView.getExecutiveRoomInput().getValue();

				int totalRoomCount = standardRoomCount + deluxeRoomCount + executiveRoomCount;

				try {
					rSystem.addHotel(hotelName, standardRoomCount, deluxeRoomCount, executiveRoomCount);

					if (totalRoomCount == 1) {
						createHotelView.showMessageDialog(String.format("Your hotel %s was created successfully with %d room.", hotelName, totalRoomCount));
					} else {
						createHotelView.showMessageDialog(String.format("Your hotel %s was created successfully with %d standard rooms, %d deluxe rooms, and %d executive rooms.", hotelName, standardRoomCount, deluxeRoomCount, executiveRoomCount));
					}
					createHotelView.resetFields();
				} catch (InvalidRoomCountException exception) {
					createHotelView.showError("There must be 1 - 50 rooms in total.\nYour hotel could not be created.");
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
				} catch (NumberFormatException exception) {
					viewHotelView.showError("Invalid date entered. Enter a date from 1 - 30.");
				}
			}
		});
	}

	/**
	 * Connects the "Check room info" found in the "View hotel" screen to
	 * the model.
	 */
	private void addGetCheckRoomInfoListener() {
		ViewHotelView viewHotelView = (ViewHotelView) view.getViews().get(1);

		viewHotelView.getCheckRoomInfoButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hotel currentHotel = viewHotelView.getCurrentHotel();
				String roomName = viewHotelView.getCheckRoomInfoInput().getText();
				
				Room room = currentHotel.getRoom(roomName);
				viewHotelView.setRoomInfoName(roomName);

				if (room != null) {
					try {
						for (int i = 1; i < 31; i++) {
							if (room.isOccupied(new Date(i), new Date(i + 1))) {
								viewHotelView.getRoomInfoCalendar().markTileOccupied(i);
							} else {
								viewHotelView.getRoomInfoCalendar().markTileFree(i);
							}
						}
					} catch (IllegalDateException exception) {
						viewHotelView.showError("Date out of bounds.");
					}
	
					viewHotelView.showRoomInfo();
				} else {
					viewHotelView.showError("A room with that name could not be found.");
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
				String roomType = manageHotelView.getRoomType();

				try {
					currentHotel.addRoom(roomCount, roomType);

					if (roomCount == 1) {
						manageHotelView.showMessageDialog(roomType + " was added successfully.");
					} else {
						manageHotelView.showMessageDialog(roomType + "s were added successfully.");
					}
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
					manageHotelView.showMessageDialog("Room was removed successfully.");
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
	 * Connects the "Update base price" button found in the "Manage hotel"
	 * screen to the model.
	 */
	private void addUpdateBasePriceListener() {
		ManageHotelView manageHotelView = (ManageHotelView) view.getViews().get(2);

		manageHotelView.getUpdateBasePriceButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hotel currentHotel = manageHotelView.getCurrentHotel();
				double newBasePrice;
				
				try {
					newBasePrice = Double.valueOf(manageHotelView.getUpdateBasePriceInput().getText());
					currentHotel.setBasePrice(newBasePrice);
					manageHotelView.showMessageDialog("Base price was updated successfully.");
				} catch (NumberFormatException exception) {
					manageHotelView.showError("Enter a valid number greater than or equal to 100 as the base price.");
				} catch (RoomHasBookingsException exception) {
					manageHotelView.showError("You cannot modify the base room price of a hotel with bookings.");
				} catch (InvalidBasePriceException exception) {
					manageHotelView.showError("Enter a valid number greater than or equal to 100 as the base price.");
				}

			}
		});
	}

	/**
	 * Connects the "Delete hotel" button found in the "Manage hotel"
	 * screen to the model.
	 */
	private void addDeleteHotelListener() {
		ManageHotelView manageHotelView = (ManageHotelView) view.getViews().get(2);

		manageHotelView.getDeleteHotelButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hotel currentHotel = manageHotelView.getCurrentHotel();
				
				try {
					manageHotelView.hideOutput();
					manageHotelView.getInput().setText("");
					manageHotelView.showMessageDialog("Hotel was successfully removed.");

					rSystem.removeHotel(currentHotel.getName());
				} catch (InvalidHotelNameException exception) {
					manageHotelView.showError("Invalid hotel name provided.");
				}
			}
		});
	}

	/**
	 * Connects the "Reserve" button found in the "Book room"
	 * screen to the model.
	 */
	private void addBookRoomReserveListener() {
		BookRoomView bookRoomView = (BookRoomView) view.getViews().get(3);

		bookRoomView.getSubmitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bookRoomView.getLastName().equals("") ||
					bookRoomView.getFirstName().equals("")) {
					bookRoomView.showError("You must enter both a first and last name.");
				} else if (bookRoomView.getHotelsInput().getSelectedItem().equals("Select a hotel")) {
					bookRoomView.showError("You must select a hotel.");
				} else if (bookRoomView.getRoomsInput().getSelectedItem().equals("Select a room")) {
					bookRoomView.showError("You must select a room.");
				} else {
					try {
						// i might cry
						// so you get the room from the hotel,
						// then you reserve the room by creating a new guest instance
						// from their name,
						// their check in/out dates,
						// the room's price,
						// and the discount code.
						try {
							rSystem.getHotel((String) bookRoomView.getHotelsInput().getSelectedItem())
									.getRoom((String) bookRoomView.getRoomsInput().getSelectedItem())
									.reserveRoom(new Guest(bookRoomView.getFirstName(), bookRoomView.getLastName()), 
															bookRoomView.getCheckInDate(), bookRoomView.getCheckOutDate(),
															rSystem.getHotel((String) bookRoomView.getHotelsInput().getSelectedItem()).getRoom((String) bookRoomView.getRoomsInput().getSelectedItem()).getRoomPrice(),
															bookRoomView.getDiscountCode(), rSystem.getHotel((String) bookRoomView.getHotelsInput().getSelectedItem()).getDatePriceModifiers());
							bookRoomView.showMessageDialog("Your booking was successful.");
						} catch (InvalidDiscountCodeException exception) {
							bookRoomView.showError("Invalid discount code entered.");
						}
					} catch (IllegalDateException exception) {
						bookRoomView.showError("Invalid date selected.");
					} catch (InvalidCheckInDateException exception) {
						bookRoomView.showError("Your check out date must come after your check in date.");
					} catch (RoomIsOccupiedException exception) {
						bookRoomView.showError("This room is occupied on your given dates.");
					}
				}
			}
		});
	}

	/**
	 * Updates the spinner that lets the user choose a hotel room with values
	 * based on the value of the spinner that lets the user choose a hotel.
	 */
	private void addGetHotelRoomsListener() {
		BookRoomView bookRoomView = (BookRoomView) view.getViews().get(3);

		bookRoomView.getHotelsInput().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookRoomView.getRoomsInput().removeAllItems();
				bookRoomView.getRoomsInput().addItem("Select a room");

				if (bookRoomView.getHotelsInput().getSelectedItem() != null) {
					if (!bookRoomView.getHotelsInput().getSelectedItem().toString().equals("Select a hotel")) {
						for (Room room : rSystem.getHotel(bookRoomView.getHotelsInput().getSelectedItem().toString()).getRooms()) {
							bookRoomView.getRoomsInput().addItem(room.getName());
						}
						bookRoomView.getRoomsInput().setEnabled(true);
					} else {
						bookRoomView.getRoomsInput().setEnabled(false);
					}
				}
			}
		});
	}
}
