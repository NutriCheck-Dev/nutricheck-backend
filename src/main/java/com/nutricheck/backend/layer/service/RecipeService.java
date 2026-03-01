package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.RecipeDto;
import com.nutricheck.backend.dto.ReportDto;

/**
 * Service interface for user-related actions on recipes.
 * This interface defines methods for uploading recipes, reporting recipes,
 * and downloading recipes.
 */
public interface RecipeService {

    /**
     * Stores the specified recipe in the database.
     *
     * @param recipeDTO the RecipeDto object containing the recipe details.
     * @return the uploaded RecipeDto object.
     */
    RecipeDto uploadRecipe(RecipeDto recipeDTO);

    /**
     * Reports a recipe by storing the report in the database.
     *
     * @param reportDTO the ReportDto object containing the report details.
     * @return the reported ReportDto object.
     */
    ReportDto reportRecipe(ReportDto reportDTO);

}
