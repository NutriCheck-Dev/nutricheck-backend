package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

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
        return Objects.equals(this.id.getFoodProductId(), that.getId().getFoodProductId()) &&
                this.quantity == that.getQuantity();
    }
    @Override
    public int hashCode() {
        return Objects.hash(id.getFoodProductId(), quantity);
    }
}
