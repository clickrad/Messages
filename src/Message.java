public class Message {
    private String text;
    private String sender;
    private String recipient;
    private int id;

    public Message(String text, String sender, String recipient, int id) {
        this.text = text;
        this.sender = sender;
        this.recipient = recipient;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "From: " + sender + " | To: " + recipient + " | Message: " + text;
    }
}
