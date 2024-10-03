package sk.pocsik.services.api;

import com.google.gson.*;
import sk.pocsik.models.PokemonCard;

import java.util.ArrayList;
import java.util.List;

public class CardListBuilder {
    public static List<PokemonCard> build(String response) {
        List<PokemonCard> cards = new ArrayList<>();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonArray jsonArray = gson.fromJson(response, JsonObject.class).getAsJsonArray("data");

        for (JsonElement jsonElement : jsonArray) {
            PokemonCard card = CardBuilder.makeCard(jsonElement);
            cards.add(card);
        }

        return cards;
    }
}
