package com.nutricheck.backend.dto.external;

import lombok.*;

import java.util.List;

/**
 * Represents the highest-level response from the Open Food Facts API
 * consisting of list of food products.
 * It is used to map the JSON response from the Open Food Facts API to a Java object.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenFoodFactsResponseDto {
    private List<OpenFoodFactsFoodProductDto> products;
}
