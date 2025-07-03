package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;

public interface RecipeService {

    RecipeDTO uploadRecipe(RecipeDTO recipeDTO);

    ReportDTO reportRecipe(ReportDTO reportDTO);

    RecipeDTO downloadRecipe(String recipeId);
}
