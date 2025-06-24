package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.RecipeRequestDTO;
import com.nutricheck.backend.dto.RecipeResponseDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.exception.DuplicateRecipeException;

public interface RecipeService {

    RecipeResponseDTO uploadRecipe(RecipeRequestDTO recipeRequestDTO) throws DuplicateRecipeException;

    ReportDTO reportRecipe(ReportDTO reportDTO);

    RecipeResponseDTO rateRecipe(Long recipeId, int rating);

    RecipeResponseDTO downloadRecipe(Long recipeId);
}
