package com.nutricheck.backend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RecipeContent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long recipeId;
    private Long foodId;
    private double quantity;
}
