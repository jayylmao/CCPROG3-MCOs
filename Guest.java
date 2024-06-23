/**
 * The Guest class is a class representing a Guest willing to book into a hotel
 */
public class Guest {
    /** The first name of the guest. */
    private String firstName;

    /** The last name of the guest. */
	private String lastName;

    /**
	 * Constructor that creates a Guest.
	 * @param firstName First name of the Guest.
	 * @param lastName Last name of the Guest.
	 */
    public Guest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
	 * Sets the first name of the Guest.
	 * @param firstName First name of the Guest.
	 */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
	 * Sets the last name of the Guest.
	 * @param lastName Last name of the Guest.
	 */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
	 * Gets the first name of the Guest.
	 * @return Guest's first name.
	 */
    public String getFirstName() {
        return this.firstName;
    }

    /**
	 * Gets the last name of the Guest.
	 * @return Guest's last name.
	 */
    public String getLastName() {
        return this.lastName;
    }

    /**
	 * Gets the full name of the Guest, including first and last names combines as a String.
	 * @return Guest's full name.
	 */
    public String getName() {
		return this.firstName + " " + this.lastName;
	}
}
