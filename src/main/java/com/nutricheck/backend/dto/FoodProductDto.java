package com.nutricheck.backend.dto;

import com.nutricheck.backend.Utils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for food product information.
 * This class is used to transfer food product data over the REST API.
 */
public class FoodProductDto {
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

    public FoodProductDto() {
    }

    public FoodProductDto(String id, String name, double calories, double carbohydrates, double protein, double fat) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
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

    @Override
    public String toString() {
        return "FoodProductDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                ", carbohydrates=" + carbohydrates +
                ", protein=" + protein +
                ", fat=" + fat +
                '}';
    }

    // Exclude 'id' because it is generated for swiss products
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodProductDto that = (FoodProductDto) o;
        return Utils.compareDouble(that.calories, calories) == 0 &&
                Utils.compareDouble(that.carbohydrates, carbohydrates) == 0 &&
                Utils.compareDouble(that.protein, protein) == 0 &&
                Utils.compareDouble(that.fat, fat) == 0 &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, calories, carbohydrates, protein, fat);
    }
}
