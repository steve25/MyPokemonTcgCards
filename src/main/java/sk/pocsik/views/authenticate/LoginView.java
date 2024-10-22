package sk.pocsik.views.authenticate;

import sk.pocsik.services.AuthService;
import sk.pocsik.utils.UserInfo;
import sk.pocsik.views.main.MainView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {
    private AuthService authService;
    private JPanel panel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;


    public LoginView() {
        this.authService = new AuthService();

        this.init();

        System.out.println(UserInfo.getUserName());

        this.loginButton.addActionListener(e -> this.loginAction());

        this.usernameField.addActionListener(this.enterAction());

        this.passwordField.addActionListener(this.enterAction());

        this.registerButton.addActionListener(e -> this.registerAction());

        this.add(panel);
        this.setVisible(true);
    }

    private Action enterAction() {
        return new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loginAction();
            }
        };
    }

    private void loginAction() {
        String username = this.usernameField.getText();
        String password = new String(this.passwordField.getPassword());

        if (authService.authenticate(username, password)) {
            new MainView();
            this.dispose();
        } else {
            this.usernameField.requestFocusInWindow();
            this.passwordField.setText("");
            JOptionPane.showMessageDialog(null, "Invalid login. Please try again.");
        }
    }

    private void registerAction() {
        new RegisterView(this);
        this.dispose();
    }

    private void init() {
        this.setTitle("Login");
        this.setSize(350, 180);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(3, 2, 10, 10));
        this.panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        this.usernameLabel = new JLabel("Username:");
        this.usernameField = new JTextField();
        this.passwordLabel = new JLabel("Password:");
        this.passwordField = new JPasswordField();
        this.loginButton = new JButton("Login");
        this.registerButton = new JButton("Register");

        this.panel.add(usernameLabel);
        this.panel.add(usernameField);
        this.panel.add(passwordLabel);
        this.panel.add(passwordField);
        this.panel.add(loginButton);
        this.panel.add(registerButton);
    }
}
