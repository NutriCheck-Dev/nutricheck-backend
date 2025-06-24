package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.layer.service.RecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
    public ResponseEntity<RecipeDTO> uploadRecipe(@RequestBody @Valid RecipeDTO recipeDTO) {
        return null;
    }

    @PostMapping("/report")
    public ResponseEntity<ReportDTO> reportRecipe(@RequestBody @Valid ReportDTO reportDTO) {
        return null;
    }


    @GetMapping("/download/{recipeId}")
    public ResponseEntity<RecipeDTO> downloadRecipe(@PathVariable @NotBlank String recipeId) {
        return null;
    }
}
