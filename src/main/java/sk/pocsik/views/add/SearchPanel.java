package sk.pocsik.views.add;

import lombok.Setter;
import sk.pocsik.models.Pokemon;
import sk.pocsik.services.SearchService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class SearchPanel extends JPanel {
    private final SearchService searchService;
    @Setter private SearchResultListener searchResultListener;
    private JTextField searchField;
    private JButton searchButton;


    public SearchPanel() {
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
        JLabel searchLabel = new JLabel("Search for a card:");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        this.add(searchLabel);
        this.add(searchField);
        this.add(searchButton);
    }

    private void search(JTextField searchField) {
        String query = searchField.getText().trim();
        List<Pokemon> pokemons = searchService.build(query);

        if (searchResultListener != null) {
            searchResultListener.onSearchResult(pokemons);
        }
    }
}