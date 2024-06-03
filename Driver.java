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
					rSystem.createHotel(newHotel);
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
		} while(menuOption != 0);

		scanner.close();
	}

	/**
	 * Standardized way of printing a menu header.
	 * @param messageString Header title.
	 */
	private void printHeader(String messageString) {
		int length = messageString.length();

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
		boolean hotelFound = false;
		Hotel currentHotel;

		do {
			printHeader("View a hotel");
			System.out.print("Enter the name of the hotel you want to view, or '0' to exit: ");
			input = scanner.nextLine();

			for (int i = 0; i < rSystem.getHotels().size(); i++) {
				if (rSystem.getHotels().get(i).getName().equals(input)) {
					hotelFound = true;
					currentHotel = rSystem.getHotels().get(i);

					System.out.println("Name: " + currentHotel.getName());
					System.out.println("Rooms: " + currentHotel.getRoomCount());
					System.out.println("      Booked: " + currentHotel.getBookedRoomCount() + " Available: " + currentHotel.getAvailableRoomCount());
					System.out.println("Estimate Earnings: ₱" + currentHotel.getReservationCount() * currentHotel.getBasePrice() + "\n");

					break;
				}
			}

			if (!hotelFound) {
				System.out.println("[*]: No hotel was found with that name.");
			}
		} while (!input.equals("0"));
	}

	private void manageHotelMenu() {
		printHeader("Manage a hotel");

	}

	/**
	 * Lists all hotels stored in the system.
	 */
	private void listHotelsMenu() {
		Hotel currentHotel;
		int input;

		printHeader("All hotels");

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

	private void bookingMenu() {
		printHeader("Book a room");
	}
}
