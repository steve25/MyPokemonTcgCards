package sk.pocsik.views.pokemons;

import sk.pocsik.models.Pokemon;
import sk.pocsik.services.PokemonService;
import sk.pocsik.utils.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PokemonListPanel extends JPanel {
    private final PokemonService pokemonService;
    private final String actionType;

    public PokemonListPanel(PokemonService pokemonService, String actionType) {
        this.pokemonService = pokemonService;
        this.actionType = actionType;

        this.init();
    }

    private void init() {
        this.setPreferredSize(new Dimension(1550, super.getPreferredSize().height));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 25));
    }

    public void setCards() {
        List<Pokemon> pokemons = pokemonService.getPokemonsByUser(UserInfo.getUser());
        updateCards(pokemons);
    }

    public void updateCards(List<Pokemon> pokemons) {
        this.removeAll();
        for (Pokemon pokemon : pokemons) {
            PokemonPanel pokemonPanel = new PokemonPanel(pokemon, pokemonService, actionType, this);
            this.add(pokemonPanel);
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
