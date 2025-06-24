package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.RecipeResponseDTO;
import com.nutricheck.backend.layer.model.repository.FoodProductRepository;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import com.nutricheck.backend.layer.service.mapper.FoodProductMapper;
import com.nutricheck.backend.layer.service.mapper.RecipeResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private final RecipeRepository recipeRepository;
    private final FoodProductRepository foodProductRepository;
    private final RecipeResponseMapper recipeResponseMapper;
    private final FoodProductMapper foodProductMapper;

    public MealServiceImpl(RecipeRepository recipeRepository, FoodProductRepository foodProductRepository,
                           RecipeResponseMapper recipeResponseMapper, FoodProductMapper foodProductMapper) {
        this.recipeRepository = recipeRepository;
        this.foodProductRepository = foodProductRepository;
        this.recipeResponseMapper = recipeResponseMapper;
        this.foodProductMapper = foodProductMapper;
    }

    @Override
    public List<FoodProductDTO> searchFoodProduct(String name) {
        return null;
    }

    @Override
    public List<RecipeResponseDTO> searchRecipe(String name) {
        return null;
    }
}
