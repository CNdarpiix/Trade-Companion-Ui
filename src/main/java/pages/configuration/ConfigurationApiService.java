package pages.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.criterion.CreateCriterion;
import model.criterion.CriterionResponse;
import model.table.CreateTable;
import model.table.TableResponse;
import util.ApiConfig;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static util.HttpClientUtil.getClient;

public class ConfigurationApiService {
    private final HttpClient client = getClient();

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    /// TABLES API SERVICE
    public List<TableResponse> getAllTables() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.BASE_URL + "/table"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return mapper.readValue(response.body(), new TypeReference<List<TableResponse>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TableResponse createTable(CreateTable createTable) {
        try {
            String json = mapper.writeValueAsString(createTable);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.BASE_URL + "/table"))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return mapper.readValue(response.body(), TableResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TableResponse deleteTable(Long id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.BASE_URL + "/table/" + id))
                    .DELETE()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(response.body(), TableResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TableResponse updateTable(CreateTable createTable, Long id) {
        try {
            String json = mapper.writeValueAsString(createTable);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.BASE_URL + "/table/" + id))
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return mapper.readValue(response.body(), TableResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TableResponse getTableById(Long id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.BASE_URL + "/table/" + id))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return mapper.readValue(response.body(), TableResponse.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /// CRITERIONS API SERVICE
    public List<CriterionResponse> getAllCriterion() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.BASE_URL + "/criterion"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return mapper.readValue(response.body(), new TypeReference<List<CriterionResponse>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriterionResponse createCriterion(CreateCriterion createCriterion) {
        try {
            String json = mapper.writeValueAsString(createCriterion);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.BASE_URL + "/criterion"))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return mapper.readValue(response.body(), CriterionResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriterionResponse deleteCriterion(Long id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.BASE_URL + "/criterion/" + id))
                    .DELETE()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(response.body(), CriterionResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriterionResponse updateCriterion(CreateCriterion createCriterion, Long id) {
        try {
            String json = mapper.writeValueAsString(createCriterion);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.BASE_URL + "/criterion/" + id))
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return mapper.readValue(response.body(), CriterionResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriterionResponse getCriterionById(Long id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.BASE_URL + "/criterion/" + id))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return mapper.readValue(response.body(), CriterionResponse.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
