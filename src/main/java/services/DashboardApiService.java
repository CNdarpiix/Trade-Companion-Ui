package services;

import pages.dashboard.models.DashBoardResponse;
import util.ApiConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static util.HttpClientUtil.getClient;

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
