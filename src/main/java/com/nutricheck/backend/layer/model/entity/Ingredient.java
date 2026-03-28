package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.*;

import java.util.Objects;


/**
 * Entity representing an ingredient in a recipe.
 * This class is used to map ingredient data to the database.
 */
@Entity
public class Ingredient {

    @EmbeddedId
    private IngredientId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    /*
     * only cascade persist (needed when creating a recipe with a new product) and merge
     * (needed as our food products have ids already set and Spring Data JPA save method
     *  will try to merge them even if they are transient)
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("foodProductId")
    @JoinColumn(name = "food_product_id")
    private FoodProduct foodProduct;

    private double quantity;

    public Ingredient() {
    }

    public Ingredient(IngredientId id, Recipe recipe, FoodProduct foodProduct, double quantity) {
        this.id = id;
        this.recipe = recipe;
        this.foodProduct = foodProduct;
        this.quantity = quantity;
    }

    public IngredientId getId() {
        return id;
    }

    public void setId(IngredientId id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public FoodProduct getFoodProduct() {
        return foodProduct;
    }

    public void setFoodProduct(FoodProduct foodProduct) {
        this.foodProduct = foodProduct;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
