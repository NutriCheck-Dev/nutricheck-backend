package com.nutricheck.backend.layer.client;

import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsPageResponse;

public interface FoodDBClient {

    OpenFoodFactsPageResponse search(ProductSearchRequest request);

}
