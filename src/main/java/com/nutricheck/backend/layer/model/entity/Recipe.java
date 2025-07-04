package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
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
