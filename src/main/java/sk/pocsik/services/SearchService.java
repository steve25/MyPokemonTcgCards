package sk.pocsik.services;

import sk.pocsik.models.PokemonCard;
import sk.pocsik.services.api.CardListBuilder;
import sk.pocsik.services.api.HttpService;

import java.util.List;

public class SearchService {
    private final HttpService service = new HttpService();
    private final CardListBuilder cardListBuilder;

    public SearchService() {
        this.cardListBuilder = new CardListBuilder();
    }

    public List<PokemonCard> build(String query) {
        String response = service.findCardByName(query);

        return cardListBuilder.build(response);
    }
}
