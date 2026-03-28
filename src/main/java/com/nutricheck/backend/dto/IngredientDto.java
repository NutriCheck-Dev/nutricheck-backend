package com.nutricheck.backend.dto;

import com.nutricheck.backend.Utils;
import  jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for ingredients of a <code>RecipeDto</code>.
 * This class is used to transfer ingredient data over the REST API.
 */
public class IngredientDto {
    @NotBlank(message = "Recipe ID for an ingredient cannot be blank")
    private String recipeId;
    @NotBlank(message = "Food product ID for an ingredient cannot be blank")
    private String foodProductId;
    @Valid
    @NotNull(message = "An Ingredient must have a food product")
    private FoodProductDto foodProduct;
    @Positive(message = "Quantity of an ingredient must be a positive number")
    private double quantity;

    public IngredientDto() {
    }

    public IngredientDto(String recipeId, String foodProductId, FoodProductDto foodProduct, double quantity) {
        this.recipeId = recipeId;
        this.foodProductId = foodProductId;
        this.foodProduct = foodProduct;
        this.quantity = quantity;
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

    public FoodProductDto getFoodProduct() {
        return foodProduct;
    }

    public void setFoodProduct(FoodProductDto foodProduct) {
        this.foodProduct = foodProduct;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "IngredientDto{" +
                "recipeId='" + recipeId + '\'' +
                ", foodProductId='" + foodProductId + '\'' +
                ", foodProduct=" + foodProduct +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientDto that = (IngredientDto) o;
        return Utils.compareDouble(quantity, that.quantity) == 0 &&
                Objects.equals(recipeId, that.recipeId) &&
                Objects.equals(foodProductId, that.foodProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, foodProductId, quantity);
    }
}
