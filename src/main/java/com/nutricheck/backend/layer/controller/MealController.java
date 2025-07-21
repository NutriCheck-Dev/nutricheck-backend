package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.MealDTO;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.layer.service.MealService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/search/product/{name}")
    public ResponseEntity<List<FoodProductDTO>> searchFoodProduct(@PathVariable @NotNull String name,
                                                                  @RequestParam(required = false, defaultValue = "de")
                                                                  @Pattern(regexp = "^(de|en)$", message = "Only german (de) and english (en) provided") String language) {
        List<FoodProductDTO> foodProducts = mealService.searchFoodProduct(name, language);
        return ResponseEntity.ok(foodProducts);
    }

    @GetMapping("/search/recipe/{name}")
    public ResponseEntity<List<RecipeDTO>> searchRecipe(@PathVariable @NotNull String name) {
        List<RecipeDTO> recipes = mealService.searchRecipe(name);
        return ResponseEntity.ok(recipes);
    }

    @PostMapping(value = "/meal/estimate")
    public ResponseEntity<MealDTO> estimateMeal(@RequestParam("file") MultipartFile file) {
        // for performance reasons validate image here
        if(file.isEmpty() || !file.getContentType().equals(MediaType.IMAGE_PNG_VALUE))
            return ResponseEntity.badRequest().build();

        MealDTO meal = mealService.estimateMeal(file);
        return ResponseEntity.ok(meal);
    }
}
