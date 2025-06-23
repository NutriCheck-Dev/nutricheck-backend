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
public class MealDTO {
    @NotBlank(message = "ID of a meal cannot be blank")
    private String id;
    @Positive(message = "Calories of a meal must be a positive number")
    private double calories;
    @PositiveOrZero(message = "Carbohydrates of a meal must be a non-negative number")
    private double carbohydrates;
    @PositiveOrZero(message = "Protein of a meal must be a non-negative number")
    private double protein;
    @PositiveOrZero(message = "Fat of a meal must be a non-negative number")
    private double fat;
    @Size(min = 1, message = "A meal must contain at least one food product")
    private Set<IngredientDTO> ingredients;
}
