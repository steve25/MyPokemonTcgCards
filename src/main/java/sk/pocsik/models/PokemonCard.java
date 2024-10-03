package sk.pocsik.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PokemonCard {
    private String id;
    private String name;
    private String imageUrl;
    private double price;

}
