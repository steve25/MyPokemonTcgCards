package sk.pocsik.services.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import sk.pocsik.models.Pokemon;

import java.lang.reflect.Type;
import java.util.List;

public class ApiResponseParser {

    private final Gson gson = new Gson();

    public List<Pokemon> build(String response) {

        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        String dataJson = jsonObject.getAsJsonArray("data").toString();

        Type listType = new TypeToken<List<Pokemon>>() {}.getType();

        return gson.fromJson(dataJson, listType);
    }
}
