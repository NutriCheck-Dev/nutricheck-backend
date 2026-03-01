package com.nutricheck.backend.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * DTO representing the nutriments of a food product from Open Food Facts.
 * This class is used to map the JSON response from the Open Food Facts API.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenFoodFactsNutrimentsDto {
    private double fat;
    @JsonProperty("energy-kcal")
    private double energyKcal;
    private double carbohydrates;
    private double proteins;
}
