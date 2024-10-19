package sk.pocsik.view.addcardview;

import sk.pocsik.models.PokemonCard;

import java.util.List;

public interface SearchResultListener {
        void onSearchResult(List<PokemonCard> result);
}
