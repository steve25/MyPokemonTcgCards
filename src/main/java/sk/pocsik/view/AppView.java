package sk.pocsik.view;

import javax.swing.*;
import java.awt.*;

public class AppView extends JFrame {
    public AppView() {
        this.setTitle("My Pokemon TCG Cards");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 600);

        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        JButton searchButton = new JButton("Add cards");
        searchButton.addActionListener(e -> new AddCardView());
        container.add(searchButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
