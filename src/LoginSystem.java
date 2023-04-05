import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginSystem extends JFrame {
    private UserDatabase userDatabase;

    private JPanel mainPanel;
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginSystem(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
        setTitle("Messaging App - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mainPanel = new JPanel(new GridBagLayout());
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(usernameLabel, gbc);

        usernameTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(usernameTextField, gbc);

        passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(passwordField, gbc);

        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(loginButton, gbc);

        registerButton = new JButton("Register");
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(registerButton, gbc);

        initListeners();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }



    private void initListeners() {
        loginButton.addActionListener(e -> login());

        registerButton.addActionListener(e -> {
            RegisterSystem registerSystem = new RegisterSystem(userDatabase);
            registerSystem.setVisible(true);
        });
    }


    private void login() {
        String username = usernameTextField.getText();
        String password = new String(passwordField.getPassword());

        User user = userDatabase.getUserByUsername(username);

        if (user == null) {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
            return;
        }

        if (user.getPassword().equals(password)) {
            new MessagingApp(user, userDatabase);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    private void register() {
        new RegisterSystem(userDatabase);
        dispose();
    }
}
