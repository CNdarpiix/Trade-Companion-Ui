package pages.journal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.trade.TradeResponse;
import util.ApiConfig;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static util.HttpClientUtil.getClient;

public class JournalApiService {

    private final HttpClient client = getClient();

    private final ObjectMapper mapper = new ObjectMapper();

    public List<TradeResponse> getAllTrades() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.BASE_URL + "/trade"))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString());

            return mapper.readValue(
                    response.body(),
                    new TypeReference<List<TradeResponse>>() {} );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
