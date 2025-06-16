package com.backend.nutricheck.model.entity;

import jakarta.persistence.Entity;

@Entity
public class RecipeContentEntity {
    private Long recipeId;
    private Long foodId;
    private double quantity;
}
