package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.layer.service.RecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
     * @return the uploaded RecipeDTO object.
     */
    @PostMapping
    public ResponseEntity<RecipeDTO> uploadRecipe(@RequestBody @Valid RecipeDTO recipeDTO) {
        RecipeDTO uploadedRecipe = recipeService.uploadRecipe(recipeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(uploadedRecipe);
    }

    /**
     * This endpoint allows users to report a specified recipe.
     *
     * @param reportDTO the report data transfer object containing the recipe ID.
     * @return the uploaded ReportDTO object.
     */
    @PostMapping("/report")
    public ResponseEntity<ReportDTO> reportRecipe(@RequestBody @Valid ReportDTO reportDTO) {
        ReportDTO reportedRecipe = recipeService.reportRecipe(reportDTO);
        return ResponseEntity.ok(reportedRecipe);
    }


    /**
     * This endpoint allows users to download a specified recipe.
     *
     * @param recipeId the ID of the recipe to be downloaded.
     * @return the RecipeDTO object.
     */
    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> downloadRecipe(@PathVariable @NotBlank String recipeId) {
        RecipeDTO recipe = recipeService.downloadRecipe(recipeId);
        return ResponseEntity.ok(recipe);
    }
}
