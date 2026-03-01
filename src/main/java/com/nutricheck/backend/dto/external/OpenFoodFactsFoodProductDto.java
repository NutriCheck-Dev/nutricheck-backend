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
public class OpenFoodFactsFoodProductDto {
    @JsonProperty("_id")
    private String id;
    @JsonProperty("product_name")
    private String name;
    @JsonProperty("product_name_de")
    private String germanName;
    @JsonProperty("product_name_en")
    private String englishName;
    private OpenFoodFactsNutrimentsDto nutriments;
}
