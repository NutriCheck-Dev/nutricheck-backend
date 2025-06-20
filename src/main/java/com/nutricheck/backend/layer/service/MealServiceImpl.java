package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.layer.model.repository.FoodProductRepository;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private final RecipeRepository recipeRepository;

    private final FoodProductRepository foodProductRepository;

    public MealServiceImpl(RecipeRepository recipeRepository, FoodProductRepository foodProductRepository) {
        this.recipeRepository = recipeRepository;
        this.foodProductRepository = foodProductRepository;
    }

    @Override
    public List<FoodProductDTO> searchFoodProduct(String name) {
        return null;
    }

    @Override
    public List<RecipeDTO> searchRecipe(String name) {
        return null;
    }
}
