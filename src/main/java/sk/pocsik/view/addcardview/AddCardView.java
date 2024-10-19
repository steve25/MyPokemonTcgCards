package sk.pocsik.view.addcardview;

import sk.pocsik.models.PokemonCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddCardView extends JFrame implements SearchResultListener {
    private final ResultView resultView;
    private final SearchView searchView;
    private final List<PokemonCard> pokemonCards = new ArrayList<>();

    public AddCardView() {
        this.init();

        searchView = new SearchView();
        resultView = new ResultView();

        searchView.setSearchResultListener(this);

        JScrollPane scrollPane = new JScrollPane(resultView);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(searchView, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void init() {
        this.setTitle("Search Pokemon TCG Cards");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(680, 460);
        this.setLayout(new BorderLayout(0, 10));
    }

    @Override
    public void onSearchResult(List<PokemonCard> pokemonCards) {
        this.pokemonCards.clear();
        this.pokemonCards.addAll(pokemonCards);

        resultView.updateCards(pokemonCards);
    }
}
