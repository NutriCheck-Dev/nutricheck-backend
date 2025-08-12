package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.*;
import lombok.*;


/**
 * Entity representing an ingredient in a recipe.
 * This class is used to map ingredient data to the database.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @EmbeddedId
    private IngredientID id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        // check for null id to avoid comparing transient entities
        return id != null && id.equals(that.id);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
