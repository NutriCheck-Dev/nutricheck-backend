package com.nutricheck.backend.layer.client.impl;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.external.SwissFoodCDFoodProductDTO;
import com.nutricheck.backend.dto.external.SwissFoodCDResponseDTO;
import com.nutricheck.backend.layer.client.FoodDBClient;
import com.nutricheck.backend.layer.client.mapper.SwissFoodCDMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Client implementation for interacting with the Swiss Food Composition Database (SwissFoodCD).
 */
@Component("swiss")
public class SwissFoodCDClient implements FoodDBClient {

    static final String MAX_SEARCH_RESULTS = "40";

    private final RestClient restClient;
    private final SwissFoodCDMapper mapper;

    public SwissFoodCDClient(SwissFoodCDMapper mapper, RestClient.Builder builder) {
        this.mapper = mapper;
        this.restClient = builder
                .baseUrl("https://api.webapp.prod.blv.foodcase-services.com/BLV_WebApp_WS/webresources/BLV-api")
                .build();
    }
    @Override
    public List<FoodProductDTO> search(String request, String language) {
        List<SwissFoodCDResponseDTO> foods = getFoods(request, language);
        List<SwissFoodCDFoodProductDTO> foodProducts = new ArrayList<>();
        for (SwissFoodCDResponseDTO food: foods) {
            SwissFoodCDFoodProductDTO foodProductDTO = getParticularFood(food.getId(), language);
            foodProducts.add(foodProductDTO);
        }
        return mapper.toFoodProductDTO(foodProducts);
    }

    private List<SwissFoodCDResponseDTO> getFoods(String request, String language) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/foods")
                        .queryParam("search", request)
                        .queryParam("lang", language)
                        .queryParam("limit", MAX_SEARCH_RESULTS)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    private SwissFoodCDFoodProductDTO getParticularFood(String id, String language) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/food/{id}")
                        .queryParam("lang", language)
                        .build(id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(SwissFoodCDFoodProductDTO.class);
    }
}
