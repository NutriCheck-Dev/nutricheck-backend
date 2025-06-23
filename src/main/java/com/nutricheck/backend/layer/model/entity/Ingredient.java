package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @EmbeddedId
    private IngredientID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("foodId")
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("foodProductId")
    @JoinColumn(name = "food_product_id")
    private FoodProduct foodProduct;

    private double quantity;

    @Override
    public boolean equals(Object o) {
        // TODO: Implement proper equals method for duplicate checking
        return false;
    }
    @Override
    public int hashCode() {
        // TODO: Implement proper hash
        return 0;
    }
}
