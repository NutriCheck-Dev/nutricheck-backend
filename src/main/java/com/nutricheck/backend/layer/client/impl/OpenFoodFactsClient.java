package com.nutricheck.backend.layer.client.impl;

import com.nutricheck.backend.dto.FoodProductDto;
import com.nutricheck.backend.dto.external.OpenFoodFactsFoodProductDto;
import com.nutricheck.backend.dto.external.OpenFoodFactsResponseDto;
import com.nutricheck.backend.layer.client.FoodDBClient;
import com.nutricheck.backend.layer.client.mapper.OpenFoodFactsMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
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
    public List<FoodProductDto> search(String request, String language) {
        OpenFoodFactsResponseDto response = getData(request);
        List<OpenFoodFactsFoodProductDto> foodProducts;
        if (response == null || response.getProducts() == null) {
            foodProducts = new ArrayList<>();
        }else {
            foodProducts= response.getProducts();
        }
        List<FoodProductDto> mappedProducts = mapper.toFoodProductDTO(foodProducts);
        for (int i = mappedProducts.size() - 1; i >= 0; i--) {
            OpenFoodFactsFoodProductDto foodProductToMap = foodProducts.get(i);
            String nameToSet = getNameByLanguage(foodProductToMap, language);
            if (nameToSet != null) {
                mappedProducts.get(i).setName(nameToSet);
            } else {
                mappedProducts.remove(i);
            }
        }
        return mappedProducts;
    }

    private OpenFoodFactsResponseDto getData(String request) {
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
                        .queryParam("page_size", SwissFoodDbClient.MAX_SEARCH_RESULTS)
                        .queryParam("json", "1")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(OpenFoodFactsResponseDto.class);
    }

    private String getNameByLanguage(OpenFoodFactsFoodProductDto product, String language) {
        String languageSpecificName = switch (language) {
            case "de" -> product.getGermanName();
            case "en" -> product.getEnglishName();
            default -> null;
        };
        // Prefer language-specific name, fall back to general name
        // StringUtils handles null values gracefully
        if (StringUtils.isNotEmpty(languageSpecificName)) {
            return languageSpecificName;
        } else if (StringUtils.isNotEmpty(product.getName())) {
            return product.getName();
        } else {
            return null; // This will trigger removal
        }
    }
}
