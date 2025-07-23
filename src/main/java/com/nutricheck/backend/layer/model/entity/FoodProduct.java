package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a food product in the system.
 * This class is used to map food product data to the database.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodProduct {
    @Id
    private String id;
    private String name;

    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;

    @Builder.Default
    @OneToMany(mappedBy = "foodProduct", fetch = FetchType.LAZY)
    private Set<Ingredient> references = new HashSet<>();

    /**
     * Helper method to a reference to this food product.
     * This method is used to maintain the relationship between food products and ingredients.
     *
     * Is needed as getter returns an unmodifiable set.
     *
     * @param ingredient the ingredient that references this food product
     */
    public void addReference(Ingredient ingredient) {
        references.add(ingredient);
    }

}
