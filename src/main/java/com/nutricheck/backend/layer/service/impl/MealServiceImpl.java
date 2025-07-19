package com.nutricheck.backend.layer.service.impl;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.MealDTO;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.layer.client.AIModelClient;
import com.nutricheck.backend.layer.client.FoodDBClient;
import com.nutricheck.backend.layer.model.repository.FoodProductRepository;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import com.nutricheck.backend.layer.service.MealService;
import com.nutricheck.backend.layer.service.mapper.FoodProductMapper;
import com.nutricheck.backend.layer.service.mapper.RecipeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final RecipeRepository recipeRepository;
    private final FoodProductRepository foodProductRepository;
    private final RecipeMapper recipeMapper;
    private final FoodProductMapper foodProductMapper;
    @Qualifier("openFoodFacts") private final FoodDBClient openFoodFactsClient;
    @Qualifier("swiss") private final FoodDBClient swissFoodCompositionDatabaseClient;
    private final AIModelClient aiModelClient;

    @Override
    public MealDTO estimateMeal(MultipartFile image) {
        return null;
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
