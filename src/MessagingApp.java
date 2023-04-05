import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessagingApp extends JFrame {
    private User user;
    private JPanel mainPanel;
    private JList<Contact> contactList;
    private JList<Message> messageList;
    private JButton signOutButton;
    private JButton addContactButton;
    private JButton editContactButton;
    private JButton deleteContactButton;
    private JButton sendMessageButton;
    private JTextArea messageTextArea;
    private UserDatabase userDatabase;

    public MessagingApp(User user, UserDatabase userDatabase) {
        this.user = user;
        this.userDatabase = userDatabase;
        setTitle("Messaging App - " + user.getUsername());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        contactList = new JList<>(new DefaultListModel<>());
        mainPanel.add(new JScrollPane(contactList), BorderLayout.WEST);

        messageList = new JList<>(new DefaultListModel<>());
        mainPanel.add(new JScrollPane(messageList), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        signOutButton = new JButton("Sign Out");
        bottomPanel.add(signOutButton, BorderLayout.NORTH);

        addContactButton = new JButton("Add Contact");
        bottomPanel.add(addContactButton, BorderLayout.WEST);

        editContactButton = new JButton("Edit Contact");
        bottomPanel.add(editContactButton, BorderLayout.CENTER);

        deleteContactButton = new JButton("Delete Contact");
        bottomPanel.add(deleteContactButton, BorderLayout.EAST);

        JPanel messagePanel = new JPanel(new BorderLayout());
        bottomPanel.add(messagePanel, BorderLayout.SOUTH);

        messageTextArea = new JTextArea();
        messagePanel.add(new JScrollPane(messageTextArea), BorderLayout.CENTER);

        sendMessageButton = new JButton("Send Message");
        messagePanel.add(sendMessageButton, BorderLayout.EAST);

        initListeners();

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initListeners() {
        signOutButton.addActionListener(e -> signOut());

        addContactButton.addActionListener(e -> addContact());

        editContactButton.addActionListener(e -> editContact());

        deleteContactButton.addActionListener(e -> deleteContact());

        sendMessageButton.addActionListener(e -> sendMessage());

        contactList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displayMessagesForSelectedContact();
            }
        });

        updateContactList();
    }

    private void updateContactList() {
        DefaultListModel<Contact> contactListModel = (DefaultListModel<Contact>) contactList.getModel();
        contactListModel.clear();
        for (Contact contact : user.getContacts()) {
            contactListModel.addElement(contact);
        }
    }

    private void signOut() {
        new LoginSystem(userDatabase);
        dispose();
    }

    private void addContact() {
        String name = JOptionPane.showInputDialog(this, "Enter contact name:");
        if (name != null && !name.isEmpty()) {
            String email = JOptionPane.showInputDialog(this, "Enter contact email:");
            if (email != null && !email.isEmpty()) {
                Contact contact = new Contact(name, email);
                user.addContact(contact);
                userDatabase.updateUser(user);
                updateContactList();
            }
        }
    }



    private void editContact() {
        Contact selectedContact = contactList.getSelectedValue();
        if (selectedContact != null) {
            String name = JOptionPane.showInputDialog(this, "Enter new name for contact:", selectedContact.getName());
            if (name != null && !name.isEmpty()) {
                String email = JOptionPane.showInputDialog(this, "Enter new email for contact:", selectedContact.getEmail());
                if (email != null && !email.isEmpty()) {
                    selectedContact.setName(name);
                    selectedContact.setEmail(email);
                    userDatabase.updateUser(user);
                    updateContactList();
                }
            }
        }
    }







    private void sendMessage() {
        Contact selectedContact = contactList.getSelectedValue();
        if (selectedContact != null) {
            String messageText = messageTextArea.getText();
            if (!messageText.isEmpty()) {
                // Pass the contact's email as the recipient
                String recipient = selectedContact.getEmail();
                // Generate an appropriate ID for the message
                int messageId = user.generateMessageId();

                Message message = new Message(messageText, user.getUsername(), recipient, messageId);
                user.sendMessage(message);
                userDatabase.updateUser(user);
                displayMessagesForSelectedContact();
                messageTextArea.setText("");
            }
        }
    }






    private void deleteContact() {
        Contact selectedContact = contactList.getSelectedValue();
        if (selectedContact != null) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this contact?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                user.deleteContact(selectedContact);
                userDatabase.updateUser(user);
                updateContactList();
            }
        }
    }



    private void displayMessagesForSelectedContact() {
        Contact selectedContact = contactList.getSelectedValue();
        if (selectedContact != null) {
            DefaultListModel<Message> messageListModel = (DefaultListModel<Message>) messageList.getModel();
            messageListModel.clear();
            for (Message message : user.getMessagesForContact(selectedContact)) {
                messageListModel.addElement(message);
            }
        }
    }}


