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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final RecipeRepository recipeRepository;
    private final FoodProductRepository foodProductRepository;
    private final RecipeMapper recipeMapper;
    private final FoodProductMapper foodProductMapper;
    private final FoodDBClient foodDBClient;
    private final AIModelClient aiModelClient;

    @Override
    public MealDTO estimateMeal(MultipartFile image) throws IOException {
        byte[] imageBytes = image.getBytes();
        AIMealDTO aiEstimatedMeal = aiModelClient.estimateMeal(imageBytes);
        /*
         * Convert AIMealDTO to MealDTO by setting the whole meal as one ingredient.
         * In this way, we do not need a new dialog for logging an AI estimated meal.
         */
        MealDTO estimatedMeal = MealDTO.builder()
                .calories(aiEstimatedMeal.getCalories())
                .carbohydrates(aiEstimatedMeal.getCarbohydrates())
                .fat(aiEstimatedMeal.getFat())
                .build();
        FoodProductDTO wholeMeal = FoodProductDTO.builder()
                .name(aiEstimatedMeal.getName() + " (AI)")
                .id(UUID.randomUUID().toString())
                .calories(aiEstimatedMeal.getCalories())
                .carbohydrates(aiEstimatedMeal.getCarbohydrates())
                .fat(aiEstimatedMeal.getFat())
                .protein(aiEstimatedMeal.getProtein())
                .build();
        estimatedMeal.setItems(Set.of(new MealItemDTO(wholeMeal.getId(), wholeMeal)));
        return estimatedMeal;

    }

    @Override
    public List<FoodProductDTO> searchFoodProduct(String name) {
        return null;
    }

    @Override
    public List<RecipeDTO> searchRecipe(String name) {
        List<Recipe> recipes = recipeRepository.findByNameContainingIgnoreCase(name);
        return recipeMapper.toDTO(recipes);
    }
}
