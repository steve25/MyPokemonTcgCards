package sk.pocsik.view;

import sk.pocsik.models.PokemonCard;
import sk.pocsik.services.api.Search;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AddCardView extends JFrame {
    public AddCardView() {
        this.setTitle("Search Pokemon TCG Cards");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(680, 460);

        Container container = getContentPane();
        container.setLayout(new FlowLayout());


        JLabel searchLabel = new JLabel("Search for a card:");
        container.add(searchLabel);

        JTextField searchField = new JTextField(20);
        container.add(searchField);

        JButton searchButton = new JButton("Search");
        container.add(searchButton);

        searchField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search(searchField);
            }
        });

        searchButton.addActionListener(e -> search(searchField));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void search(JTextField searchField) {
        String cardName = searchField.getText();
        List<PokemonCard> cards = Search.build(cardName);

        cards.forEach(System.out::println);
        System.out.println(cards.size());
    }
}
