package sk.pocsik.view.addcardview;

import sk.pocsik.models.PokemonCard;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResultView extends JPanel {
    public ResultView() {
        this.init();
    }

    private void init() {
        this.setPreferredSize(new Dimension(640, super.getPreferredSize().height));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    }

    public void updateCards(List<PokemonCard> pokemonCards) {
        System.out.println(pokemonCards.size());

        this.removeAll();


        for (PokemonCard pokemonCard : pokemonCards) {
            this.add(new PokemonCardView(pokemonCard));
        }

        this.revalidate();
        this.repaint();

        updatePreferredSize();
    }

    private void updatePreferredSize() {
        int rows = (int) Math.ceil(this.getComponentCount() / 5.0);
        int height = rows * 220;

        this.setPreferredSize(new Dimension(640, height));

        this.revalidate();
        this.repaint();
    }
}
