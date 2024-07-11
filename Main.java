/**
 * Main is the driver class of the program.
 */
public class Main {
	public static void main(String[] args) {
		MainView view = new MainView();
		MenuModel model = new MenuModel();

		MainController controller = new MainController(view, model);
	}
}
