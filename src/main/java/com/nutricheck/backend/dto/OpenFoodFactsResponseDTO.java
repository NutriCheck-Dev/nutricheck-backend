package com.nutricheck.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nutricheck.backend.layer.model.entity.FoodProduct;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenFoodFactsResponseDTO {
    @JsonProperty("products")
    private List<OpenFoodFactsFoodProductDTO> products;
}
