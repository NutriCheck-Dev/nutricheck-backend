package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientID implements Serializable {

    private String recipeId;
    private String foodProductId;

}
