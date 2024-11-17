package sk.pocsik.views.authenticate;

import sk.pocsik.services.AuthService;
import sk.pocsik.utils.UIHelper;
import sk.pocsik.views.base.BaseFrame;
import sk.pocsik.views.main.MainView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class LoginView extends BaseFrame {
    private final AuthService authService;
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;


    public LoginView(AuthService authService) {
        super("Login", 350, 180, JFrame.EXIT_ON_CLOSE);
        this.authService = authService;

        this.init();

        this.loginButton.addActionListener(e -> this.loginAction());
        this.usernameField.addActionListener(UIHelper.getEnterAction(this::loginAction));
        this.passwordField.addActionListener(UIHelper.getEnterAction(this::loginAction));
        this.registerButton.addActionListener(e -> this.registerAction());


    }

    private void init() {
        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(3, 2, 10, 10));
        this.panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel usernameLabel = new JLabel("Username:");
        this.usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        this.passwordField = new JPasswordField();
        this.loginButton = new JButton("Login");
        this.registerButton = new JButton("Register");

        this.panel.add(usernameLabel);
        this.panel.add(usernameField);
        this.panel.add(passwordLabel);
        this.panel.add(passwordField);
        this.panel.add(loginButton);
        this.panel.add(registerButton);

        this.add(panel);
        this.setVisible(true);
    }

    private void loginAction() {
        String username = this.usernameField.getText().trim();
        String password = new String(this.passwordField.getPassword());

        List<String> errors = authService.checkLoginFields(username, password);
        if (!errors.isEmpty()) {
            JOptionPane.showMessageDialog(null, String.join("\n", errors));
            return;
        }

        if (authService.authenticate(username, password)) {
            new MainView(authService);
            this.dispose();
        } else {
            this.usernameField.requestFocusInWindow();
            this.passwordField.setText("");
            JOptionPane.showMessageDialog(null, "Invalid login. Please try again.");
        }
    }

    private void registerAction() {
        new RegisterView(this, this.authService);
        this.dispose();
    }
}
