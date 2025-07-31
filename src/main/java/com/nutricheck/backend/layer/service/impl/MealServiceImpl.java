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
    public MealDTO estimateMeal(MultipartFile image) throws IOException {
        byte[] imageBytes = image.getBytes();
        return aiModelClient.estimateMeal(imageBytes);

    }

    @Override
    public List<FoodProductDTO> searchFoodProduct(String name, String language) {
        Set<FoodProductDTO> foodProducts = new LinkedHashSet<>();

        List<FoodProductDTO> internalProducts = foodProductMapper.toDTO(foodProductRepository.findByNameContainingIgnoreCase(name));
        foodProducts.addAll(internalProducts);

        if( foodProducts.size() < MAX_SEARCH_RESULTS) {
            List<FoodProductDTO> swissFoodProducts = swissFoodCDClient.search(name, language);
            foodProducts.addAll(swissFoodProducts);

            List<FoodProductDTO> openFoodFacts = openFoodFactsClient.search(name, language);

            foodProducts.addAll(openFoodFacts);
        }
        // TODO: Does sorting by name length improve the user experience?
        List<FoodProductDTO> sortedFoodProducts = new ArrayList<>(foodProducts);
        Comparator<FoodProductDTO> nameLengthComparator = (product1, product2) ->
                Integer.compare(product1.getName().length(), product2.getName().length());
        Collections.sort(sortedFoodProducts, nameLengthComparator);
        return sortedFoodProducts;
    }

    @Override
    public List<RecipeDTO> searchRecipe(String name) {
        List<Recipe> recipes = recipeRepository.findByNameContainingIgnoreCase(name);
        return recipeMapper.toDTO(recipes);
    }
}
