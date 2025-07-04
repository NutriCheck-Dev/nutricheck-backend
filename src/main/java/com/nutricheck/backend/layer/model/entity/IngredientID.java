package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientID implements Serializable {

    private String recipeId;
    private String foodProductId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientID that = (IngredientID) o;
        return recipeId.equals(that.recipeId) && foodProductId.equals(that.foodProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, foodProductId);
    }

}
