package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.MealDTO;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.layer.service.MealService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<FoodProductDTO>> searchFoodProduct(@PathVariable @NotNull String name) {
        return null;
    }

    @GetMapping("/search/recipe/{name}")
    public ResponseEntity<List<RecipeDTO>> searchRecipe(@PathVariable @NotNull String name) {
        return null;
    }

    @PostMapping(value = "/meal/estimate", consumes = "text/plain")
    public ResponseEntity<MealDTO> estimateMeal(@RequestBody String encodedImage) {
        return null;
    }
}
