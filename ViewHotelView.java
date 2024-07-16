import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewHotelView extends JPanel {
	UI ui = new UI();

	public ViewHotelView() {
		JLabel header = new JLabel("View Hotel");
		header.setFont(ui.HEADER_FONT);

		setBackground(ui.BG_MAIN);
		add(header);
	}
}
