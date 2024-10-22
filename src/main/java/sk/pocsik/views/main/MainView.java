package sk.pocsik.views.main;

import sk.pocsik.services.AuthService;
import sk.pocsik.utils.UserInfo;
import sk.pocsik.views.addcardview.AddCardView;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public MainView() {
        this.init();

        JPanel userPanel = new UserPanel(this);

        this.add(userPanel, BorderLayout.NORTH);

        JButton searchButton = new JButton("Add cards");
        searchButton.addActionListener(e -> new AddCardView());
        this.add(searchButton);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void init() {
        this.setTitle("My Pokemon TCG Cards");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1870, 1030);
        this.setLayout(new BorderLayout(0, 10));
    }
}
