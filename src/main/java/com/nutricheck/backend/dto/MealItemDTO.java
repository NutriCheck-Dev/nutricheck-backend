package com.nutricheck.backend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealItemDTO {
    private String foodProductId;
    private FoodProductDTO foodProduct;
}
