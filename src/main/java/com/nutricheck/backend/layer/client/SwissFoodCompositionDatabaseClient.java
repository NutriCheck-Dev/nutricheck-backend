package com.nutricheck.backend.layer.client;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.SwissFoodCDFoodProductDTO;
import com.nutricheck.backend.dto.SwissFoodCDResponseDTO;
import com.nutricheck.backend.layer.client.mapper.SwissFoodCDFoodProductMapper;
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
public class SwissFoodCompositionDatabaseClient implements FoodDBClient {

    private final RestClient restClient;
    private final SwissFoodCDFoodProductMapper mapper;

    public SwissFoodCompositionDatabaseClient(SwissFoodCDFoodProductMapper mapper, RestClient.Builder builder) {
        this.mapper = mapper;
        this.restClient = builder
                .baseUrl("https://api.webapp.prod.blv.foodcase-services.com/BLV_WebApp_WS/webresources/BLV-api")
                .build();
    }
    @Override
    public List<FoodProductDTO> search(String request) {
        // TODO: implement german language
        List<SwissFoodCDResponseDTO> foods = getFoods(request, "en");
        List<SwissFoodCDFoodProductDTO> foodProducts = new ArrayList<>();
        for (SwissFoodCDResponseDTO food: foods) {
            SwissFoodCDFoodProductDTO foodProductDTO = getParticularFood(food.getId(), "en");
            foodProducts.add(foodProductDTO);
        }
        return mapper.toDTO(foodProducts);
    }

    private List<SwissFoodCDResponseDTO> getFoods(String request, String language) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/foods")
                        .queryParam("search", request)
                        .queryParam("lang", language)
                        .queryParam("limit", "20")
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
