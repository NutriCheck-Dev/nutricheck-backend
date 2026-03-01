package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.dto.RecipeDto;
import com.nutricheck.backend.dto.ReportDto;
import com.nutricheck.backend.layer.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Maps recipe-related HTTP requests of users to the appropriate service methods.
 * Provides endpoints for uploading, reporting, and downloading recipes.
 */
@RestController
@RequestMapping("/user/recipes")
@Validated
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    /**
     * This endpoint allows users to upload a recipe.
     *
     * @param recipeDTO the recipe data transfer object containing the recipe details.
     * @return the uploaded RecipeDto object.
     */
    @PostMapping
    public ResponseEntity<RecipeDto> uploadRecipe(@RequestBody @Valid RecipeDto recipeDTO) {
        RecipeDto uploadedRecipe = recipeService.uploadRecipe(recipeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(uploadedRecipe);
    }

    /**
     * This endpoint allows users to report a specified recipe.
     *
     * @param reportDTO the report data transfer object containing the recipe ID.
     * @return the uploaded ReportDto object.
     */
    @PostMapping("/report")
    public ResponseEntity<ReportDto> reportRecipe(@RequestBody @Valid ReportDto reportDTO) {
        ReportDto reportedRecipe = recipeService.reportRecipe(reportDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(reportedRecipe);
    }

}
