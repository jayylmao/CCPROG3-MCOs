import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookRoomView extends JPanel {
	UI ui = new UI();

	public BookRoomView() {
		JLabel header = new JLabel("Book Room");
		header.setFont(ui.HEADER_FONT);

		setBackground(ui.BG_MAIN);
		add(header);
	}
}
