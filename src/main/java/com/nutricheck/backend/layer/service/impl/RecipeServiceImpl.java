package com.nutricheck.backend.layer.service.impl;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.exception.RecipeNotFoundException;
import com.nutricheck.backend.layer.model.entity.Recipe;
import com.nutricheck.backend.layer.model.entity.Report;
import com.nutricheck.backend.layer.model.repository.FoodProductRepository;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import com.nutricheck.backend.layer.model.repository.ReportRepository;
import com.nutricheck.backend.layer.service.RecipeService;
import com.nutricheck.backend.layer.service.mapper.IngredientMapper;
import com.nutricheck.backend.layer.service.mapper.RecipeMapper;
import com.nutricheck.backend.layer.service.mapper.ReportMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final FoodProductRepository foodProductRepository;
    private final ReportRepository reportRepository;
    private final RecipeMapper recipeMapper;
    private final IngredientMapper ingredientMapper;
    private final ReportMapper reportMapper;

    @Override
    @Transactional
    public RecipeDTO uploadRecipe(RecipeDTO recipeDTO) {
        // Contains logic to create a new recipe for complex business logic needed as foodProducts need to be looked up in
        // db and potentially created if they do not exist.
        return null;
    }

    @Override
    public ReportDTO reportRecipe(ReportDTO reportDTO) {
        Report report = reportMapper.toEntity(reportDTO);
        Optional<Recipe> recipeToReport = recipeRepository.findById(report.getRecipeId());
        if (recipeToReport.isEmpty()) {
            throw new RecipeNotFoundException("Recipe with ID " + report.getRecipeId() + " cannot be found");
        }
        Report managedReport = reportRepository.save(report);
        return reportMapper.toDTO(managedReport);
    }

    @Override
    public RecipeDTO downloadRecipe(String recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isEmpty()) {
            throw new RecipeNotFoundException("Recipe with ID " + recipeId + " cannot be found");
        }
        return recipeMapper.toDTO(recipe.get());
    }

}
