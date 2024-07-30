/**
 * Generic Header class that shows large text.
 */
public class Header extends Text {
	/**
	 * Constructor that creates a header.
	 */
	public Header() {
		super();
		setFont(UI.HEADER_FONT);
	}

	/**
	 * Constructor that creates a header.
	 * @param message message that the header will show.
	 */
	public Header(String message) {
		super(message);
		setFont(UI.HEADER_FONT);
	}
}
