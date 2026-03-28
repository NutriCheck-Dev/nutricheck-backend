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
class RecipeDtoValidationTest {
    private Validator validator;

    @BeforeAll
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validRecipeDTOTest() {
        RecipeDto recipeDTO = TestDataFactory.createDefaultRecipeDTO();

        Set<ConstraintViolation<RecipeDto>> violations = validator.validate(recipeDTO);
        assertTrue(violations.isEmpty(), "Valid RecipeDto should have no violations");
    }

    // As an exemplary validation test case
    @Test
    void recipeDTOWithZeroIngredientsTest() {
        RecipeDto recipeDTO = TestDataFactory.createDefaultRecipeDTO();
        recipeDTO.setIngredients(Set.of());

        Set<ConstraintViolation<RecipeDto>> violations = validator.validate(recipeDTO);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("A recipe must contain at least two ingredients")),
                "Expected violation message for zero ingredients of a recipe not found");
    }
}
