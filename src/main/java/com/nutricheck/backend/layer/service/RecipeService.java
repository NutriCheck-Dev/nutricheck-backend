package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.exception.DuplicateRecipeException;
import com.nutricheck.backend.exception.RecipeNotFoundException;

public interface RecipeService {

    RecipeDTO uploadRecipe(RecipeDTO recipeDTO) throws DuplicateRecipeException;

    ReportDTO reportRecipe(ReportDTO reportDTO);

    RecipeDTO downloadRecipe(String recipeId) throws RecipeNotFoundException;
}
