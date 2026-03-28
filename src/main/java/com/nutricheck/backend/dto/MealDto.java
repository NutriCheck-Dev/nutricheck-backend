package com.nutricheck.backend.dto;


import com.nutricheck.backend.Utils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Data Transfer Object (DTO) for meal information.
 * This class is used to transfer meal data over the REST API.
 */
public class MealDto {
    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;
    private Set<MealItemDto> items;

    public MealDto() {
        this.items = new HashSet<>();
    }

    public MealDto(double calories, double carbohydrates, double protein, double fat, Set<MealItemDto> items) {
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
        this.items = items == null ? new HashSet<>() : items;
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

    public Set<MealItemDto> getItems() {
        return items;
    }

    public void setItems(Set<MealItemDto> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "MealDto{" +
                "calories=" + calories +
                ", carbohydrates=" + carbohydrates +
                ", protein=" + protein +
                ", fat=" + fat +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealDto mealDto = (MealDto) o;
        return Utils.compareDouble(mealDto.calories, calories) == 0 &&
                Utils.compareDouble(mealDto.carbohydrates, carbohydrates) == 0 &&
                Utils.compareDouble(mealDto.protein, protein) == 0 &&
                Utils.compareDouble(mealDto.fat, fat) == 0 &&
                Objects.equals(items, mealDto.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calories, carbohydrates, protein, fat, items);
    }
}
