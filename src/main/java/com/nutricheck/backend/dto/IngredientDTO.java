package com.nutricheck.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {
    @NotBlank(message = "Food ID for an ingredient cannot be blank")
    private String foodId;
    @NotBlank(message = "Food product ID for an ingredient cannot be blank")
    private String foodProductId;
    @NotNull(message = "An Ingredient must have a food product")
    private FoodProductDTO foodProduct;
    @Positive(message = "Quantity of an ingredient must be a positive number")
    private double quantity;
}
