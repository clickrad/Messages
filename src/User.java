import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private int messageCounter;
    private ArrayList<Message> messages;
    private ArrayList<Contact> contacts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        contacts = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public void sendMessage(Message message) {
        messages.add(message);
    }

    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    public ArrayList<Message> getMessagesForContact(Contact contact) {
        ArrayList<Message> contactMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getRecipient().equals(contact.getEmail())) {
                contactMessages.add(message);
            }
        }
        return contactMessages;
    }

    public int generateMessageId() {
        // Generate a unique ID for the message.
        // You can use a simple counter, a random number, or any other unique ID generation strategy.
        // In this example, we will use a simple counter.
        int newId = messageCounter;
        messageCounter++;
        return newId;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
}
