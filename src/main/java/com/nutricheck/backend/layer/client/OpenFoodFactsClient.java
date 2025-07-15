package com.nutricheck.backend.layer.client;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.OpenFoodFactsFoodProductDTO;
import com.nutricheck.backend.dto.OpenFoodFactsResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Client implementation for interacting with the Open Food Facts API.
 */
@Component
public class OpenFoodFactsClient implements FoodDBClient {

    private RestClient webClient;

    private OpenFoodFactsFoodProductMapper mapper;

    public OpenFoodFactsClient(OpenFoodFactsFoodProductMapper mapper) {
        this.mapper = mapper;
        this.webClient = RestClient.builder()
                .baseUrl("https://world.openfoodfacts.org")
                .defaultHeader(HttpHeaders.USER_AGENT, "NutriCheck/1.0 (uvtal@student.kit.edu)")
                .build();
    }
    @Override
    public List<FoodProductDTO> search(String request) {
        OpenFoodFactsResponseDTO response = getData(request);
        List<OpenFoodFactsFoodProductDTO> foodProducts = response.getProducts();
        return foodProducts.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    private OpenFoodFactsResponseDTO getData(String request) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/cgi/search.pl")
                        .queryParam("action", "process")
                        .queryParam("search_terms", request)
                        .query("nutriment_0=carbohydrates&nutriment_compare_0=gt&nutriment_value_0=0")
                        .query("nutriment_1=proteins&nutriment_compare_1=gt&nutriment_value_1=0")
                        .query("nutriment_2=fat&nutriment_compare_2=gt&nutriment_value_2=0")
                        .query("nutriment_3=energy-kcal&nutriment_compare_3=gt&nutriment_value_3=0")
                        .queryParam("sort_by", "unique_scans_n")
                        .queryParam("page", "1")
                        .queryParam("page_size", "20")
                        .queryParam("json", "1")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(OpenFoodFactsResponseDTO.class);
    }
}
