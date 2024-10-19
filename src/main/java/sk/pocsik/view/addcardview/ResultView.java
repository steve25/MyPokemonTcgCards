package sk.pocsik.view.addcardview;

import sk.pocsik.models.PokemonCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ResultView extends JPanel {
    private List<PokemonCard> pokemonCards = new ArrayList<>();
    public ResultView() {
        this.init();

        for (int i = 0; i < 10; i++) {
            JPanel cardPanel = new JPanel();
            cardPanel.setPreferredSize(new Dimension(100, 200));
            cardPanel.setBackground(new Color((int)(Math.random() * 0x1000000)));
            this.add(cardPanel);
        }
    }

    private void init() {
        this.setPreferredSize(new Dimension(640, 400));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    }

    public void updateCards(List<PokemonCard> pokemonCards) {
        this.pokemonCards = pokemonCards;
        System.out.println("Updated cards: " + this.pokemonCards.size());
    }
}
