package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
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
  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodProduct that = (FoodProduct) o;
        // id might be different for the same swiss food product as they are generated in the client
        return Objects.equals(name, that.getName()) &&
                Objects.equals(calories, that.getCalories()) &&
                Objects.equals(carbohydrates, that.getCarbohydrates()) &&
                Objects.equals(protein, that.getProtein()) &&
                Objects.equals(fat, that.getFat());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, calories, carbohydrates, protein, fat);
    }

}
