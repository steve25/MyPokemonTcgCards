package sk.pocsik.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
public class PokemonCard {
    @Id
    private String id;
    private String name;
    private String imageUrl;
    private double price;

}
