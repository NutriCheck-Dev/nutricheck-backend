package com.nutricheck.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// FIXME: How to set the foodProduct of the Ingredient entity to an already existing one? PostMapping or Cascade.Persist?
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {
    @NotBlank(message = "Food ID for an Ingredient cannot be blank")
    private String foodId;
    @NotNull(message = "An Ingredient must have a food product")
    private FoodProductDTO foodProduct;
    @Positive(message = "Quantity of an ingredient must be a positive number")
    private double quantity;
}
