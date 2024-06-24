import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Driver class displays the user interface and takes interactions from the user.
 */
public class Driver {
	/** Create instance of reservation system. */
	private HotelReservationSystem rSystem = new HotelReservationSystem();

	Scanner scanner;

	/**
	 * Instantiates the driver class and invokes the main menu.
	 * @param args Arguments for invoking the Driver class from the console.
	 */
	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.mainMenu();
	}

	/**
	 * The main menu that the user interacts with.
	 */
	private void mainMenu() {
		scanner = new Scanner(System.in);
		int menuOption;

		do {
			printHeader("Hotel Reservation System");
			System.out.println("[0.] Exit");
			System.out.println("Terminate the program.\n");

			System.out.println("[1.] Create hotel");
			System.out.println("Create and store a hotel in the reservation system.\n");

			System.out.println("[2.] View hotel");
			System.out.println("View information about a selected hotel.\n");

			System.out.println("[3.] Manage hotel");
			System.out.println("Modify the configuration of a hotel.\n");

			System.out.println("[4.] List hotels");
			System.out.println("Show a list of all hotels stored in the reservation system.\n");

			System.out.println("[5.] Book room");
			System.out.println("Book a specific room in a chosen hotel.\n");

			System.out.print("[/]: Select a menu option: ");

			// Input validation.
			while (!scanner.hasNextInt()) {
				System.out.println("[*]: Enter a number from 0 - 4.");
				scanner.nextLine();

				System.out.print("[/]: Select a menu option: ");
			}

			// Putting a nextInt() scanner and a nextLine() scanner next to one another
			// (nextLine() is used to get the name of a hotel in the create hotel menu),
			// it causes dropped inputs.
			menuOption = Integer.parseInt(scanner.nextLine());

			switch (menuOption) {
				case 0:
					break;
				case 1:
					Hotel newHotel = createHotelMenu();
					rSystem.addHotel(newHotel);
					break;
				case 2:
					viewHotelMenu();
					break;
				case 3:
					manageHotelMenu();
					break;
				case 4:
					listHotelsMenu();
					break;
				case 5:
					bookingMenu();
					break;
				default:
					System.out.println("[*]: Invalid input.");
					break;
			}
		} while (menuOption != 0);

		scanner.close();
	}

	/**
	 * Standardized way of printing a menu header.
	 * @param messageString Header title.
	 */
	private void printHeader(String messageString) {
		int length = messageString.length();

		System.out.print("\n");

		for (int i = 0; i < 9; i++) {
			System.out.print("=");
		}

		System.out.print("[ " + messageString + " ]");

		for (int i = 0; i < 56 - length; i++) {
			System.out.print("=");
		}

		System.out.print("\n");
	}

	/**
	 * Shows the user a menu to create a hotel.
	 * @return Created hotel.
	 */
	private Hotel createHotelMenu() {
		String name = "";
		int roomCount = 0;
		int reviewInput;

		int page = 0;

		// Each page is represented as a case.
		do {
			switch (page) {
				case 0:
					printHeader("Create a hotel - Name");
					do {
						System.out.print("Enter the name of the hotel: ");
						name = scanner.nextLine();

						if (rSystem.isDuplicate(name)) {
							System.out.println("This is a duplicate name.");
						}
					} while (rSystem.isDuplicate(name));

					page += 1;
					break;
				case 1:
					printHeader("Create a hotel - Room count");
					do {
						System.out.print("Enter the number of rooms in the hotel: ");

						while (!scanner.hasNextInt()) {
							System.out.println("[*]: Enter a valid integer from 0 - 50.");
							scanner.nextLine();
						}

						roomCount = Integer.parseInt(scanner.nextLine());
					} while (roomCount < 1 || roomCount > 50);

					page += 1;
					break;
				case 2:
					printHeader("Create a hotel - Review details");
					do {
						System.out.println("Name: " + name);
						System.out.println("Number of rooms: " + roomCount);

						System.out.print("[/]: Input '0' to exit: ");

						while (!scanner.hasNextInt()) {
							System.out.println("[*]: Enter a '0' or one of the menu options.");
							scanner.nextLine();
						}

						reviewInput = Integer.parseInt(scanner.nextLine());
					} while (reviewInput != 0);

					page += 1;
					break;
				default:
					break;
			}
		} while (page < 3);

		return new Hotel(name, roomCount);
	}

	/**
	 * Shows the user a menu to view a hotel.
	 */
	private void viewHotelMenu() {
		String input;
		Hotel hotel;

		do {
			printHeader("View a hotel");
			System.out.print("Enter the name of the hotel you want to view, or '0' to exit: ");
			input = scanner.nextLine();

			hotel = rSystem.getHotel(input);

			if (hotel == null) {
				System.out.println("[*]: No hotel was found with that name.");
			} else {
				System.out.println("Name: " + hotel.getName());
				System.out.println("      Rooms: " + hotel.getRoomCount());
				System.out.println("      Booked: " + hotel.getBookedRoomCount() + " Available: " + hotel.getAvailableRoomCount());
				System.out.println("      Base price: ₱" + hotel.getBasePrice() + "\n");
			}
		} while (!input.equals("0"));
	}

	/**
	 * Shows the submenu that lets the user manage individual hotels.
	 */
	private void manageHotelMenu() {
		Hotel currentHotel = new Hotel(null, 0);
		String input;
		int input2;

		// Return to main menu if no hotels are in the list.
		if (rSystem.getHotelCount() < 1) {
			System.out.println("[*]: There are no hotels registered in the system. Add one from the 'Create Hotel' menu. ");
			return;
		}

		printHeader("Select a hotel to manage");
		for (int i = 0; i < rSystem.getHotelCount(); i++) {
			currentHotel = rSystem.getHotels().get(i);
			System.out.print(String.format("[%02d.] ", i + 1));
			System.out.println("Name: " + currentHotel.getName());
			System.out.println("      Rooms: " + currentHotel.getRoomCount());
			System.out.println("      Booked: " + currentHotel.getBookedRoomCount() + " Available: " + currentHotel.getAvailableRoomCount());
			System.out.println("      Base price: ₱" + currentHotel.getBasePrice() + "\n");
		}

		do {
			System.out.print("Enter the name of the hotel you want to manage, or '0' to exit: ");
			input = scanner.nextLine();

			currentHotel = rSystem.getHotel(input);

			if (currentHotel == null && !input.equals("0")) {
				System.out.println("[*]: No hotel was found with that name.");
			}
		} while (currentHotel == null && !input.equals("0"));

		if (currentHotel != null && !input.equals("0")) {
			do {
				printHeader("Manage a hotel");
				System.out.println("[0.] Back to main menu");
				System.out.println("Go back to the main menu.\n");

				System.out.println("[1.] Change hotel name");
				System.out.println("Change the hotel's name.\n");

				System.out.println("[2.] Add rooms");
				System.out.println("Add more rooms to the hotel.\n");

				System.out.println("[3.] Remove rooms");
				System.out.println("Remove rooms from the hotel.\n");

				System.out.println("[4.] Update base price for a room");
				System.out.println("Change the base price for a room. Note that you can only do this if there are no reservations currently in the hotel.\n");

				System.out.println("[5.] Remove reservation");
				System.out.println("Remove a reservation from the hotel.\n");

				System.out.println("[6.] Remove hotel");
				System.out.println("Remove a hotel from being managed by the system.\n");

				System.out.print("[/]: Select a menu option: ");

				while (!scanner.hasNextInt()) {
					System.out.println("[*]: Select a menu option from 0 - 6. ");
					scanner.nextLine();
					System.out.print("[/]: Select a menu option: ");
				}

				input2 = Integer.parseInt(scanner.nextLine());

				switch (input2) {
					case 0:
						break;
					case 1:
						changeHotelName(currentHotel);
						break;
					case 2:
						addRooms(currentHotel);
						break;
					case 3:
						removeRooms(currentHotel);
						break;
					case 4:
						updateBasePrice(currentHotel);
						break;
					case 5:
						// removeReservation();
						break;
					case 6:
						// removeHotel();
						break;
					default:
						System.out.println("[*]: Enter a number from 0 - 6.");
						break;
				}
			} while (input2 != 0);
		}
	}

	/**
	 * Ask the user for a new hotel name and set it.
	 * @param hotel Hotel to change name of.
	 */
	private void changeHotelName(Hotel hotel) {
		String name;

		printHeader("Change hotel name");
		System.out.println("Current name: " + hotel.getName());
		System.out.print("Enter new name or enter '0' to go back: ");

		name = scanner.nextLine();

		if (!name.equals("0")) {
			rSystem.changeHotelName(hotel, name);
		}
	}

	/**
	 * addRooms() asks the user how many rooms they want to add to a given hotel.
	 * @param hotel Hotel to add room/s to.
	 */
	private void addRooms(Hotel hotel) {
		int count;
		boolean validInput = false;

		printHeader("Add rooms to hotel");
		System.out.println("Current rooms in hotel: " + hotel.getRoomCount());
		System.out.print("How many rooms do you want to add? Enter '0' to go back: ");

		do {
			// Input validation.
			while (!scanner.hasNextInt()) {
				System.out.println("[*]: Enter a valid positive integer. The total cannot exceed 50.");
				scanner.nextLine();
			}

			count = Integer.parseInt(scanner.nextLine());

			if (hotel.getRoomCount() + count > 50) {
				System.out.println("[*]: Enter a valid positive integer. The total cannot exceed 50.");
			} else {
				validInput = rSystem.addRooms(hotel, count);
			}
		} while (!validInput && count != 0);
	}

	/**
	 * removeRooms() asks the user to enter the name of a room to remove.
	 * @param hotel
	 */
	private void removeRooms(Hotel hotel) {
		boolean successfullyRemovedRoom = false;
		String roomName;

		printHeader("Remove rooms from hotel");

		for (int i = 0; i < hotel.getRoomCount(); i++) {
			System.out.print("| " + hotel.getRoom(i).getName());
			if (hotel.getRoom(i).isOccupied()) {
				System.out.print(" X ");
			} else {
				System.out.print("   ");
			}

			if ((i + 1) % 10 == 0) {
				System.out.println(" |");
			}
		}

		System.out.print("|");

		do {
			System.out.print("\nSelect a room not marked as occupied to remove, or enter '0' to exit: ");
			roomName = scanner.nextLine();
			successfullyRemovedRoom = rSystem.removeRoom(hotel, roomName);

			if (!successfullyRemovedRoom && !roomName.equals("0")) {
				System.out.println("[*]: Enter a valid room to remove.");
			}
		} while (!successfullyRemovedRoom && !roomName.equals("0"));
	}

	/**
	 * updateBasePrice() changes the base price of a given hotel.
	 * @param hotel Hotel to change base price of.
	 */
	private void updateBasePrice(Hotel hotel) {
		double basePrice;
		boolean successfullyChangedBasePrice = false;

		if (hotel.getBookedRoomCount() == 0) {
			do {
				printHeader("Update base price");
				System.out.println("Current price: ₱" + hotel.getBasePrice());
				System.out.print("Enter the new base price, or enter '0' to exit: ₱");
				// Input validation.
				while (!scanner.hasNextInt()) {
					System.out.println("[*]: Enter a valid positive integer greater than 100.");
					scanner.nextLine();
					System.out.print("Enter the new base price: ₱");
				}

				basePrice = Double.parseDouble(scanner.nextLine());

				successfullyChangedBasePrice = rSystem.updateBasePrice(hotel, basePrice);

				if (!successfullyChangedBasePrice && basePrice != 0) {
					System.out.println("[*]: Enter a valid positive integer greater than 100.");
				}
			} while (!successfullyChangedBasePrice && basePrice != 0);
		} else {
			System.out.println("[*]: You cannot update the base price of the hotel while there are reservations.");
			return;
		}
	}

	/**
	 * listHotelsMenu() lists all hotels stored in the system.
	 */
	private void listHotelsMenu() {
		Hotel currentHotel;
		int input;

		printHeader("All hotels");

		// Display available hotels.
		for (int i = 0; i < rSystem.getHotelCount(); i++) {
			currentHotel = rSystem.getHotels().get(i);
			System.out.print(String.format("[%02d.] ", i + 1));
			System.out.println("Name: " + currentHotel.getName());
			System.out.println("      Rooms: " + currentHotel.getRoomCount());
			System.out.println("      Booked: " + currentHotel.getBookedRoomCount() + " Available: " + currentHotel.getAvailableRoomCount());
			System.out.println("      Base price: ₱" + currentHotel.getBasePrice() + "\n");
		}

		do {
			System.out.print("[/]: Input '0' to exit: ");

			while (!scanner.hasNextInt()) {
				System.out.println("[*]: Enter a '0' to go back to the menu.");
				scanner.nextLine();
			}

			input = Integer.parseInt(scanner.nextLine());
		} while (input != 0);
	}

	/**
	 * bookingMenu() opens the menu that allows a user to book a specific room in a hotel during specified dates.
	 */
	private void bookingMenu() {
		Hotel hotel = null;
		Room room = null;
		Guest guest = null;
		Date checkInDate = null;
		Date checkOutDate = null;
		int step = 0;

		boolean finishBooking = false;

		do {
			switch (step) {
				case 0:
					printHeader("Enter your name");
					guest = setGuest();
					step += 1;
					break;
				case 1:
					printHeader("Choose a hotel");
					hotel = setHotel();
					step += 1;
					break;
				case 2:
					printHeader("Choose a check-in date");
					checkInDate = setCheckInDate();
					step += 1;
					break;
				case 3:
					printHeader("Choose a check-out date");
					checkOutDate = setCheckOutDate(checkInDate);
					step += 1;
					break;
				case 4:
					printHeader("Choose a room");
					room = setRoom(hotel, checkInDate, checkOutDate);
					step += 1;
					break;
				case 5:
					room.reserveRoom(guest, checkInDate, checkOutDate, hotel.getBasePrice() * checkInDate.getDayDifference(checkOutDate));
					printHeader("Review your details");
					System.out.println("Name          : " + guest.getName());
					System.out.println("Hotel         : " + hotel.getName());
					System.out.println("Room          : " + room.getName());
					System.out.println("Check-in date : " + checkInDate.getFormattedDate());
					System.out.println("Check-out date: " + checkOutDate.getFormattedDate());
					System.out.println("Total price   : ₱" + room.getReservation(room.getReservationCount() - 1).getReservedPrice());
					finishBooking = true;
				default:
					break;
			}
		} while (!finishBooking);

	}

	/**
	 * setGuest() asks the user for their first and last names and returns a Guest instance with those details.
	 * @return Instance of Guest with given details.
	 */
	private Guest setGuest() {
		String firstName;
		String lastName;

		System.out.print("Enter your first name: ");
		firstName = scanner.nextLine();

		System.out.print("Enter your last name: ");
		lastName = scanner.nextLine();

		return new Guest(firstName, lastName);
	}

	/**
	 * setHotel() asks the user for the name of a hotel and returns the corresponding hotel with that name.
	 * @return Hotel with name entered by user.
	 */
	private Hotel setHotel() {
		String hotelName;
		Hotel hotel;

		do {
			// Display available hotels.
			for (int i = 0; i < rSystem.getHotelCount(); i++) {
				hotel = rSystem.getHotels().get(i);
				System.out.print(String.format("[%02d.] ", i + 1));
				System.out.println("Name: " + hotel.getName());
				System.out.println("      Rooms: " + hotel.getRoomCount());
				System.out.println("      Booked: " + hotel.getBookedRoomCount() + " Available: " + hotel.getAvailableRoomCount());
				System.out.println("      Base price: ₱" + hotel.getBasePrice() + "\n");
			}

			System.out.print("Enter the name of a hotel to book: ");
			hotelName = scanner.nextLine();

			hotel = rSystem.getHotel(hotelName);

			if (hotel != null) {
				return hotel;
			} else {
				System.out.println("[*]: No hotel with that name was found.");
			}
		} while (hotel == null || hotel.getAvailableRoomCount() == 0);

		return hotel;
	}

	/**
	 * setRoom() returns the first
	 * @param hotel Hotel to get room list from.
	 * @return Room with name entered by user.
	 */
	private Room setRoom(Hotel hotel, Date checkInDate, Date checkOutDate) {
		Room room = null;
		String roomName;
		boolean successfullySetRoom = false;

		do {
			for (int i = 0; i < hotel.getRoomCount(); i++) {
				System.out.print("| " + hotel.getRoom(i).getName());
				if (!hotel.isRoomAvailable(hotel.getRoom(i).getName(), checkInDate, checkOutDate)) {
					System.out.print(" X ");
				} else {
					System.out.print("   ");
				}

				if ((i + 1) % 10 == 0) {
					System.out.print(" |");
				}
			}
			System.out.print("\nEnter a room number: ");
			roomName = scanner.nextLine();

			room = hotel.getRoom(roomName);

			if (room != null && hotel.isRoomAvailable(roomName, checkInDate, checkOutDate)) {
				successfullySetRoom = true;
				System.out.println("[/]: You have been assigned to room " + room.getName());
			} else if (room == null) {
				System.out.println("[*]: Invalid room name.");
			} else if (!hotel.isRoomAvailable(roomName, checkInDate, checkOutDate)) {
				System.out.println("[*]: Room + " + roomName + " is not available on your selected dates.");
			}
		} while (!successfullySetRoom);
		return room;
	}

	/**
	 * setCheckInDate() asks the user for the date they would like to check in on.
	 * @param room Room to check in to.
	 * @return Date that the user selected.
	 */
	private Date setCheckInDate() {
		Date date = null;
		int dateNumber;

		do {
			displayCalendar();
			System.out.print("Enter your check-in date: ");

			while (!scanner.hasNextInt()) {
				System.out.print("[*]: Enter a valid unbooked day from 0 - 31:");
				scanner.nextLine();
			}

			dateNumber = Integer.parseInt(scanner.nextLine());

			if (dateNumber > 1 || dateNumber < 31) {
				date = new Date(dateNumber);
				date.setHour(14);
				date.setMinute(0);
			} else {
				System.out.print("[*]: Enter a valid unbooked day from 0 - 31: ");
			}
		} while (date == null);
		return date;
	}

	/**
	 * setCheckOutDate() asks the user for the date they would like to check out on.
	 * @param room Room to check in to.
	 * @param checkInDate Date that the user will check in to the room on.
	 * @return Date that the user selected.
	 */
	private Date setCheckOutDate(Date checkInDate) {
		Date date = null;
		int dateNumber;

		do {
			displayCalendar(checkInDate);
			System.out.print("Enter your check-out date: ");

			while (!scanner.hasNextInt()) {
				System.out.print("[*]: Enter a valid unbooked day from 1 - 31 after your check-in date:");
				scanner.nextLine();
			}

			dateNumber = Integer.parseInt(scanner.nextLine());

			if ((dateNumber > 1 || dateNumber < 31) && new Date(dateNumber).isAfter(checkInDate)) {
				date = new Date(dateNumber);
				date.setHour(12);
				date.setMinute(0);
			} else {
				System.out.println("[*]: Enter a valid unbooked day from 1 - 31 after your check-in date. ");
			}
		} while (date == null);
		return date;
	}

	/**
	 * displayOccupancy() displays a calendar showing the dates available.
	 */
	private void displayCalendar() {
		for (int i = 1; i <= 31; i++) {
			System.out.print("| " + i + " ");
		}
		System.out.print("|\n");
	}

	/**
	 * displayCalendar() displays a calendar showing the dates available after a certain date.
	 * @param date Date to start after.
	 */
	private void displayCalendar(Date date) {
		Date currentDate;
		for (int i = 1; i <= 31; i++) {
			currentDate = new Date(i);

			if (currentDate.isBefore(date)) {
				System.out.print("| X ");
			} else {
				System.out.print("| " + i + " ");
			}
		}
		System.out.print("|\n");
	}
}
