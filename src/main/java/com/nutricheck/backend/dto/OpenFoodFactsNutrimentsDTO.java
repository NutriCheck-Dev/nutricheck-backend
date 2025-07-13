package com.nutricheck.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenFoodFactsNutrimentsDTO {
    @JsonProperty("fat")
    private Double fat;
    @JsonProperty("energy-kcal")
    private Double energyKcal;
    @JsonProperty("carbohydrates")
    private Double carbohydrates;
    @JsonProperty("proteins")
    private Double proteins;
}
