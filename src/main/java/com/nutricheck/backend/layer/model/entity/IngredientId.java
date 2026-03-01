package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

/**
 * Composite key class for the Ingredient entity.
 * This class is used to uniquely identify an ingredient in a recipe
 * based on the recipe ID and food product ID.
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientId implements Serializable {

    private String recipeId;
    private String foodProductId;

}
