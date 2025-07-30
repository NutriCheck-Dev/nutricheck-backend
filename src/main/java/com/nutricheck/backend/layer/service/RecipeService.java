package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;

/**
 * Service interface for user-related actions on recipes.
 * This interface defines methods for uploading recipes, reporting recipes,
 * and downloading recipes.
 */
public interface RecipeService {

    /**
     * Stores the specified recipe in the database.
     *
     * @param recipeDTO the RecipeDTO object containing the recipe details.
     * @return the uploaded RecipeDTO object.
     */
    RecipeDTO uploadRecipe(RecipeDTO recipeDTO);

    /**
     * Reports a recipe by storing the report in the database.
     *
     * @param reportDTO the ReportDTO object containing the report details.
     * @return the reported ReportDTO object.
     */
    ReportDTO reportRecipe(ReportDTO reportDTO);

}
