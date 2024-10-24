package sk.pocsik.views.main;

import sk.pocsik.services.AuthService;
import sk.pocsik.utils.UserInfo;
import sk.pocsik.views.authenticate.LoginView;

import javax.swing.*;

public class UserPanel extends JPanel {
    private final AuthService authService;
    private JFrame parentFrame;


    public UserPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.authService = new AuthService();

        this.init();
    }

    private void init() {
        JLabel userNameLabel = new JLabel("Welcome: " + UserInfo.getUserName());
        JButton logoutButton = new JButton("Logout");

        logoutButton.addActionListener(e -> {
            authService.logout();
            new LoginView();
            parentFrame.dispose(); // Close the current window when logging out.
        });

        add(userNameLabel);
        add(logoutButton);
    }


}
