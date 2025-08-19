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

import java.io.IOException;
import java.util.List;

/**
 * Maps meal-related HTTP requests of users to the appropriate service methods.
 * Provides endpoints for searching food products, recipes, and estimating meals.
 */
@RestController
@RequestMapping("/user")
@Validated
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    /**
     * This endpoint allows users to search for food products by name.
     *
     * @param name the name of the food product to search for.
     * @param language the language for the response, either "de" (German) or "en" (English).
     * @return a list of FoodProductDTO objects matching the search criteria.
     */
    @GetMapping("/search/products/{name}")
    public ResponseEntity<List<FoodProductDTO>> searchFoodProduct(@PathVariable @NotNull String name,
                                                                  @RequestParam(required = false, defaultValue = "de")
                                                                  @Pattern(regexp = "^(de|en)$",
                                                                          message = "Only german (de) and english (en) are allowed")
                                                                  String language) {
        List<FoodProductDTO> foodProducts = mealService.searchFoodProduct(name, language);
        return ResponseEntity.ok(foodProducts);
    }

    /**
     * This endpoint allows users to search for recipes by name.
     *
     * @param name the name of the recipe to search for.
     * @return a list of RecipeDTO objects matching the search criteria.
     */
    @GetMapping("/search/recipes/{name}")
    public ResponseEntity<List<RecipeDTO>> searchRecipe(@PathVariable @NotNull String name) {
        List<RecipeDTO> recipes = mealService.searchRecipe(name);
        return ResponseEntity.ok(recipes);
    }

    /**
     * This endpoint allows users to estimate a meal from an image.
     *
     * @param file the image file containing the meal to be estimated.
     * @param language the language for the response, either "de" (German) or "en" (English).
     * @return the estimated MealDTO object.
     */
    @PostMapping(value = "/meal")
    public ResponseEntity<MealDTO> estimateMeal(@RequestParam("file") MultipartFile file,
                                                @RequestParam(required = false, defaultValue = "de")
                                                @Pattern(regexp = "^(de|en)$",
                                                        message = "Only german (de) and english (en) are allowed")
                                                String language) throws IOException {
        // for performance reasons validate image here
        String contentType = file.getContentType();
        if(file.isEmpty() || contentType == null || !contentType.equals(MediaType.IMAGE_JPEG_VALUE))
            return ResponseEntity.badRequest().build();

        MealDTO meal = mealService.estimateMeal(file, language);
        return ResponseEntity.ok(meal);
    }
}
