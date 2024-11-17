package sk.pocsik.views.add;

import sk.pocsik.models.Pokemon;

import java.util.List;

public interface SearchResultListener {
    void onSearchResult(List<Pokemon> result);
}
