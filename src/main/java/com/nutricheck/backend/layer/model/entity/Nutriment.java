package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.MappedSuperclass;

/**
 * Abstract class representing the nutritional information of a food product or recipe.
 * Prevents code duplication by providing common nutritional fields.
 */
@MappedSuperclass
public abstract class Nutriment {
    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;

    protected Nutriment() {
    }

    protected Nutriment(double calories, double carbohydrates, double protein, double fat) {
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
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
}
