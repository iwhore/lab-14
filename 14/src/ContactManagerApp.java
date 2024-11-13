import java.awt.*;
import java.awt.event.*;

public class ContactManagerApp extends Frame {
    private TextField nameField;
    private TextField phoneField;
    private List contactList;
    private Label messageLabel;
    private ContactManager contactManager;

    public ContactManagerApp() {
        contactManager = new ContactManager();

        setLayout(new FlowLayout());

        add(new Label("Ім'я:"));
        nameField = new TextField(20);
        add(nameField);

        add(new Label("Телефон:"));
        phoneField = new TextField(20);
        add(phoneField);

        Button addButton = new Button("Додати");
        add(addButton);

        contactList = new List();
        add(contactList);
        
        Button deleteButton = new Button("Видалити");
        add(deleteButton);

        messageLabel = new Label();
        add(messageLabel);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String phoneNumber = phoneField.getText().trim();
                
                if (name.isEmpty() || phoneNumber.isEmpty()) {
                    messageLabel.setText("Заповніть всі поля.");
                    return;
                }

                Contact contact = new Contact(name, phoneNumber);
                if (contactManager.addContact(contact)) {
                    contactList.add(contact.toString());
                    messageLabel.setText("Контакт додано!");
                } else {
                    messageLabel.setText("Контакт з таким іменем вже існує.");
                }

                nameField.setText("");
                phoneField.setText("");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    String selectedContact = contactList.getItem(selectedIndex);
                    String contactName = selectedContact.split(":")[0];
                    Contact contactToRemove = null;

                    for (Contact contact : contactManager.getContacts()) {
                        if (contact.getName().equals(contactName)) {
                            contactToRemove = contact;
                            break;
                        }
                    }

                    if (contactToRemove != null) {
                        contactManager.removeContact(contactToRemove);
                        contactList.remove(selectedIndex);
                        messageLabel.setText("Контакт видалено!");
                    }
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        setSize(300, 400);
        setTitle("Менеджер контактів");
        setVisible(true);
    }

    public static void main(String[] args) {
        new ContactManagerApp();
    }
}
