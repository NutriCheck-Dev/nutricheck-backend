package com.nutricheck.backend.layer.client.impl;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.external.OpenFoodFactsFoodProductDTO;
import com.nutricheck.backend.dto.external.OpenFoodFactsResponseDTO;
import com.nutricheck.backend.layer.client.FoodDBClient;
import com.nutricheck.backend.layer.client.mapper.OpenFoodFactsMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * Client implementation for interacting with the Open Food Facts API.
 */
@Component("openFoodFacts")
public class OpenFoodFactsClient implements FoodDBClient {

    private static final String NUMBER_OF_SEARCH_PAGES = "1";

    private RestClient restClient;

    private OpenFoodFactsMapper mapper;

    public OpenFoodFactsClient(OpenFoodFactsMapper mapper, RestClient.Builder builder) {
        this.mapper = mapper;
        this.restClient = builder
                .baseUrl("https://world.openfoodfacts.org")
                .defaultHeader(HttpHeaders.USER_AGENT, "NutriCheck/1.0 (uvtal@student.kit.edu)")
                .build();
    }
    @Override
    public List<FoodProductDTO> search(String request, String language) {
        OpenFoodFactsResponseDTO response = getData(request);
        List<OpenFoodFactsFoodProductDTO> foodProducts = response.getProducts();
        List<FoodProductDTO> mappedProducts = mapper.toFoodProductDTO(foodProducts);
        for (int i = 0; i < mappedProducts.size(); i++) {
            // Set the name based on the requested language
            FoodProductDTO currentProduct = mappedProducts.get(i);
            if ("de".equals(language) && foodProducts.get(i).getGermanName() != null) {
                currentProduct.setName(foodProducts.get(i).getGermanName());
            } else if ("en".equals(language) && foodProducts.get(i).getEnglishName() != null) {
                currentProduct.setName(foodProducts.get(i).getEnglishName());
            } else {
                currentProduct.setName(foodProducts.get(i).getName());
            }
        }
        return mappedProducts;
    }

    private OpenFoodFactsResponseDTO getData(String request) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/cgi/search.pl")
                        .queryParam("action", "process")
                        .queryParam("search_terms", request)
                        .query("nutriment_0=carbohydrates&nutriment_compare_0=gt&nutriment_value_0=0")
                        .query("nutriment_1=proteins&nutriment_compare_1=gt&nutriment_value_1=0")
                        .query("nutriment_2=fat&nutriment_compare_2=gt&nutriment_value_2=0")
                        .query("nutriment_3=energy-kcal&nutriment_compare_3=gt&nutriment_value_3=0")
                        .queryParam("sort_by", "unique_scans_n")
                        .queryParam("page", NUMBER_OF_SEARCH_PAGES)
                        .queryParam("page_size", SwissFoodCDClient.MAX_SEARCH_RESULTS)
                        .queryParam("json", "1")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(OpenFoodFactsResponseDTO.class);
    }
}
