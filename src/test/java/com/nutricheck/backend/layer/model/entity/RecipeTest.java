package com.nutricheck.backend.layer.model.entity;

import com.nutricheck.backend.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeTest {

    private Recipe recipe;

    @BeforeEach
    void setup() {
        recipe = TestDataFactory.createDefaultRecipe();
    }

    @Test
    void equalsTest() {
        Recipe expectedRecipe = TestDataFactory.createDefaultRecipe();

        assertEquals(expectedRecipe, recipe);
    }

    @Test
    void hashCodeConsistencyTest() {
        int hashCode1 = recipe.hashCode();
        int hashCode2 = recipe.hashCode();
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void hashCodeEqualityTest() {
        Recipe expectedRecipe = TestDataFactory.createDefaultRecipe();
        assertEquals(expectedRecipe.hashCode(), recipe.hashCode());
    }
}
