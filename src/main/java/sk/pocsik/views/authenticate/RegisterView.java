package sk.pocsik.views.authenticate;

import sk.pocsik.services.AuthService;
import sk.pocsik.views.MainView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

    public RegisterView(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.authService = new AuthService();

        this.init();

        this.registerButton.addActionListener(e -> {
            this.registerAction();
        });

        this.usernameField.addActionListener(this.enterAction());
        this.passwordField.addActionListener(this.enterAction());
        this.confirmPasswordField.addActionListener(this.enterAction());

        this.cancelButton.addActionListener(e -> {
            this.onCloseAction();
        });

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

    private Action enterAction() {
        return new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                registerAction();
            }
        };
    }

    private void registerAction() {
        String username = this.usernameField.getText();
        String password = new String(this.passwordField.getPassword());
        String confirmPassword = new String(this.confirmPasswordField.getPassword());

        if (!password.equals(confirmPassword)
                || username.trim().isEmpty()
                || password.trim().isEmpty()
        ) {
            JOptionPane.showMessageDialog(null, "Empty username, or passwords do not match. Please try again.");
            return;
        }

        if (authService.checkIfExists(username)) {
            JOptionPane.showMessageDialog(null, "Username is taken");
            return;
        }

        this.authService.register(username, password);
        new MainView();
        this.dispose();
    }

    private void init() {
        this.setTitle("Register");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);

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
