package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.dto.RecipeRequestDTO;
import com.nutricheck.backend.dto.RecipeResponseDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.layer.service.RecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("nutricheck/user/recipe")
@Validated
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/upload")
    public ResponseEntity<RecipeResponseDTO> uploadRecipe(@RequestBody @Valid RecipeRequestDTO recipeRequestDTO) {

        return null;
    }

    @PostMapping("/report")
    public ResponseEntity<ReportDTO> reportRecipe(@RequestBody @Valid ReportDTO reportDTO) {
        return null;
    }

    @PostMapping("/rate/{recipeId}/{rating}")
    public ResponseEntity<RecipeResponseDTO> rateRecipe(@PathVariable @NotBlank String recipeId, @PathVariable @Positive int rating) {
        return null;
    }

    @GetMapping("/download/{recipeId}")
    public ResponseEntity<RecipeResponseDTO> downloadRecipe(@PathVariable @NotBlank String recipeId) {
        return null;
    }
}
