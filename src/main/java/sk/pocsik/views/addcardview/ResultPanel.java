package sk.pocsik.views.addcardview;

import sk.pocsik.models.PokemonCard;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResultPanel extends JPanel {
    public ResultPanel() {
        this.init();
    }

    private void init() {
        this.setPreferredSize(new Dimension(1550, super.getPreferredSize().height));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    }

    public void updateCards(List<PokemonCard> pokemonCards) {
        this.removeAll();
        for (PokemonCard pokemonCard : pokemonCards) {
            this.add(new PokemonCardPanel(pokemonCard));
        }
        updatePreferredSize();
    }

    private void updatePreferredSize() {
        int rows = (int) Math.ceil(this.getComponentCount() / 5.0);
        int height = rows * 362;

        this.setPreferredSize(new Dimension(1550, height + 10));

        this.revalidate();
        this.repaint();
    }
}
