package com.charly.tradecompanion.trade_companion_ui.service;

import com.charly.tradecompanion.trade_companion_ui.models.dashBoard.DashBoardResponse;
import com.charly.tradecompanion.trade_companion_ui.util.ApiConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.charly.tradecompanion.trade_companion_ui.util.HttpClientUtil.getClient;

public class DashboardApiService {
    private final HttpClient client = getClient();

    private final ObjectMapper mapper = new ObjectMapper();


    public DashBoardResponse getDashboard() {

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.BASE_URL + "/dashboard"))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            return mapper.readValue(
                    response.body(),
                    DashBoardResponse.class
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
