package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.MealDTO;
import com.nutricheck.backend.dto.RecipeDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MealService {

    List<FoodProductDTO> searchFoodProduct(String name, String language);

    List<RecipeDTO> searchRecipe(String name);

    MealDTO estimateMeal(MultipartFile image);
}
