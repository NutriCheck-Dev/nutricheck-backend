package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public RecipeDTO uploadRecipe(RecipeDTO recipeDTO) {
        return null;
    }

    @Override
    public ReportDTO reportRecipe(ReportDTO reportDTO) {
        return null;
    }

    @Override
    public RecipeDTO rateRecipe(Long recipeId, int rating) {
        return null;
    }

    @Override
    public RecipeDTO downloadRecipe(Long recipeId) {
        return null;
    }
}
