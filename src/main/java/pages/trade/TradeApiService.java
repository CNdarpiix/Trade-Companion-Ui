package pages.trade;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.trade.*;
import util.ApiConfig;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static util.HttpClientUtil.getClient;

public class TradeApiService {
    private final HttpClient client = getClient();
    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());


    public TradeResponse createTrade(CreateTradeRequest request) {
        try {

            String json = mapper.writeValueAsString(request);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.BASE_URL + "/trade"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString()
            );

            return mapper.readValue(
                    response.body(),
                    TradeResponse.class
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TradeResponse updateTrade(UpdateTradeRequest request, Long id) {
        try {

            String json = mapper.writeValueAsString(request);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.BASE_URL + "/trade/" + id))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            return mapper.readValue(
                    response.body(),
                    TradeResponse.class
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TradeResponse closeTrade(CloseTradeRequest request, Long id) {
        try {

            String json = mapper.writeValueAsString(request);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.BASE_URL + "/trade/" + id + "/close"))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            return mapper.readValue(
                    response.body(),
                    TradeResponse.class
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TradeResponse removeTrade(Long id){
        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.BASE_URL + "/trade/"+id))
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return mapper.readValue(response.body() , TradeResponse.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
