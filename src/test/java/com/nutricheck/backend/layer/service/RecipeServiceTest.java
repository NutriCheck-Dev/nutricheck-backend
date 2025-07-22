package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.layer.model.entity.Recipe;
import com.nutricheck.backend.layer.model.entity.Report;
import com.nutricheck.backend.layer.model.repository.FoodProductRepository;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import com.nutricheck.backend.layer.model.repository.ReportRepository;
import com.nutricheck.backend.layer.service.impl.RecipeServiceImpl;
import com.nutricheck.backend.layer.service.mapper.IngredientMapper;
import com.nutricheck.backend.layer.service.mapper.RecipeMapper;
import com.nutricheck.backend.layer.service.mapper.ReportMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private FoodProductRepository foodProductRepository;
    @Mock
    private ReportRepository reportRepository;
    @Mock
    private RecipeMapper recipeMapper;
    @Mock
    private IngredientMapper ingredientMapper;
    @Mock
    private ReportMapper reportMapper;

    private RecipeServiceImpl recipeService;

    @BeforeEach
    void setUp() {
        recipeService = new RecipeServiceImpl(recipeRepository, foodProductRepository, reportRepository,
                recipeMapper, ingredientMapper, reportMapper);
    }

    @Test
    void uploadRecipeTest() {}

    @Test
    void reportRecipeTest() {
        Report report = TestDataFactory.createDefaultReport();
        ReportDTO expectedReportDTO = TestDataFactory.createDefaultReportDTO();
        given(reportMapper.toEntity(expectedReportDTO)).willReturn(report);
        given(recipeRepository.findById(report.getRecipeId()))
                .willReturn(Optional.of(TestDataFactory.createDefaultRecipe()));

        ReportDTO actualReport = recipeService.reportRecipe(expectedReportDTO);
        assertEquals(expectedReportDTO, actualReport);
    }

    @Test
    void downloadRecipeTest() {
        String recipeId = "testRecipeId";
        RecipeDTO expectedRecipeDTO = TestDataFactory.createDefaultRecipeDTO();
        given(recipeRepository.findById(recipeId))
                .willReturn(Optional.of(TestDataFactory.createDefaultRecipe()));
        Recipe recipe = TestDataFactory.createDefaultRecipe();
        given(recipeMapper.toDTO(recipe)).willReturn(expectedRecipeDTO);

        RecipeDTO actualRecipeDTO = recipeService.downloadRecipe(recipeId);
        assertEquals(expectedRecipeDTO, actualRecipeDTO);
    }
}