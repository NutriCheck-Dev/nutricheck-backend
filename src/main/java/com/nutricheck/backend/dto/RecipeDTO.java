package com.nutricheck.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
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
    @PositiveOrZero(message = "Rating count of a recipe must be a non-negative number")
    private int ratingCount;
    @PositiveOrZero(message = "Average rating of a recipe must be a non-negative number")
    private double averageRating;
    // FIXME: Is this a correct approach?
    @Size(min = 2, message = "A recipe must contain at least two ingredients")
    private Set<IngredientDTO> ingredients;
}
