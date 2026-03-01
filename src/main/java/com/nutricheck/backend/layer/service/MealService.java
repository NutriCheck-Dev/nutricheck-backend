package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.FoodProductDto;
import com.nutricheck.backend.dto.MealDto;
import com.nutricheck.backend.dto.RecipeDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Service interface for meal-related operations.
 * This interface defines methods for searching food products and recipes,
 * as well as estimating a meal based on an image.
 */
public interface MealService {
  
    /**
     * Searches for food products by name, first in the internal database
     * and then using the external food database.
     *
     * @param name the name of the food product to search for.
     * @param language the language in which to perform the search.
     * @return a list of FoodProductDto objects matching the search criteria.
     */
    List<FoodProductDto> searchFoodProduct(String name, String language);

    /**
     * Searches for recipes by name.
     *
     * @param name the name of the recipe to search for.
     * @return a list of RecipeDto objects matching the search criteria.
     */
    List<RecipeDto> searchRecipe(String name);

    /**
     * Estimates a meal based on an image using an external AI model.
     *
     * @param image the image file of the meal to be estimated.
     * @param language the language in which to perform the estimation.
     * @return a MealDto object containing the estimated meal information.
     */
    MealDto estimateMeal(MultipartFile image, String language) throws IOException;
}
