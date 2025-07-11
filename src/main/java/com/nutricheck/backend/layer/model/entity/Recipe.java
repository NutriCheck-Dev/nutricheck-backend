package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    private String id;

    private String name;
    private String instructions;
    private int servings;

    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;

    @Builder.Default
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Ingredient> ingredients = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
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
