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

			menuOption = scanner.nextInt();

			switch (menuOption) {
				case 0:
					break;
				case 1:
					Hotel newHotel = createHotelMenu(rSystem);
					rSystem.createHotel(newHotel);
					break;
				case 2:
					viewHotelMenu();
					break;
				case 3:
					manageHotelMenu();
					break;
				case 4:
					listHotelsMenu(rSystem.getHotels());
					break;
				default:
					System.out.println("[*]: Invalid input.");
					break;
			}
		} while(menuOption != 0);

		scanner.close();
	}

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
						roomCount = hotelCreateScanner.nextInt();
					} while (roomCount < 1 || roomCount > 50);

					page += 1;
					break;
				case 2:
					printHeader("Create a hotel - Base room price");
					do {
						System.out.print("Enter the base price of the rooms in the hotel: ");
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

	private void viewHotelMenu() {
		printHeader("View a hotel");

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
			input = listScanner.nextInt();
		} while (input != 0);
	}
}
