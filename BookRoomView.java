import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookRoomView extends JPanel {

	public BookRoomView() {
		JLabel header = new JLabel("Book Room");
		header.setFont(UI.HEADER_FONT);

		setBackground(UI.BG_MAIN);
		add(header);
	}
}
