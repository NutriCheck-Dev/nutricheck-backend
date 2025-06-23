package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.dto.RecipeRequestDTO;
import com.nutricheck.backend.dto.RecipeResponseDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.layer.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("nutricheck/user/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    // FIXME: Why return anything?
    @PostMapping("/upload")
    public ResponseEntity<RecipeResponseDTO> uploadRecipe(@RequestBody RecipeRequestDTO recipeRequestDTO) {

        return null;
    }

    // FIXME: why return anything?
    @PostMapping("/report")
    public ResponseEntity<ReportDTO> reportRecipe(@RequestBody ReportDTO reportDTO) {
        return null;
    }

    // FIXME: why return anything?
    @PostMapping("/rate/{recipeId}/{rating}")
    public ResponseEntity<RecipeResponseDTO> rateRecipe(@PathVariable String recipeId, @PathVariable int rating) {
        return null;
    }

    @GetMapping("/download/{recipeId}")
    public ResponseEntity<RecipeResponseDTO> downloadRecipe(@PathVariable String recipeId) {
        return null;
    }
}
