package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.MealDTO;
import com.nutricheck.backend.dto.RecipeDTO;
import org.springframework.web.multipart.MultipartFile;

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
     * @return a list of FoodProductDTO objects matching the search criteria.
     */
    List<FoodProductDTO> searchFoodProduct(String name);

    /**
     * Searches for recipes by name.
     *
     * @param name the name of the recipe to search for.
     * @return a list of RecipeDTO objects matching the search criteria.
     */
    List<RecipeDTO> searchRecipe(String name);

    /**
     * Estimates a meal based on an image using an external AI model.
     *
     * @param image the image file of the meal to be estimated.
     * @return a MealDTO object containing the estimated meal information.
     */
    MealDTO estimateMeal(MultipartFile image);
}
