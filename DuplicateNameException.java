/**
 * DuplicateNameException is thrown when an attempt is made to
 * create a hotel with an existing name.
 */
public class DuplicateNameException extends Exception {
	/**
	 * Constructor that makes a DuplicateNameException.
	 */
	public DuplicateNameException() {
		super("A hotel with that name alreaady exists.");
	}
}
