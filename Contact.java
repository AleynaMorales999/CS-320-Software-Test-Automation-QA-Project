// Implements a Contact domain object with validation.

public class Contact {

    private final String contactId; // made contactID to never be reassigned and only accessed inside class itself.
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        // validate and assign all fields
        this.contactId = validateId(contactId); // checks the rules (not null no longer than 10 characters)
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
    }

    private String validateId(String id) { // checks and gives back valid ID
        if (id == null) {
            throw new IllegalArgumentException("ContactID cannot be null.");
        }
        if (id.length() > 10) {
            throw new IllegalArgumentException("ContactID cannot be longer than 10 characters.");
        }
        return id;
    }

    private String validateName(String name, String field) { // a reusable rule-checker for names.
        if (name == null) {
            throw new IllegalArgumentException(field + " cannot be null.");
        }
        if (name.length() > 10) {
            throw new IllegalArgumentException(field + " cannot be longer than 10 characters.");
        }
        return name;
    }

    private String validatePhone(String phone) { // makes sure phone number isn't missing and is 10 digits long (no symbols)
        if (phone == null) {
            throw new IllegalArgumentException("Phone cannot be null.");
        }
        if (!phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone must be exactly 10 digits.");
        }
        return phone;
    }

    private String validateAddress(String address) { // makes sure address isn't missing and no longer than 30 characters.
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null.");
        }
        if (address.length() > 30) {
            throw new IllegalArgumentException("Address cannot be longer than 30 characters.");
        }
        return address;
    }

    // Method for data to be readable, but not changed.
    public String getContactId() { 
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // Allows to update a contact's info later, only if new values pass rules.
    public void setFirstName(String firstName) {
        this.firstName = validateName(firstName, "First Name");
    }

    public void setLastName(String lastName) {
        this.lastName = validateName(lastName, "Last Name");
    }

    public void setPhone(String phone) {
        this.phone = validatePhone(phone);
    }

    public void setAddress(String address) {
        this.address = validateAddress(address);
    }
}
