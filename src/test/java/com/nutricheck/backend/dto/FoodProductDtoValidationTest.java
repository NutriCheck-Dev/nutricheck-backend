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
class FoodProductDtoValidationTest {
    private Validator validator;

    @BeforeAll
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validFoodProductDTOTest() {
        FoodProductDto foodProductDTO = TestDataFactory.createDefaultFoodProductDTO();

        Set<ConstraintViolation<FoodProductDto>> violations = validator.validate(foodProductDTO);
        assertTrue(violations.isEmpty(), "Valid FoodProductDto should have no violations");
    }

    // As an exemplary validation test case
    @Test
    void foodProductDTOWithBlankIdTest() {
        FoodProductDto foodProductDTO = TestDataFactory.createDefaultFoodProductDTO();
        foodProductDTO.setId("");

        Set<ConstraintViolation<FoodProductDto>> violations = validator.validate(foodProductDTO);
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("ID of a food product cannot be blank")),
                "FoodProductDto with blank ID should have a violation");
    }
}
