package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.RecipeResponseDTO;
import com.nutricheck.backend.layer.service.MealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("nutricheck/user/search")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<List<FoodProductDTO>> searchFoodProduct(@PathVariable String name) {
        return null;
    }

    @GetMapping("/recipe/{name}")
    public ResponseEntity<List<RecipeResponseDTO>> searchRecipe(@PathVariable String name) {
        return null;
    }
}
