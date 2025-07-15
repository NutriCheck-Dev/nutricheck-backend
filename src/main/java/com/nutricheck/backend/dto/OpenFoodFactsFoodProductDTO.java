package com.nutricheck.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenFoodFactsFoodProductDTO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("product_name")
    private String name;
    @JsonProperty("nutriments")
    private OpenFoodFactsNutrimentsDTO nutriments;
}
