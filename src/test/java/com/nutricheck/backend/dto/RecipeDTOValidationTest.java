package com.nutricheck.backend.dto;

import com.nutricheck.backend.TestDataFactory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RecipeDTOValidationTest {
    private Validator validator;

    @BeforeAll
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validRecipeDTOTest() {
        RecipeDTO recipeDTO = TestDataFactory.createDefaultRecipeDTO();

        Set<ConstraintViolation<RecipeDTO>> violations = validator.validate(recipeDTO);
        assertTrue(violations.isEmpty(), "Valid RecipeDTO should have no violations");
    }

    // As an exemplary validation test case
    @Test
    void recipeDTOWithZeroIngredientsTest() {
        RecipeDTO recipeDTO = TestDataFactory.createDefaultRecipeDTO();
        recipeDTO.setIngredients(Set.of());

        Set<ConstraintViolation<RecipeDTO>> violations = validator.validate(recipeDTO);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("A recipe must contain at least two ingredients")),
                "Expected violation message for zero ingredients of a recipe not found");
    }
}
