import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
			ActionListener al;

			switch (button.getText()) {
				case "Create Hotel" -> al = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("create hotel");
					}
				};
				case "View Hotel" -> al = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("view hotel");
					}
				};
				case "Manage Hotel" -> al = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("manage hotel");
					}
				};
				case "Book Room" -> al = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("book room");
					}
				};
				default -> al = null;
			}

			view.setMenuButton(button, al);
		}
	}
}
