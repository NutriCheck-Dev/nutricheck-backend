package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeContent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long recipeId;
    private Long foodId;
    private double quantity;
}
