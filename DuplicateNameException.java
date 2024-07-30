/**
 * DuplicateNameException is thrown when an attempt is made to
 * create a hotel with an existing name.
 */
public class DuplicateNameException extends Exception {
	public DuplicateNameException() {
		super("A hotel with that name alreaady exists.");
	}
}
