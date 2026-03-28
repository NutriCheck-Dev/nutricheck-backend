package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

/**
 * Composite key class for the Ingredient entity.
 * This class is used to uniquely identify an ingredient in a recipe
 * based on the recipe ID and food product ID.
 */
@Embeddable
public class IngredientId implements Serializable {

    private String recipeId;
    private String foodProductId;

    public IngredientId() {
    }

    public IngredientId(String recipeId, String foodProductId) {
        this.recipeId = recipeId;
        this.foodProductId = foodProductId;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getFoodProductId() {
        return foodProductId;
    }

    public void setFoodProductId(String foodProductId) {
        this.foodProductId = foodProductId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        IngredientId that = (IngredientId) obj;
        return Objects.equals(recipeId, that.recipeId) &&
                Objects.equals(foodProductId, that.foodProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, foodProductId);
    }
}
