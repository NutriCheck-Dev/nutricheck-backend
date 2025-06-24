package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.layer.model.repository.FoodProductRepository;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import com.nutricheck.backend.layer.service.mapper.FoodProductMapper;
import com.nutricheck.backend.layer.service.mapper.RecipeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private final RecipeRepository recipeRepository;
    private final FoodProductRepository foodProductRepository;
    private final RecipeMapper recipeMapper;
    private final FoodProductMapper foodProductMapper;

    public MealServiceImpl(RecipeRepository recipeRepository, FoodProductRepository foodProductRepository,
                           RecipeMapper recipeMapper, FoodProductMapper foodProductMapper) {
        this.recipeRepository = recipeRepository;
        this.foodProductRepository = foodProductRepository;
        this.recipeMapper = recipeMapper;
        this.foodProductMapper = foodProductMapper;
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
