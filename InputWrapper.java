import java.awt.FlowLayout;

import javax.swing.JPanel;

public class InputWrapper extends JPanel {
	public InputWrapper() {
		super(new FlowLayout(FlowLayout.LEFT));
		setBackground(UI.BG_MAIN);
		setAlignmentX(LEFT_ALIGNMENT);
	}
}
