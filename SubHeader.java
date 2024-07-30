/**
 * SubHeader segments portions of a View with multiple controls by summarizing
 * what a control does.
 */
public class SubHeader extends Text {
	public SubHeader() {
		super();
		setFont(UI.SUBHEADER_FONT);
	}
	
	public SubHeader(String message) {
		super(message);
		setFont(UI.SUBHEADER_FONT);
	}
}
