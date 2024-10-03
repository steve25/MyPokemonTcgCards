package sk.pocsik.services.api;

import sk.pocsik.models.PokemonCard;

import java.util.List;

public class Search {


    public static List<PokemonCard> build(String query) {
        HttpService service = new HttpService();
        String response = service.findCardByName(query);

        return CardListBuilder.build(response);
    }
}
