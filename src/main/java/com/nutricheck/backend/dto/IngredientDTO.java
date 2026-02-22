package com.nutricheck.backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

/**
 * Data Transfer Object (DTO) for ingredients of a <code>RecipeDTO</code>.
 * This class is used to transfer ingredient data over the REST API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {
    @NotBlank(message = "Recipe ID for an ingredient cannot be blank")
    private String recipeId;
    @NotBlank(message = "Food product ID for an ingredient cannot be blank")
    private String foodProductId;
    @Valid
    @NotNull(message = "An Ingredient must have a food product")
    private FoodProductDTO foodProduct;
    @Positive(message = "Quantity of an ingredient must be a positive number")
    private double quantity;
}
