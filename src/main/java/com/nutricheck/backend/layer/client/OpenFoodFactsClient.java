package com.nutricheck.backend.layer.client;

import com.alpermulayim.openfoodfacts_spring_boot_starter.OpenFoodFactsApi;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsPageResponse;
import org.springframework.stereotype.Component;

@Component
public class OpenFoodFactsClient implements FoodDBClient {

    private final OpenFoodFactsApi webClient;

    public OpenFoodFactsClient(OpenFoodFactsApi webClient) {
        this.webClient = webClient;
    }

    @Override
    public OpenFoodFactsPageResponse search(ProductSearchRequest request) {
        return null;
    }
}
