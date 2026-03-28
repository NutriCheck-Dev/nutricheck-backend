package com.nutricheck.backend.dto;

import com.nutricheck.backend.Utils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Data Transfer Object (DTO) for recipe information.
 * This class is used to transfer recipe data over the REST API.
 */
public class RecipeDto {
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
    @Valid
    @Size(min = 2, message = "A recipe must contain at least two ingredients")
    private Set<IngredientDto> ingredients;

    public RecipeDto() {
        this.ingredients = new HashSet<>();
    }

    public RecipeDto(String id, String name, String instructions, int servings, double calories, double carbohydrates, double protein, double fat, Set<IngredientDto> ingredients) {
        this.id = id;
        this.name = name;
        this.instructions = instructions;
        this.servings = servings;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
        this.ingredients = ingredients == null ? new HashSet<>() : ingredients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public Set<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "RecipeDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", instructions='" + instructions + '\'' +
                ", servings=" + servings +
                ", calories=" + calories +
                ", carbohydrates=" + carbohydrates +
                ", protein=" + protein +
                ", fat=" + fat +
                ", ingredients=" + ingredients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeDto recipeDto = (RecipeDto) o;
        return servings == recipeDto.servings &&
                Utils.compareDouble(recipeDto.calories, calories) == 0 &&
                Utils.compareDouble(recipeDto.carbohydrates, carbohydrates) == 0 &&
                Utils.compareDouble(recipeDto.protein, protein) == 0 &&
                Utils.compareDouble(recipeDto.fat, fat) == 0 &&
                Objects.equals(id, recipeDto.id) &&
                Objects.equals(name, recipeDto.name) &&
                Objects.equals(instructions, recipeDto.instructions) &&
                Objects.equals(ingredients, recipeDto.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, instructions, servings, calories, carbohydrates, protein, fat, ingredients);
    }
}
