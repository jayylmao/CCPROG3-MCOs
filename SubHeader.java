/**
 * SubHeader segments portions of a View with multiple controls by summarizing
 * what a control does.
 */
public class SubHeader extends Text {
	/**
	 * Constructor that creates a SubHeader.
	 */
	public SubHeader() {
		super();
		setFont(UI.SUBHEADER_FONT);
	}
	
	/**
	 * Constructor that creates a SubHeader.
	 * @param message message that the SubHeader will show.
	 */
	public SubHeader(String message) {
		super(message);
		setFont(UI.SUBHEADER_FONT);
	}
}
