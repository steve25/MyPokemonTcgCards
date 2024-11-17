package sk.pocsik.services;

import sk.pocsik.models.Pokemon;
import sk.pocsik.services.api.ApiResponseParser;
import sk.pocsik.services.api.HttpService;

import java.util.List;

public class SearchService {
    private final HttpService service = new HttpService();
//    private final PokemonListBuilder cardListBuilder;
    private final ApiResponseParser apiResponseParser;

    public SearchService() {
//        this.cardListBuilder = new PokemonListBuilder();
        this.apiResponseParser = new ApiResponseParser();
    }

    public List<Pokemon> build(String query) {
        String response = service.findCardByName(query);

//        return cardListBuilder.build(response);
        return apiResponseParser.build(response);
    }
}
