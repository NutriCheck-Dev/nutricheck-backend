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
class FoodProductDTOValidationTest {
    private Validator validator;

    @BeforeAll
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validFoodProductDTOTest() {
        FoodProductDTO foodProductDTO = TestDataFactory.createDefaultFoodProductDTO();

        Set<ConstraintViolation<FoodProductDTO>> violations = validator.validate(foodProductDTO);
        assertTrue(violations.isEmpty(), "Valid FoodProductDTO should have no violations");
    }

    // As an exemplary validation test case
    @Test
    void foodProductDTOWithBlankIdTest() {
        FoodProductDTO foodProductDTO = TestDataFactory.createDefaultFoodProductDTO();
        foodProductDTO.setId("");

        Set<ConstraintViolation<FoodProductDTO>> violations = validator.validate(foodProductDTO);
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("ID of a food product cannot be blank")),
                "FoodProductDTO with blank ID should have a violation");
    }
}
