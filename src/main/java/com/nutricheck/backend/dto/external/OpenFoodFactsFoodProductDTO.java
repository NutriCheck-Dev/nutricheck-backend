package com.nutricheck.backend.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Represents a food product from the Open Food Facts API.
 * This class is used to map the JSON response from the Open Food Facts API to a Java object.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenFoodFactsFoodProductDTO {
    @JsonProperty("_id")
    private String id;
    @JsonProperty("product_name")
    private String name;
    private OpenFoodFactsNutrimentsDTO nutriments;
}
