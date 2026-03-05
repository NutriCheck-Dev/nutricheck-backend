package com.nutricheck.backend.dto;

import com.nutricheck.backend.TestDataFactory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReportDtoValidationTest {

    private Validator validator;

    @BeforeAll
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validReportDTOTest() {
        ReportDto reportDTO = TestDataFactory.createDefaultReportDTO();

        Set<ConstraintViolation<ReportDto>> violations = validator.validate(reportDTO);
        assertTrue(violations.isEmpty(), "Valid ReportDto should have no violations");
    }

    @Test
    void reportDTOWithBlankRecipeIdTest() {
        ReportDto reportDTO = TestDataFactory.createDefaultReportDTO();
        reportDTO.setRecipeId("");

        Set<ConstraintViolation<ReportDto>> violations = validator.validate(reportDTO);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Recipe ID for a Report cannot be blank")),
                "Expected violation message for blank recipeId not found");
    }
}
