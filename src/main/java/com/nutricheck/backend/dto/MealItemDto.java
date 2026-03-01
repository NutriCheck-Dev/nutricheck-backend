package com.nutricheck.backend.dto;

import lombok.*;

/**
 * Data Transfer Object (DTO) for the items of a <code>MealDto</code>.
 * This class is used to transfer meal item data over the REST API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealItemDto {
    private String foodProductId;
    private FoodProductDto foodProduct;
}
