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
class IngredientDtoValidationTest {
    private Validator validator;

    @BeforeAll
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void validIngredientDTOTest() {
        IngredientDto ingredientDTO = TestDataFactory.createDefaultIngredientDTO();

        Set<ConstraintViolation<IngredientDto>> violations = validator.validate(ingredientDTO);
        assertTrue(violations.isEmpty(), "Valid IngredientDto should have no violations");
    }

    // As an exemplary validation test case
    @Test
    void ingredientDTOWithNegativeQuantityTest() {
        IngredientDto ingredientDTO = TestDataFactory.createDefaultIngredientDTO();
        ingredientDTO.setQuantity(-100);

        Set<ConstraintViolation<IngredientDto>> violations = validator.validate(ingredientDTO);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Quantity of an ingredient must be a positive number")),
                "Expected violation message for negative quantity of ingredient not found");
    }
}
