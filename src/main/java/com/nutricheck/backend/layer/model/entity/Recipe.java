package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing a recipe in the system.
 * This class is used to map recipe data to the database.
 */
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe extends Nutriment {

    @Id
    private String id;

    private String name;
    private String instructions;
    private int servings;

    @Builder.Default
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Ingredient> ingredients = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        // nutrition values are not considered in equality check because they are calculated from ingredients
        return Objects.equals(name, recipe.getName()) &&
                Objects.equals(instructions, recipe.getInstructions()) &&
                Objects.equals(servings, recipe.getServings()) &&
                Objects.equals(ingredients, recipe.getIngredients());
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, instructions, servings, ingredients);
    }
}
