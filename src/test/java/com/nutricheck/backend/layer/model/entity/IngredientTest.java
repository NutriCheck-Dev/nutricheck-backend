package com.nutricheck.backend.layer.model.entity;

import com.nutricheck.backend.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTest {

    private Ingredient ingredient;

    @BeforeEach
    void setup() {
        ingredient = TestDataFactory.createDefaultIngredient();
    }

    @Test
    void equalsTest() {
        Ingredient expectedIngredient = TestDataFactory.createDefaultIngredient();
        assertEquals(expectedIngredient, ingredient);
    }

    @Test
    void hashCodeConsistencyTest() {
        int hashCode1 = ingredient.hashCode();
        int hashCode2 = ingredient.hashCode();
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void hashCodeEqualityTest() {
        Ingredient expectedIngredient = TestDataFactory.createDefaultIngredient();
        assertEquals(expectedIngredient.hashCode(), ingredient.hashCode());
    }
}
