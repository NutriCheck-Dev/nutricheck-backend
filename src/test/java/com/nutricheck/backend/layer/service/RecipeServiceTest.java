package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDto;
import com.nutricheck.backend.dto.IngredientDto;
import com.nutricheck.backend.dto.RecipeDto;
import com.nutricheck.backend.dto.ReportDto;
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
import static org.mockito.Mockito.when;

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
        RecipeDto expectedRecipeDto = TestDataFactory.createDefaultRecipeDTO();
        Recipe recipe = TestDataFactory.createDefaultRecipe();
        Ingredient recipeIngredient = recipe.getIngredients().iterator().next();

        when(recipeRepository.findByNameAndInstructions(expectedRecipeDto.getName(), expectedRecipeDto.getInstructions()))
                .thenReturn(List.of());
        when(recipeMapper.toDTO(anyList())).thenReturn(List.of());
        when(recipeMapper.toEntity(expectedRecipeDto)).thenReturn(recipe);
        when(ingredientMapper.toEntity(any(IngredientDto.class))).thenReturn(recipeIngredient);
        when(foodProductMapper.toEntity(any(FoodProductDto.class))).thenReturn(TestDataFactory.createDefaultFoodProduct());
        when(foodProductRepository.findByNameAndCaloriesAndCarbohydratesAndProteinAndFat(
                any(String.class), any(Double.class), any(Double.class), any(Double.class), any(Double.class)
        )).thenReturn(Optional.empty());
        when(recipeRepository.save(recipe)).thenReturn(recipe);
        when(recipeMapper.toDTO(recipe)).thenReturn(expectedRecipeDto);

        RecipeDto actualRecipeDto = recipeService.uploadRecipe(expectedRecipeDto);
        assertEquals(expectedRecipeDto, actualRecipeDto);
    }

    @Test
    void uploadDuplicateRecipeTest() {
        RecipeDto recipeDTO = TestDataFactory.createDefaultRecipeDTO();
        Recipe existingRecipe = TestDataFactory.createDefaultRecipe();

        when(recipeRepository.findByNameAndInstructions(recipeDTO.getName(), recipeDTO.getInstructions()))
                .thenReturn(List.of(existingRecipe));
        when(recipeMapper.toDTO(anyList())).thenReturn(List.of(recipeDTO));

        assertThrows(DuplicateRecipeException.class, () ->
            recipeService.uploadRecipe(recipeDTO)
        );
    }

    @Test
    void reportRecipeTest() {
        Report report = TestDataFactory.createDefaultReport();
        ReportDto expectedReportDto = TestDataFactory.createDefaultReportDTO();

        when(reportMapper.toEntity(expectedReportDto)).thenReturn(report);
        when(recipeRepository.findById(report.getRecipeId()))
                .thenReturn(Optional.of(TestDataFactory.createDefaultRecipe()));
        when(reportRepository.save(report)).thenReturn(report);
        when(reportMapper.toDTO(report)).thenReturn(expectedReportDto);

        ReportDto actualReport = recipeService.reportRecipe(expectedReportDto);
        assertEquals(expectedReportDto, actualReport);
    }

    @Test
    void reportMissingRecipeTest() {
        ReportDto reportDTO = TestDataFactory.createDefaultReportDTO();

        when(recipeRepository.findById(reportDTO.getRecipeId()))
                .thenReturn(Optional.empty());

        assertThrows(RecipeNotFoundException.class, () ->
            recipeService.reportRecipe(reportDTO)
        );
    }
}