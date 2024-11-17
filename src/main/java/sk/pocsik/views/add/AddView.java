package sk.pocsik.views.add;

import sk.pocsik.models.Pokemon;
import sk.pocsik.services.PokemonService;
import sk.pocsik.views.base.BaseFrame;
import sk.pocsik.views.main.MainView;
import sk.pocsik.views.pokemons.PokemonListPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class AddView extends BaseFrame implements SearchResultListener {
    private final PokemonListPanel pokemonListPanel;
    private final SearchPanel searchPanel;
    private final MainView mainView;


    public AddView(PokemonService pokemonService, MainView mainView) {
        super("Search Pokemon TCG Cards", 1600, 1200, JFrame.HIDE_ON_CLOSE);

        this.mainView = mainView;
        this.searchPanel = new SearchPanel();
        pokemonListPanel = new PokemonListPanel(pokemonService, "add");

        this.init();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCloseAction();
            }
        });
    }

    private void init() {
        this.searchPanel.setSearchResultListener(this);

        this.setLayout(new BorderLayout(0, 10));

        JScrollPane scrollPane = new JScrollPane(pokemonListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(searchPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void onCloseAction() {
        this.mainView.setVisible(true);
        this.mainView.updateList();
        dispose();
    }

    @Override
    public void onSearchResult(List<Pokemon> pokemons) {
        pokemonListPanel.updateCards(pokemons);
    }
}
