package sk.pocsik.views.authenticate;

import sk.pocsik.services.AuthService;
import sk.pocsik.utils.UIHelper;
import sk.pocsik.views.main.MainView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class RegisterView extends JFrame {
    private AuthService authService;

    private JFrame parentFrame;
    private JPanel panel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel confirmPasswordLabel;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JButton cancelButton;

    public RegisterView(JFrame parentFrame, AuthService authService) {
        this.parentFrame = parentFrame;
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

        this.add(panel);

        this.setVisible(true);
    }
    private void onCloseAction() {
        this.parentFrame.setVisible(true);
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

    private void init() {
        this.setTitle("Register");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(4, 2, 10, 10));
        this.panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        this.usernameLabel = new JLabel("Username:");
        this.usernameField = new JTextField();
        this.passwordLabel = new JLabel("Password:");
        this.passwordField = new JPasswordField();
        this.confirmPasswordLabel = new JLabel("Confirm Password:");
        this.confirmPasswordField = new JPasswordField();
        this.registerButton = new JButton("Register");
        this.cancelButton = new JButton("Cancel");

        this.panel.add(this.usernameLabel);
        this.panel.add(this.usernameField);
        this.panel.add(this.passwordLabel);
        this.panel.add(this.passwordField);
        this.panel.add(this.confirmPasswordLabel);
        this.panel.add(this.confirmPasswordField);
        this.panel.add(this.registerButton);
        this.panel.add(this.cancelButton);
    }
}
