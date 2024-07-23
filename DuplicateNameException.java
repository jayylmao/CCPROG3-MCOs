public class DuplicateNameException extends Exception {
	public DuplicateNameException() {
		super("A hotel with that name alreaady exists.");
	}
}
