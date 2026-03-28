package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing a recipe in the system.
 * This class is used to map recipe data to the database.
 */
@Entity
public class Recipe extends Nutriment {

    @Id
    private String id;

    private String name;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String instructions;
    private int servings;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Ingredient> ingredients = new HashSet<>();

    public Recipe() {
        this.ingredients = new HashSet<>();
    }

    public Recipe(String id, String name, String instructions, int servings, double calories, double carbohydrates, double protein, double fat, Set<Ingredient> ingredients) {
        super(calories, carbohydrates, protein, fat);
        this.id = id;
        this.name = name;
        this.instructions = instructions;
        this.servings = servings;
        this.ingredients = ingredients == null ? new HashSet<>() : ingredients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id != null && id.equals(recipe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
