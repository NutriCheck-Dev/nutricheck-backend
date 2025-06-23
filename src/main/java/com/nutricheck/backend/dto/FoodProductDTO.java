package com.nutricheck.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodProductDTO {
    @NotBlank(message = "ID of a food product cannot be blank")
    private String id;
    @NotBlank(message = "Name of a food product cannot be blank")
    private String name;
    @Positive(message = "Calories of a food product must be a positive number")
    private double calories;
    @PositiveOrZero(message = "Carbohydrates of a food product must be a non-negative number")
    private double carbohydrates;
    @PositiveOrZero(message = "Protein of a food product must be a non-negative number")
    private double protein;
    @PositiveOrZero(message = "Fat of a food product must be a non-negative number")
    private double fat;
}
