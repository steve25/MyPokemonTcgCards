package sk.pocsik.services.api;

import com.google.gson.JsonElement;
import sk.pocsik.models.PokemonCard;

public class CardBuilder {
    public static PokemonCard makeCard(JsonElement jsonElement) {
        String id = jsonElement.getAsJsonObject().get("id").getAsString();
        String name = jsonElement.getAsJsonObject().get("name").getAsString();
        String imageUrl = jsonElement.getAsJsonObject().getAsJsonObject("images").get("small").getAsString();

        double price;

        try {
            price = jsonElement.getAsJsonObject().getAsJsonObject("cardmarket")
                    .getAsJsonObject("prices")
                    .get("trendPrice")
                    .getAsDouble();
        } catch (NullPointerException e) {
            price = 0;
        }

        return new PokemonCard(id, name, imageUrl, price);
    }
}
