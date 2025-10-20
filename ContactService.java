// manages a collection of contact objects in-memory.

import java.util.HashMap; // Import statements from Java's library to be used.
import java.util.Map;
import java.util.NoSuchElementException;

public class ContactService {

    private final Map<String, Contact> contacts = new HashMap<>(); // only this class can see it.

    // adds new contact into data only if it isn't null and ID hasn't been used before.
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null.");
        }
        String id = contact.getContactId();
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("Duplicate contact ID: " + id);
        }
        contacts.put(id, contact);
    }

    // shortcut method; add, delete, look up, or update.
    public void addContact(String contactId, String firstName, String lastName, String phone, String address) {
        addContact(new Contact(contactId, firstName, lastName, phone, address));
    }

    
    public boolean deleteContact(String contactId) {
        return contacts.remove(contactId) != null;
    }

    
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }

    
    public void updateFirstName(String contactId, String newFirstName) {
        Contact c = requireContact(contactId);
        c.setFirstName(newFirstName);
    }

    public void updateLastName(String contactId, String newLastName) {
        Contact c = requireContact(contactId);
        c.setLastName(newLastName);
    }

    public void updatePhone(String contactId, String newPhone) {
        Contact c = requireContact(contactId);
        c.setPhone(newPhone);
    }

    public void updateAddress(String contactId, String newAddress) {
        Contact c = requireContact(contactId);
        c.setAddress(newAddress);
    }

    private Contact requireContact(String id) {
        Contact c = contacts.get(id);
        if (c == null) {
            throw new NoSuchElementException("No contact with ID: " + id);
        }
        return c;
    }
}