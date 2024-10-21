package sk.pocsik.views.addcardview;

import sk.pocsik.models.PokemonCard;

import java.util.List;

public interface SearchResultListener {
        void onSearchResult(List<PokemonCard> result);
}
