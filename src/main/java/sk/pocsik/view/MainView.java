package sk.pocsik.view;

import sk.pocsik.view.addcardview.AddCardView;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    public MainView() {
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
