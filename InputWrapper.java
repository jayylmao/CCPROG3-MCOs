import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 * JPanel that contains anything regarding user input
 */
public class InputWrapper extends JPanel {
	/**
	 * Constructor that creates an InputWrapper.
	 */
	public InputWrapper() {
		super(new FlowLayout(FlowLayout.LEFT));
		setBackground(UI.BG_MAIN);
		setAlignmentX(LEFT_ALIGNMENT);
	}
}
