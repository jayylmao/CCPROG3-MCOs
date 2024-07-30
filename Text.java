import javax.swing.JLabel;

/**
 * Text is used for descriptions and smaller outputs.
 */
public class Text extends JLabel {
	/**
	 * Constructor that initializes the Text class and it's base design.
	 */
	public Text() {
		super();
		setFont(UI.TEXT_FONT);
		setForeground(UI.FG_MAIN);
		setAlignmentX(LEFT_ALIGNMENT);
	}
	
	/**
	 * Constructor that initializes the Text class
	 * @param message message that the Text will show.
	 */
	public Text(String message) {
		super(message);
		setFont(UI.TEXT_FONT);
		setForeground(UI.FG_MAIN);
		setAlignmentX(LEFT_ALIGNMENT);
	}
}
