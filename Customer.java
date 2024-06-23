/**
 * The Customer class is a class representing a customer willing to book into a hotel
 */
public class Customer {
    /** The first name of the guest. */
    private String firstName;

    /** The last name of the guest. */
	private String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getName() {
		return this.firstName + " " + this.lastName;
	}
}
