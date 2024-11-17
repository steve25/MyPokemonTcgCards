package sk.pocsik.views.pokemons;

import sk.pocsik.models.Pokemon;
import sk.pocsik.services.PokemonService;
import sk.pocsik.utils.UserInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class PokemonPanel extends JPanel {
    private final PokemonService pokemonService;
    private final Pokemon pokemon;
    private final String actionType;
    private final PokemonListPanel pokemonListPanel;

    public PokemonPanel(Pokemon pokemon, PokemonService pokemonService, String actionType, PokemonListPanel pokemonListPanel) {
        this.pokemonService = pokemonService;
        this.pokemonListPanel = pokemonListPanel;
        this.pokemon = pokemon;
        this.actionType = actionType;

        this.init();
    }

    private void init() {
        JButton theActionButton = new JButton(actionType.equals("add") ? "Add Pokemon" : "Remove Pokemon");
        theActionButton.addActionListener(e -> performAction());

        this.setLayout(new BorderLayout(0, 5));

        this.showImage();

        this.add(theActionButton, BorderLayout.SOUTH);
    }

    private void showImage() {
        try {
            URL url = URI.create(pokemon.getSmallImage()).toURL();
            BufferedImage image = ImageIO.read(url);
            PokemonImageLabel imageLabel = new PokemonImageLabel(image, 245, 342);
            this.add(imageLabel, BorderLayout.CENTER);
        } catch (IOException e) {
            System.err.println(e);
            JLabel label = new JLabel(pokemon.getName());
            label.setPreferredSize(new Dimension(245, 342));
            this.add(label, BorderLayout.CENTER);
        }
    }

    private void performAction() {
        if ("add".equals(actionType)) {
            addPokemon();
        } else if ("remove".equals(actionType)) {
            removePokemon();
        }
    }

    public void addPokemon() {
        Pokemon pokemonToSave = pokemonService.getPokemonByIdAndUser(this.pokemon.getApiId(), UserInfo.getUser());
        if (pokemonToSave == null) {
            this.pokemon.setUser(UserInfo.getUser());
            pokemonService.save(this.pokemon);
            return;
        }

        JOptionPane.showMessageDialog(null, "This card already added.");
    }
    public void removePokemon() {
        Pokemon pokemonToRemove = pokemonService.getPokemonByIdAndUser(this.pokemon.getApiId(), UserInfo.getUser());

        if (pokemonToRemove != null) {
            pokemonService.remove(this.pokemon);
            pokemonListPanel.setCards();
            return;
        }

        JOptionPane.showMessageDialog(null, "Cannot delete this card.");
    }
}
