package com.nutricheck.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenFoodFactsFoodProductDTO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("nutriments")
    private OpenFoodFactsNutrimentsDTO nutriments;
}
