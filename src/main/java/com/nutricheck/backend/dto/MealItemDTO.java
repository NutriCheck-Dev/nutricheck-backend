package com.nutricheck.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealItemDTO {
    private String mealId;
    private String foodProductId;
    private FoodProductDTO foodProduct;
}
