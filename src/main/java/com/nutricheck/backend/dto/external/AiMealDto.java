package com.nutricheck.backend.dto.external;

import com.nutricheck.backend.Utils;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) for AI-generated meal information.
 */
public class AiMealDto {
    private String name;
    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;

    public AiMealDto() {
    }

    public AiMealDto(String name, double calories, double carbohydrates, double protein, double fat) {
        this.name = name;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
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
        return "AiMealDto{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", carbohydrates=" + carbohydrates +
                ", protein=" + protein +
                ", fat=" + fat +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AiMealDto aiMealDto = (AiMealDto) o;
        return Utils.compareDouble(aiMealDto.calories, calories) == 0 &&
                Utils.compareDouble(aiMealDto.carbohydrates, carbohydrates) == 0 &&
                Utils.compareDouble(aiMealDto.protein, protein) == 0 &&
                Utils.compareDouble(aiMealDto.fat, fat) == 0 &&
                Objects.equals(name, aiMealDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, calories, carbohydrates, protein, fat);
    }
}
