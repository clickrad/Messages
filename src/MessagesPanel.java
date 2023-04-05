import javax.swing.*;
import java.awt.*;

public class MessagesPanel extends JPanel {
    private JTextArea messagesArea;

    public MessagesPanel() {
        setLayout(new BorderLayout());

        messagesArea = new JTextArea();
        messagesArea.setEditable(false);
        add(new JScrollPane(messagesArea), BorderLayout.CENTER);
    }

    public void addMessage(String sender, String message) {
        messagesArea.append(sender + ": " + message + "\n");
    }
}
