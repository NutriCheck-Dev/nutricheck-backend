package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.RecipeRequestDTO;
import com.nutricheck.backend.dto.RecipeResponseDTO;
import com.nutricheck.backend.dto.ReportDTO;

public interface RecipeService {

    RecipeResponseDTO uploadRecipe(RecipeRequestDTO recipeRequestDTO);

    ReportDTO reportRecipe(ReportDTO reportDTO);

    RecipeResponseDTO rateRecipe(Long recipeId, int rating);

    RecipeResponseDTO downloadRecipe(Long recipeId);
}
