package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.exception.DuplicateRecipeException;
import com.nutricheck.backend.exception.RecipeNotFoundException;
import com.nutricheck.backend.layer.model.entity.Ingredient;
import com.nutricheck.backend.layer.model.entity.Recipe;
import com.nutricheck.backend.layer.model.entity.Report;
import com.nutricheck.backend.layer.model.repository.FoodProductRepository;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import com.nutricheck.backend.layer.model.repository.ReportRepository;
import com.nutricheck.backend.layer.service.impl.RecipeServiceImpl;
import com.nutricheck.backend.layer.service.mapper.FoodProductMapper;
import com.nutricheck.backend.layer.service.mapper.IngredientMapper;
import com.nutricheck.backend.layer.service.mapper.RecipeMapper;
import com.nutricheck.backend.layer.service.mapper.ReportMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.any;
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
    private FoodProductMapper foodProductMapper;
    @Mock
    private ReportMapper reportMapper;
    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Test
    void uploadRecipeTest() {
        RecipeDTO expectedRecipeDTO = TestDataFactory.createDefaultRecipeDTO();
        Recipe recipe = TestDataFactory.createDefaultRecipe();
        Ingredient recipeIngredient = recipe.getIngredients().iterator().next();

        given(recipeRepository.findByNameAndInstructions(expectedRecipeDTO.getName(), expectedRecipeDTO.getInstructions()))
                .willReturn(List.of());
        given(recipeMapper.toDTO(anyList())).willReturn(List.of());
        given(recipeMapper.toEntity(expectedRecipeDTO)).willReturn(recipe);
        given(ingredientMapper.toEntity(any())).willReturn(recipeIngredient);
        given(foodProductMapper.toEntity((FoodProductDTO) any())).willReturn(TestDataFactory.createDefaultFoodProduct());
        given(foodProductRepository.findAll()).willReturn(List.of());
        given(recipeRepository.save(recipe)).willReturn(recipe);
        given(recipeMapper.toDTO(recipe)).willReturn(expectedRecipeDTO);

        RecipeDTO actualRecipeDTO = recipeService.uploadRecipe(expectedRecipeDTO);
        assertEquals(expectedRecipeDTO, actualRecipeDTO);
    }

    @Test
    void uploadDuplicateRecipeTest() {
        RecipeDTO recipeDTO = TestDataFactory.createDefaultRecipeDTO();
        Recipe existingRecipe = TestDataFactory.createDefaultRecipe();

        given(recipeRepository.findByNameAndInstructions(recipeDTO.getName(), recipeDTO.getInstructions()))
                .willReturn(List.of(existingRecipe));
        given(recipeMapper.toDTO(anyList())).willReturn(List.of(recipeDTO));

        assertThrows(DuplicateRecipeException.class, () -> {
            recipeService.uploadRecipe(recipeDTO);
        });
    }

    @Test
    void reportRecipeTest() {
        Report report = TestDataFactory.createDefaultReport();
        ReportDTO expectedReportDTO = TestDataFactory.createDefaultReportDTO();

        given(reportMapper.toEntity(expectedReportDTO)).willReturn(report);
        given(recipeRepository.findById(report.getRecipeId()))
                .willReturn(Optional.of(TestDataFactory.createDefaultRecipe()));
        given(reportRepository.save(report)).willReturn(report);
        given(reportMapper.toDTO(report)).willReturn(expectedReportDTO);

        ReportDTO actualReport = recipeService.reportRecipe(expectedReportDTO);
        assertEquals(expectedReportDTO, actualReport);
    }

    @Test
    void reportMissingRecipeTest() {
        ReportDTO reportDTO = TestDataFactory.createDefaultReportDTO();
        given(recipeRepository.findById(reportDTO.getRecipeId()))
                .willReturn(Optional.empty());

        assertThrows(RecipeNotFoundException.class, () -> {
            recipeService.reportRecipe(reportDTO);
        });
    }
}