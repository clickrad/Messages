import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactListPanel extends JPanel {

    private User user;
    private DefaultListModel<Contact> contactListModel;
    private JList<Contact> contactList;

    public ContactListPanel(User user) {
        this.user = user;
        setLayout(new BorderLayout());

        contactListModel = new DefaultListModel<>();
        contactList = new JList<>(contactListModel);
        loadContacts();

        JScrollPane scrollPane = new JScrollPane(contactList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        JButton addContactButton = new JButton("Add Contact");
        addContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });
        buttonsPanel.add(addContactButton);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void loadContacts() {
        for (Contact contact : user.getContacts()) {
            contactListModel.addElement(contact);
        }
    }

    private void addContact() {
        // Implement the logic for adding a contact here
        // You can show a dialog to get the contact's information and then add it to the user's contacts list
        // Finally, update the contactListModel to reflect the changes
    }
}
