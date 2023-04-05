import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserDatabase userDatabase = new UserDatabase();
                LoginSystem loginSystem = new LoginSystem(userDatabase);
            }
        });
    }
}
