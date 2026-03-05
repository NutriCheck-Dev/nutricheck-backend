package com.nutricheck.backend.layer.service.impl;

import com.nutricheck.backend.dto.*;
import com.nutricheck.backend.layer.client.AIModelClient;
import com.nutricheck.backend.layer.client.FoodDBClient;
import com.nutricheck.backend.layer.model.entity.Recipe;
import com.nutricheck.backend.layer.model.repository.FoodProductRepository;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import com.nutricheck.backend.layer.service.MealService;
import com.nutricheck.backend.layer.service.mapper.FoodProductMapper;
import com.nutricheck.backend.layer.service.mapper.RecipeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    public static final int MAX_SEARCH_RESULTS = 100;

    private final RecipeRepository recipeRepository;
    private final FoodProductRepository foodProductRepository;
    private final RecipeMapper recipeMapper;
    private final FoodProductMapper foodProductMapper;
    @Qualifier("openFoodFacts") private final FoodDBClient openFoodFactsClient;
    @Qualifier("swiss") private final FoodDBClient swissFoodCDClient;
    private final AIModelClient aiModelClient;

    @Override
    public MealDto estimateMeal(MultipartFile image, String language) throws IOException {
        byte[] imageBytes = image.getBytes();
        return aiModelClient.estimateMeal(imageBytes, language);

    }

    @Override
    @Cacheable(value = "foodProducts", key = "#name.toLowerCase()")
    public List<FoodProductDto> searchFoodProduct(String name, String language) {
        Set<FoodProductDto> foodProducts = new LinkedHashSet<>();

        List<FoodProductDto> internalProducts = foodProductMapper.toDTO(foodProductRepository.findByNameContainingIgnoreCase(name));
        foodProducts.addAll(internalProducts);

        if( foodProducts.size() < MAX_SEARCH_RESULTS) {
            List<FoodProductDto> swissFoodProducts = swissFoodCDClient.search(name, language);
            foodProducts.addAll(sortFoodProductsByNameLength(swissFoodProducts));

            List<FoodProductDto> openFoodFacts = openFoodFactsClient.search(name, language);
            foodProducts.addAll(sortFoodProductsByNameLength(openFoodFacts));
        }
        return new ArrayList<>(foodProducts);
    }

    private List<FoodProductDto> sortFoodProductsByNameLength(List<FoodProductDto> foodProducts) {
        return foodProducts.stream()
                .sorted(Comparator.comparingInt(product -> product.getName().length()))
                .toList();
    }

    @Override
    @Cacheable(value = "recipes", key = "#name.toLowerCase()")
    public List<RecipeDto> searchRecipe(String name) {
        List<Recipe> recipes = recipeRepository.findByNameContainingIgnoreCase(name);
        return recipeMapper.toDTO(recipes);
    }
}
