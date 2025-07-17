package com.nutricheck.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenFoodFactsNutrimentsDTO {
    private double fat;
    @JsonProperty("energy-kcal")
    private double energyKcal;
    private double carbohydrates;
    private double proteins;
}
