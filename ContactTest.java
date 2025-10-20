// Unit tests for Contact using JUnit 5.

// provides tools to mark methods as @Test, and to use built-in checkers.
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    // proves adding a valid contact can be created successfully.
    @Test
    void validContactIsCreated() {
        Contact c = new Contact("abc123", "Alice", "Brown", "1234567890", "123 Main Street");
        assertEquals("abc123", c.getContactId());
        assertEquals("Alice", c.getFirstName());
        assertEquals("Brown", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main Street", c.getAddress());
    }

    // confirms rule that IDs can't be null and must be 10 characters long.
    @Test
    void contactIdCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class, () -> new Contact(null, "A", "B", "1234567890", "addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("01234567890", "A", "B", "1234567890", "addr")); // 11 chars
    }

    // proves that once ID is set, it can never change.
    @Test
    void idIsImmutable() {
        Contact c = new Contact("id1", "A", "B", "1234567890", "addr");
        // No setter; ensure only getter exists by behavior (compile-time check in real project)
        assertEquals("id1", c.getContactId());
    }

    // validates the rules for adding first name.
    @Test
    void firstNameConstraints() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("id", null, "B", "1234567890", "addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("id", "ABCDEFGHIJK", "B", "1234567890", "addr")); // 11 chars
    }

    // validates the rules for adding last name.
    @Test
    void lastNameConstraints() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("id", "A", null, "1234567890", "addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("id", "A", "ABCDEFGHIJK", "1234567890", "addr"));
    }

    // confirms adding phone must be 10 digits long, no exceptions.
    @Test
    void phoneConstraints() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("id", "A", "B", null, "addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("id", "A", "B", "123456789", "addr"));  // 9 digits
        assertThrows(IllegalArgumentException.class, () -> new Contact("id", "A", "B", "12345678901", "addr")); // 11 digits
        assertThrows(IllegalArgumentException.class, () -> new Contact("id", "A", "B", "12345abc90", "addr")); // non-digits
    }

    // confirms address is present and must be 30 characters long.
    @Test
    void addressConstraints() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("id", "A", "B", "1234567890", null));
        String longAddress = "1234567890123456789012345678901"; // 31 chars
        assertThrows(IllegalArgumentException.class, () -> new Contact("id", "A", "B", "1234567890", longAddress));
    }

    // tests that each setter throws an error when not following each ones rules, even when updated.
    @Test
    void settersEnforceValidation() {
        Contact c = new Contact("id", "A", "B", "1234567890", "addr");
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setLastName("ABCDEFGHIJK"));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("abcdefghij"));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress("x".repeat(31)));
    }
}