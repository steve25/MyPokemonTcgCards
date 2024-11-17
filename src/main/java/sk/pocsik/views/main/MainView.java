package sk.pocsik.views.main;

import lombok.Getter;
import sk.pocsik.daos.PokemonDao;
import sk.pocsik.services.AuthService;
import sk.pocsik.services.PokemonService;
import sk.pocsik.views.base.BaseFrame;
import sk.pocsik.views.pokemons.PokemonListPanel;

import javax.swing.*;
import java.awt.*;


public class MainView extends BaseFrame {
    @Getter private PokemonListPanel userPokemonListPanel;
    private final PokemonService pokemonService;
    private final AuthService authService;

    public MainView(AuthService authService) {
        super("My Pokemon TCG Cards", 1870, 1030, JFrame.EXIT_ON_CLOSE);

        PokemonDao pokemonDao = new PokemonDao();
        this.pokemonService = new PokemonService(pokemonDao);
        this.authService = authService;

        this.init();
    }

    private void init() {
        this.setLayout(new BorderLayout(0, 10));
        JPanel userPanel = new UserPanel(this, authService, pokemonService);
        this.userPokemonListPanel = new PokemonListPanel(pokemonService, "remove");
        userPokemonListPanel.setCards();

        this.add(userPanel, BorderLayout.NORTH);
        this.add(userPokemonListPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public void updateList() {
        userPokemonListPanel.setCards();
    }
}
