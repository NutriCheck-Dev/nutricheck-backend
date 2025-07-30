package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FoodProduct extends Nutriment {
    @Id
    private String id;
    private String name;

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
                Objects.equals(getCalories(), that.getCalories()) &&
                Objects.equals(getCarbohydrates(), that.getCarbohydrates()) &&
                Objects.equals(getProtein(), that.getProtein()) &&
                Objects.equals(getFat(), that.getFat());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getCalories(), getCarbohydrates(), getProtein(), getFat());
    }

}
