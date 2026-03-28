package com.nutricheck.backend.dto;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) for the items of a <code>MealDto</code>.
 * This class is used to transfer meal item data over the REST API.
 */
public class MealItemDto {
    private String foodProductId;
    private FoodProductDto foodProduct;

    public MealItemDto() {
    }

    public MealItemDto(String foodProductId, FoodProductDto foodProduct) {
        this.foodProductId = foodProductId;
        this.foodProduct = foodProduct;
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

    @Override
    public String toString() {
        return "MealItemDto{" +
                "foodProductId='" + foodProductId + '\'' +
                ", foodProduct=" + foodProduct +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealItemDto that = (MealItemDto) o;
        return Objects.equals(foodProductId, that.foodProductId) &&
                Objects.equals(foodProduct, that.foodProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodProductId, foodProduct);
    }
}
