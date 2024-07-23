import javax.swing.JLabel;

public class Text extends JLabel {
	public Text() {
		super();
		setFont(UI.TEXT_FONT);
		setForeground(UI.FG_MAIN);
		setAlignmentX(LEFT_ALIGNMENT);
	}
	
	public Text(String message) {
		super(message);
		setFont(UI.TEXT_FONT);
		setForeground(UI.FG_MAIN);
		setAlignmentX(LEFT_ALIGNMENT);
	}
}