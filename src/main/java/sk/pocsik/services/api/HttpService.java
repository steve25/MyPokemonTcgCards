package sk.pocsik.services.api;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpService {

    Dotenv dotenv;
    String apiUrl;
    String apiKey;
    HttpClient httpClient;

    public HttpService() {
        this.dotenv = Dotenv.load();
        this.apiUrl = dotenv.get("API_URL");
        this.apiKey = dotenv.get("API_KEY") == null ? "" : dotenv.get("API_KEY");
        this.httpClient = HttpClient.newHttpClient();
    }

    public String findCardByName(String name) {
        StringBuilder url = new StringBuilder(this.apiUrl).append("?q=name:")
                .append(name)
                .append("*")
                .append("&orderBy=name")
                .append("/?select=id,name,images,tcgplayer");

        try {
            URI uri = new URI(url.toString());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .header("X-Api-Key", this.apiKey)
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
