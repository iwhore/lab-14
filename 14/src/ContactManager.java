import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private List<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
    }

    public boolean addContact(Contact contact) {
        for (Contact c : contacts) {
            if (c.getName().equals(contact.getName())) {
                return false; // Ім'я вже існує
            }
        }
        contacts.add(contact);
        return true;
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}