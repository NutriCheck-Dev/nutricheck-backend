package com.nutricheck.backend.integration;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.RecipeDto;
import com.nutricheck.backend.dto.ReportDto;
import com.nutricheck.backend.layer.model.entity.Report;
import com.nutricheck.backend.layer.model.repository.FoodProductRepository;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import com.nutricheck.backend.layer.model.repository.ReportRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(IntegrationTestConfig.class)
@ActiveProfiles("test")
class RecipeIT {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ReportRepository reportRepo;
    @Autowired
    private RecipeRepository recipeRepo;
    @Autowired
    private FoodProductRepository foodProductRepo;

    @AfterEach
    void tearDown() {
        reportRepo.deleteAll();
        recipeRepo.deleteAll();
    }

    @Test
    void uploadReportTest() {
        recipeRepo.save(TestDataFactory.createDefaultRecipe());

        ResponseEntity<ReportDto> response = restTemplate.postForEntity("/user/recipes/report", TestDataFactory.createDefaultReportDTO(), ReportDto.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ReportDto result = response.getBody();
        ReportDto expected = TestDataFactory.createDefaultReportDTO();
        // do not compare the ids because id is generated for Reports
        assertEquals(expected.getDescription(), result.getDescription());
        assertEquals(expected.getRecipeId(), result.getRecipeId());
        assertEquals(1, reportRepo.count());
        Report found = reportRepo.findAll().get(0);
        assertEquals(expected.getDescription(), found.getDescription());
        assertEquals(expected.getRecipeId(), found.getRecipeId());
    }

    @Test
    void uploadReportForNonExistingRecipeTest() {
        ResponseEntity<ReportDto> response = restTemplate.postForEntity("/user/recipes/report", TestDataFactory.createDefaultReportDTO(), ReportDto.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(0, reportRepo.count());
    }

    @Test
    void uploadRecipeTest() {
        RecipeDto toUpload = TestDataFactory.createDefaultRecipeDTO();
        ResponseEntity<RecipeDto> response = restTemplate.postForEntity("/user/recipes", toUpload, RecipeDto.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        RecipeDto result = response.getBody();
        assertEquals(toUpload, result);
        assertEquals(1, recipeRepo.count());
        assertEquals(toUpload.getId(), recipeRepo.findAll().get(0).getId());
        assertEquals(2, foodProductRepo.count());
    }

    @Test
    void uploadRecipeWithExistingFoodProductsTest() {
        foodProductRepo.save(TestDataFactory.createDefaultFoodProduct());
        foodProductRepo.save(TestDataFactory.createDefaultFoodProduct2());

        RecipeDto toUpload = TestDataFactory.createDefaultRecipeDTO();
        ResponseEntity<RecipeDto> response = restTemplate.postForEntity("/user/recipes", toUpload, RecipeDto.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        RecipeDto result = response.getBody();
        assertEquals(toUpload, result);
        assertEquals(1, recipeRepo.count());
        assertEquals(toUpload.getId(), recipeRepo.findAll().get(0).getId());
        assertEquals(2, foodProductRepo.count());
    }

    @Test
    void uploadDuplicateRecipeTest() {
        RecipeDto toUpload = TestDataFactory.createDefaultRecipeDTO();
        recipeRepo.save(TestDataFactory.createDefaultRecipe());

        ResponseEntity<RecipeDto> response = restTemplate.postForEntity("/user/recipes", toUpload, RecipeDto.class);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
}
