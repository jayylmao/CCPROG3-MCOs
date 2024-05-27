import java.util.Scanner;
import java.util.ArrayList;

/**
 * The Driver class displays the user interface and takes interactions from the user.
 */
public class Driver {
	/**
	 * Instantiates the driver class and invokes the main menu.
	 * @param args
	 */
	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.mainMenu();
	}

	/**
	 * The main menu that the user interacts with.
	 */
	private void mainMenu() {
		HotelReservationSystem rSystem = new HotelReservationSystem();
		Scanner scanner = new Scanner(System.in);

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
				scanner.next();

				System.out.print("[/]: Select a menu option: ");
			}

			menuOption = scanner.nextInt();

			switch (menuOption) {
				case 0:
					break;
				case 1:
					Hotel newHotel = createHotelMenu(rSystem);
					rSystem.createHotel(newHotel);
					break;
				case 2:
					viewHotelMenu(rSystem.getHotels());
					break;
				case 3:
					manageHotelMenu();
					break;
				case 4:
					listHotelsMenu(rSystem.getHotels());
					break;
				case 5:
					bookingMenu(rSystem);
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
	 * @param rSystem Instance of the reservation system and its methods.
	 * @return Created hotel.
	 */
	private Hotel createHotelMenu(HotelReservationSystem rSystem) {
		Scanner hotelCreateScanner = new Scanner(System.in); // Closing this causes a NoSuchElement exception?
		String name = "";
		int roomCount = 0;
		float basePrice = 0;
		int reviewInput;

		int page = 0;

		// Each page is represented as a case.
		do {
			switch (page) {
				case 0:
					printHeader("Create a hotel - Name");
					do {
						System.out.print("Enter the name of the hotel: ");
						name = hotelCreateScanner.nextLine();

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

						while (!hotelCreateScanner.hasNextInt()) {
							System.out.println("[*]: Enter a valid integer from 0 - 50.");
							hotelCreateScanner.next();
						}

						roomCount = hotelCreateScanner.nextInt();
					} while (roomCount < 1 || roomCount > 50);

					page += 1;
					break;
				case 2:
					printHeader("Create a hotel - Base room price");
					do {
						System.out.print("Enter the base price of the rooms in the hotel: ");

						while (!hotelCreateScanner.hasNextFloat()) {
							System.out.println("[*]: Enter a valid number greater than or equal to 100.");
							hotelCreateScanner.next();
						}

						basePrice = hotelCreateScanner.nextFloat();
					} while (basePrice < 100.0);

					page += 1;
					break;
				case 3:
					printHeader("Create a hotel - Review details");
					do {
						System.out.println("Name: " + name);
						System.out.println("Number of rooms: " + roomCount);
						System.out.println("Base room price: ₱" + basePrice);

						System.out.print("[/]: Input '0' to exit: ");

						while (!hotelCreateScanner.hasNextInt()) {
							System.out.println("[*]: Enter a '0' or one of the menu options.");
							hotelCreateScanner.next();
						}

						reviewInput = hotelCreateScanner.nextInt();
					} while (reviewInput != 0);

					page += 1;
					break;
				default:
					break;
			}
		} while (page < 4);

		return new Hotel(name, roomCount, basePrice);
	}

	/**
	 * Shows the user a menu to view a hotel.
	 */
	private void viewHotelMenu(ArrayList<Hotel> hotels) {
		Scanner viewMenuScanner = new Scanner(System.in);
		String input;
		boolean hotelFound = false;
		Hotel currentHotel;

		do {
			printHeader("View a hotel");
			System.out.print("Enter the name of the hotel you want to view, or '0' to exit: ");
			input = viewMenuScanner.nextLine();

			for (int i = 0; i < hotels.size(); i++) {
				if (hotels.get(i).getName().equals(input)) {
					hotelFound = true;
					currentHotel = hotels.get(i);

					System.out.println("Name: " + currentHotel.getName());
					System.out.println("Rooms: " + currentHotel.getRoomCount());
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

	private void listHotelsMenu(ArrayList<Hotel> hotels) {
		Scanner listScanner = new Scanner(System.in);
		int input;

		printHeader("All hotels");

		for (int i = 0; i < hotels.size(); i++) {
			System.out.print(String.format("[%02d.] ", i + 1));
			System.out.println("Name: " + hotels.get(i).getName());
			System.out.println("      Rooms: " + hotels.get(i).getRoomCount());
			System.out.println("      Base price: ₱" + hotels.get(i).getBasePrice() + "\n");
		}

		do {
			System.out.print("[/]: Input '0' to exit: ");

			while (!listScanner.hasNextInt()) {
				System.out.println("[*]: Enter a '0' to go back to the menu.");
				listScanner.next();
			}

			input = listScanner.nextInt();
		} while (input != 0);
	}

	private void bookingMenu(HotelReservationSystem rSystem) {
		printHeader("Booking");
	}
}
