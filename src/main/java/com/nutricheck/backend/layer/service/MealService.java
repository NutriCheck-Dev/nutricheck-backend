package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.RecipeDTO;

import java.util.List;

public interface MealService {

    List<FoodProductDTO> searchFoodProduct(String name);

    List<RecipeDTO> searchRecipe(String name);
}
