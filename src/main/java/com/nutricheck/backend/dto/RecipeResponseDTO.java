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
public class RecipeResponseDTO {
    private String id;
    private String name;
    private String instructions;
    private int servings;
    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;
    private int ratingCount;
    private double averageRating;
    private Set<IngredientDTO> ingredients;
}