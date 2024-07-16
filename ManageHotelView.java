import javax.swing.JLabel;
import javax.swing.JPanel;

public class ManageHotelView extends JPanel {
	UI ui = new UI();

	public ManageHotelView() {
		JLabel header = new JLabel("Manage Hotel");
		header.setFont(ui.HEADER_FONT);
		
		setBackground(ui.BG_MAIN);
		add(header);
	}
}
