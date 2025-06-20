package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.layer.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("nutricheck/user/recipe/")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/upload")
    public ResponseEntity<RecipeDTO> uploadRecipe(@RequestBody RecipeDTO recipeDTO) {
        return null;
    }

    @PostMapping("/report")
    public ResponseEntity<ReportDTO> reportRecipe(@RequestBody ReportDTO reportDTO) {
        return null;
    }

    @PostMapping("/rate/{recipeId}/{rating}")
    public ResponseEntity<RecipeDTO> rateRecipe(@PathVariable Long recipeId, @PathVariable int rating) {
        return null;
    }

    @GetMapping("/download/{recipeId}")
    public ResponseEntity<RecipeDTO> downloadRecipe(@PathVariable Long recipeId) {
        return null;
    }
}
