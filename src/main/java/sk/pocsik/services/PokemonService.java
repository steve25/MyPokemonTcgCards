package sk.pocsik.services;

import sk.pocsik.daos.PokemonDao;
import sk.pocsik.models.Pokemon;
import sk.pocsik.models.User;

import java.util.List;

public class PokemonService {
    private final PokemonDao pokemonDao;

    public PokemonService(PokemonDao pokemonDao) {
        this.pokemonDao = pokemonDao;
    }

    public List<Pokemon> getPokemonsByUser(User user) {
        return pokemonDao.getPokemonsByUser(user);
    }

    public Pokemon getPokemonByIdAndUser(String apiId, User user) {
        return pokemonDao.getPokemonByIdAndUser(apiId, user);
    }

    public void save(Pokemon pokemon) {
        pokemonDao.save(pokemon);
    }

    public void remove(Pokemon pokemon) { pokemonDao.remove(pokemon);}
}
