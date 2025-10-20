// Unit tests for ContactService using JUnit 5.

// Imports the testing library JUnit 5, giving the @Test annotation.
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

public class ContactServiceTest {

    // confirms the requirement that IDs must be unique and no duplicates are allowed.
    @Test
    void addContactRequiresUniqueId() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "A", "B", "1111111111", "addr"));
        assertThrows(IllegalArgumentException.class, () ->
            service.addContact(new Contact("1", "C", "D", "2222222222", "addr2"))
        );
    }

    // verifies deleting values and  what's returned works properly.
    @Test
    void deleteContactById() {
        ContactService service = new ContactService();
        service.addContact("2", "A", "B", "1111111111", "addr");
        assertTrue(service.deleteContact("2"));
        assertFalse(service.deleteContact("2")); 
        assertNull(service.getContact("2"));
    }

    // checks contact and confirms match per ID, making sure they save in data.
    @Test
    void updateFieldsById() {
        ContactService service = new ContactService();
        service.addContact("3", "A", "B", "1111111111", "addr");
        service.updateFirstName("3", "Alice");
        service.updateLastName("3", "Brown");
        service.updatePhone("3", "1234567890");
        service.updateAddress("3", "123 Main St");
        Contact c = service.getContact("3");
        assertEquals("Alice", c.getFirstName());
        assertEquals("Brown", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main St", c.getAddress());
    }

    // ensures user is aware clearly that the ID they typed in doesn't exist.
    @Test
    void updatingNonexistentIdThrows() {
        ContactService service = new ContactService();
        assertThrows(NoSuchElementException.class, () -> service.updateFirstName("missing", "X"));
        assertThrows(NoSuchElementException.class, () -> service.updateLastName("missing", "Y"));
        assertThrows(NoSuchElementException.class, () -> service.updatePhone("missing", "1234567890"));
        assertThrows(NoSuchElementException.class, () -> service.updateAddress("missing", "Z St"));
    }

    // checks contact and verifies it exists with expected first name.
    @Test
    void addContactOverloadWorks() {
        ContactService service = new ContactService();
        service.addContact("4", "John", "Doe", "5555555555", "Somewhere Ave");
        Contact c = service.getContact("4");
        assertNotNull(c);
        assertEquals("John", c.getFirstName());
    }
}