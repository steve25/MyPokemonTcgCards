package sk.pocsik.view.addcardview;

import lombok.Setter;
import sk.pocsik.models.PokemonCard;
import sk.pocsik.services.SearchService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class SearchView extends JPanel {
    private final SearchService searchService;
    @Setter
    private SearchResultListener searchResultListener;
    private JLabel searchLabel;
    private JTextField searchField;
    private JButton searchButton;


    public SearchView() {
        this.searchService = new SearchService();
        this.init();

        searchField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search(searchField);
            }
        });

        searchButton.addActionListener(e -> search(searchField));
    }

    private void init() {
        searchLabel = new JLabel("Search for a card:");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        this.add(searchLabel);
        this.add(searchField);
        this.add(searchButton);
    }

    private void search(JTextField searchField) {
        String cardName = searchField.getText().trim();
        List<PokemonCard> pokemonCards = searchService.build(cardName);

        if (searchResultListener != null) {
            searchResultListener.onSearchResult(pokemonCards);
        }
    }

}
