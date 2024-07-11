import javax.swing.JButton;
import java.util.Iterator;

/**
 * MainController controls the logic of the main menu.
 */
public class MainController {
	private MainView view;
	private MenuModel model;

	public MainController(MainView view, MenuModel model) {
		this.view = view;
		this.model = model;

		Iterator<JButton> i = model.getMenuButtons().iterator();

		while (i.hasNext()) {
			JButton button = i.next();
			view.setMenuButton(button);
		}
	}
}
