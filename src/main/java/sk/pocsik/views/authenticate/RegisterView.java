package sk.pocsik.views.authenticate;

import sk.pocsik.services.AuthService;
import sk.pocsik.utils.UIHelper;
import sk.pocsik.views.base.BaseFrame;
import sk.pocsik.views.main.MainView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class RegisterView extends BaseFrame {
    private final AuthService authService;
    private final JFrame loginView;
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JButton cancelButton;

    public RegisterView(JFrame loginView, AuthService authService) {
        super("Register", 350, 220, JFrame.EXIT_ON_CLOSE);

        this.loginView = loginView;
        this.authService = authService;

        this.init();

        this.registerButton.addActionListener(e -> {
            this.registerAction();
        });

        this.usernameField.addActionListener(UIHelper.getEnterAction(this::registerAction));
        this.passwordField.addActionListener(UIHelper.getEnterAction(this::registerAction));
        this.confirmPasswordField.addActionListener(UIHelper.getEnterAction(this::registerAction));

        this.cancelButton.addActionListener(e -> this.onCloseAction());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCloseAction();
            }
        });


    }

    private void init() {
        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(4, 2, 10, 10));
        this.panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel usernameLabel = new JLabel("Username:");
        this.usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        this.passwordField = new JPasswordField();
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        this.confirmPasswordField = new JPasswordField();
        this.registerButton = new JButton("Register");
        this.cancelButton = new JButton("Cancel");

        this.panel.add(usernameLabel);
        this.panel.add(this.usernameField);
        this.panel.add(passwordLabel);
        this.panel.add(this.passwordField);
        this.panel.add(confirmPasswordLabel);
        this.panel.add(this.confirmPasswordField);
        this.panel.add(this.registerButton);
        this.panel.add(this.cancelButton);

        this.add(panel);
        this.setVisible(true);
    }

    private void onCloseAction() {
        this.loginView.setVisible(true);
        dispose();
    }

    private void registerAction() {
        String username = this.usernameField.getText().trim();
        String password = new String(this.passwordField.getPassword());
        String confirmPassword = new String(this.confirmPasswordField.getPassword());

        List<String> errors = authService.checkRegisterFields(username, password, confirmPassword);
        if (!errors.isEmpty()) {
            JOptionPane.showMessageDialog(null, String.join("\n", errors));
            return;
        }

        this.authService.register(username, password);
        new MainView(authService);
        this.dispose();
    }
}
