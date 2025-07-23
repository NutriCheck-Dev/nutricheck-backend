package com.nutricheck.backend.dto;

import lombok.*;

/**
 * Data Transfer Object (DTO) for the items of a <code>MealDTO</code>.
 * This class is used to transfer meal item data over the REST API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealItemDTO {
    private String foodProductId;
    private FoodProductDTO foodProduct;
}
