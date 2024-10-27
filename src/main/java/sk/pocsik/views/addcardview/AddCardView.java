package sk.pocsik.views.addcardview;

import sk.pocsik.models.PokemonCard;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddCardView extends JFrame implements SearchResultListener {
    private final ResultPanel resultPanel;
    private final SearchPanel searchPanel;


    public AddCardView() {
        this.init();

        searchPanel = new SearchPanel();
        resultPanel = new ResultPanel();

        searchPanel.setSearchResultListener(this);

        JScrollPane scrollPane = new JScrollPane(resultPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(searchPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void init() {
        this.setTitle("Search Pokemon TCG Cards");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1600, 1200);
        this.setLayout(new BorderLayout(0, 10));
    }

    @Override
    public void onSearchResult(List<PokemonCard> pokemonCards) {
        resultPanel.updateCards(pokemonCards);
    }
}
