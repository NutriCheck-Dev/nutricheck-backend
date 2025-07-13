package com.nutricheck.backend.layer.client;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.OpenFoodFactsResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class OpenFoodFactsClient implements FoodDBClient {

    private RestClient webClient;

    public OpenFoodFactsClient() {
        this.webClient = RestClient.builder()
                .baseUrl("https://world.openfoodfacts.org")
                .defaultHeader(HttpHeaders.USER_AGENT, "NutriCheck/1.0 (uvtal@student.kit.edu)")
                .build();
    }
    @Override
    public List<FoodProductDTO> search(String request) {
        return null;
    }

    private OpenFoodFactsResponseDTO getData(String request) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/cgi/search.pl")
                        .queryParam("search_terms", request)
                        .queryParam("sort_by", "popularity")
                        .queryParam("json", "1")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(OpenFoodFactsResponseDTO.class);
    }
}
