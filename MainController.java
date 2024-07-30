import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
							mainLayout.show(contentPanel, viewNames.get(idx));
							viewHotelView.hideOutput();
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
							mainLayout.show(contentPanel, viewNames.get(idx));
							manageHotelView.hideOutput();
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
							bookRoomView.hideOutput();
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
		addCheckReservationListener();
		addGetReservationsListener();
		
		addManageHotelListener();
		addChangeHotelNameListener();
		addAddRoomsListener();
		addRemoveRoomsListener();
		addUpdateBasePriceListener();
		addDeleteReservationListener();
		addDeleteReservationButtonListener();
		addDeleteHotelListener();
		addDatePriceModifierListener();
		addDatePriceModifierButtonListener();
		
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

					for (Room room : foundHotel.getRooms()) {
						viewHotelView.getReservationInfoRoom().addItem(room.getName());
					}
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
				String roomName = (String) viewHotelView.getCheckRoomInfoInput().getSelectedItem();
				
				Room room = currentHotel.getRoom(roomName);

				if (room != null) {
					try {
						viewHotelView.setRoomInfoName(roomName);
						for (int i = 1; i < 31; i++) {
							viewHotelView.getRoomInfoCalendar().updateTileInfo(i, i, currentHotel.getDatePriceModifier(i) * currentHotel.getRoom(roomName).getRoomPrice());

							Date checkOutDate = new Date(i + 1);
							checkOutDate.setHour(12);

							if (room.isOccupied(new Date(i), checkOutDate)) {
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
	 * Gets reservations from the system based on the selected room number.
	 */
	private void addGetReservationsListener() {
		ViewHotelView viewHotelView = (ViewHotelView) view.getViews().get(1);

		viewHotelView.getReservationInfoRoom().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hotel currentHotel = viewHotelView.getCurrentHotel();
				String roomName = (String) viewHotelView.getReservationInfoRoom().getSelectedItem();

				if (roomName != null) {
					viewHotelView.getReservationInfoReservation().removeAllItems();
					viewHotelView.getReservationInfoReservation().addItem("Select a reservation");
					if (!roomName.equals("Select a room")) {
						for (Reservation reservation : currentHotel.getRoom(roomName).getReservations()) {
							viewHotelView.getReservationInfoReservation().addItem(reservation.getGuests().get(0).getName());
						}
					}
				}
			}
		});
	}

	/**
	 * Connects the "Check reservation info" button to the model.
	 */
	private void addCheckReservationListener() {
		ViewHotelView viewHotelView = (ViewHotelView) view.getViews().get(1);

		viewHotelView.getReservationInfoButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hotel currentHotel = viewHotelView.getCurrentHotel();
				String roomName = (String) viewHotelView.getReservationInfoRoom().getSelectedItem();
				String reservationName = (String) viewHotelView.getReservationInfoReservation().getSelectedItem();

				Reservation reservation;

				if (roomName.equals("Select a room") || reservationName.equals("Select a reservation")) {
					viewHotelView.showError("You must select a room and a reservation.");
				} else {
					reservation = rSystem.getHotel(currentHotel.getName()).getRoom(roomName).getReservation(reservationName);
					viewHotelView.setReservationName(reservation.getGuests().get(0).getName());
					viewHotelView.setReservationRoomName(roomName);
					viewHotelView.setReservationPrice(reservation.getTotalPrice());
					viewHotelView.setCheckInOutDates(reservation.getCheckIn(), reservation.getCheckOut());

					for (int i = 1; i < 31; i++) {
						viewHotelView.getReservationCalendar().markTileFree(i);
					}

					for (int i = reservation.getCheckIn().getDay(); i < reservation.getCheckOut().getDay(); i++) {
						viewHotelView.getReservationCalendar().markTileOccupied(i);
						viewHotelView.getReservationCalendar().updateTileInfo(i, i, currentHotel.getBasePrice() * currentHotel.getDatePriceModifier(i));
					}

					viewHotelView.getReservationCalendar().setVisible(true);
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
				
				manageHotelView.getDeleteReservationRoom().removeAllItems();
				manageHotelView.getDeleteReservationRoom().addItem("Select a room");
				for (Room room : foundHotel.getRooms()) {
					// System.out.println(room.getName());
					manageHotelView.getDeleteReservationRoom().addItem(room.getName());
				}

				manageHotelView.showResult(foundHotel);
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
						manageHotelView.updateRooms(currentHotel);
					} else {
						manageHotelView.showMessageDialog(roomType + "s were added successfully.");
						manageHotelView.updateRooms(currentHotel);
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
				String roomName = (String) manageHotelView.getRemoveRoomsInput().getSelectedItem();

				try {
					currentHotel.removeRoom(roomName);
					manageHotelView.removeRoom(roomName);
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
	 * Updates the "Manage hotel" view's price modifier spinner
	 * based on the corresponding date in the date spinner.
	 */
	private void addDatePriceModifierListener() {
		ManageHotelView manageHotelView = (ManageHotelView) view.getViews().get(2);

		manageHotelView.getDatePriceDate().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Hotel currentHotel = manageHotelView.getCurrentHotel();
				int date = (int) manageHotelView.getDatePriceDate().getValue();
				manageHotelView.getDatePriceModifier().setValue(currentHotel.getDatePriceModifier(date));
			}
		});
	}

	/**
	 * Updates the date price modifier based on the selected date and modifier.
	 */
	private void addDatePriceModifierButtonListener() {
		ManageHotelView manageHotelView = (ManageHotelView) view.getViews().get(2);

		manageHotelView.getDatePriceModifierButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hotel currentHotel = manageHotelView.getCurrentHotel();
				int date = (int) manageHotelView.getDatePriceDate().getValue();
				double modifier = (double) manageHotelView.getDatePriceModifier().getValue();

				currentHotel.setDatePriceModifier(date, modifier);
			}
		});
	}

	/**
	 * Updates the dropdown box of reservations in the "Manage hotel" screen
	 * based on the selected room.
	 */
	private void addDeleteReservationListener() {
		ManageHotelView manageHotelView = (ManageHotelView) view.getViews().get(2);

		manageHotelView.getDeleteReservationRoom().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hotel currentHotel = manageHotelView.getCurrentHotel();
				String roomName = (String) manageHotelView.getDeleteReservationRoom().getSelectedItem();

				if (roomName != null) {
					if (!roomName.equals("Select a room")) {
						manageHotelView.getDeleteReservationName().removeAllItems();
						manageHotelView.getDeleteReservationName().addItem("Select a reservation");
						
						for (Reservation reservation : currentHotel.getRoom(roomName).getReservations()) {
							manageHotelView.getDeleteReservationName().addItem(reservation.getGuests().get(0).getName());
						}
					}
				}
			}
		});
	}

	/**
	 * Connects the "Delete reservation" button found in the "Manage hotel"
	 * screen to the model.
	 */
	private void addDeleteReservationButtonListener() {
		ManageHotelView manageHotelView = (ManageHotelView) view.getViews().get(2);

		manageHotelView.getDeleteReservationButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hotel currentHotel = manageHotelView.getCurrentHotel();
				String roomName = (String) manageHotelView.getDeleteReservationRoom().getSelectedItem();
				String reservationName = (String) manageHotelView.getDeleteReservationName().getSelectedItem();

				if (roomName != null) {
					if (roomName.equals("Select a room") || roomName.equals("Select a reservation")) {
						manageHotelView.showError("You must select both a room and a reservation.");
					} else {
						try {
							manageHotelView.getDeleteReservationName().removeItem(manageHotelView.getDeleteReservationName().getSelectedIndex());
							currentHotel.getRoom(roomName).removeReservation(reservationName);
	
							manageHotelView.showMessageDialog("Reservation was removed successfully.");
						} catch (ReservationNotFoundException exception) {
							manageHotelView.showError("A reservation with that name was not found.");
						}
					}
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
						// the discount code,
						// and the hashmap coresponding to date price modifiers.
						try {
							Date checkIn, checkOut;
							checkIn = bookRoomView.getCheckInDate();
							checkOut = bookRoomView.getCheckOutDate();

							checkIn.setHour(14);
							checkOut.setHour(12);

							rSystem.getHotel((String) bookRoomView.getHotelsInput().getSelectedItem())
									.getRoom((String) bookRoomView.getRoomsInput().getSelectedItem())
									.reserveRoom(new Guest(bookRoomView.getFirstName(), bookRoomView.getLastName()), 
															checkIn, checkOut,
															rSystem.getHotel((String) bookRoomView.getHotelsInput().getSelectedItem()).getRoom((String) bookRoomView.getRoomsInput().getSelectedItem()).getRoomPrice(),
															bookRoomView.getDiscountCode(), rSystem.getHotel((String) bookRoomView.getHotelsInput().getSelectedItem()).getDatePriceModifiers());
							
							if (bookRoomView.getDiscountCode().equals("")) {
								bookRoomView.showMessageDialog("Your booking was successful.");
							} else {
								bookRoomView.showMessageDialog("Your booking was successful. Your discount code " + bookRoomView.getDiscountCode() + " was applied.");
							}
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
