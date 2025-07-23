package com.nutricheck.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

/**
 * Data Transfer Object (DTO) for recipe information.
 * This class is used to transfer recipe data over the REST API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    @NotBlank(message = "ID of a recipe cannot be blank")
    private String id;
    @NotBlank(message = "Name of a recipe cannot be blank")
    private String name;
    private String instructions;
    @Positive(message = "Servings of a recipe must be a positive number")
    private int servings;
    @Positive(message = "Calories of a recipe must be a positive number")
    private double calories;
    @PositiveOrZero(message = "Carbohydrates of a recipe must be a non-negative number")
    private double carbohydrates;
    @PositiveOrZero(message = "Protein of a recipe must be a non-negative number")
    private double protein;
    @PositiveOrZero(message = "Fat of a recipe must be a non-negative number")
    private double fat;
    @Size(min = 2, message = "A recipe must contain at least two ingredients")
    private Set<IngredientDTO> ingredients;
}
