package sk.pocsik.services.api;

import com.google.gson.*;
import sk.pocsik.models.PokemonCard;

import java.util.ArrayList;
import java.util.List;

public class CardListBuilder {
    private final CardBuilder cardBuilder;
    private final List<PokemonCard> cards = new ArrayList<>();
    private final Gson gson = new Gson();

    public CardListBuilder() {
        this.cardBuilder = new CardBuilder();
    }

    public List<PokemonCard> build(String response) {
        JsonArray jsonArray = gson.fromJson(response, JsonObject.class).getAsJsonArray("data");

        for (JsonElement jsonElement : jsonArray) {
            cards.add(cardBuilder.makeCard(jsonElement));
        }

        return cards;
    }
}
