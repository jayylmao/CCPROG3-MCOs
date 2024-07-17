import javax.swing.JLabel;
import javax.swing.JPanel;

public class ManageHotelView extends JPanel {

	public ManageHotelView() {
		JLabel header = new JLabel("Manage Hotel");
		header.setFont(UI.HEADER_FONT);
		
		setBackground(UI.BG_MAIN);
		add(header);
	}
}
