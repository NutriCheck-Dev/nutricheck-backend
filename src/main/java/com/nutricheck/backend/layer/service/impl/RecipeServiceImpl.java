package com.nutricheck.backend.layer.service.impl;

import com.nutricheck.backend.Utils;
import com.nutricheck.backend.dto.IngredientDTO;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.exception.DuplicateRecipeException;
import com.nutricheck.backend.exception.RecipeNotFoundException;
import com.nutricheck.backend.layer.model.entity.FoodProduct;
import com.nutricheck.backend.layer.model.entity.Ingredient;
import com.nutricheck.backend.layer.model.entity.Recipe;
import com.nutricheck.backend.layer.model.entity.Report;
import com.nutricheck.backend.layer.model.repository.FoodProductRepository;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import com.nutricheck.backend.layer.model.repository.ReportRepository;
import com.nutricheck.backend.layer.service.RecipeService;
import com.nutricheck.backend.layer.service.mapper.FoodProductMapper;
import com.nutricheck.backend.layer.service.mapper.IngredientMapper;
import com.nutricheck.backend.layer.service.mapper.RecipeMapper;
import com.nutricheck.backend.layer.service.mapper.ReportMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final FoodProductRepository foodProductRepository;
    private final ReportRepository reportRepository;
    private final RecipeMapper recipeMapper;
    private final IngredientMapper ingredientMapper;
    private final ReportMapper reportMapper;
    private final FoodProductMapper foodProductMapper;

    @Override
    @Transactional
    @CacheEvict(value = "recipes", key = "#recipeDTO.name.toLowerCase()")
    public RecipeDTO uploadRecipe(RecipeDTO recipeDTO) {
        List<Recipe> existingRecipes = recipeRepository.findByNameAndInstructions(recipeDTO.getName(), recipeDTO.getInstructions());
        if(isDuplicateRecipe(recipeDTO, recipeMapper.toDTO(existingRecipes))) {
            throw new DuplicateRecipeException(
                    String.format("Recipe with name '%s', instructions '%s' and the given ingredients already exists.",
                            recipeDTO.getName(), recipeDTO.getInstructions()));
        }
        // The recipe can be a modified existing recipe and needs a new id to save it (currently not possible to modify
        // existing recipes as no ownership is modelled, but this might change in the future)
        if(recipeRepository.findById(recipeDTO.getId()).isPresent()) {
            UUID newId = UUID.randomUUID();
            recipeDTO.setId(newId.toString());
            for(IngredientDTO ingredientDTO : recipeDTO.getIngredients()) {
                ingredientDTO.setRecipeId(newId.toString());
            }
        }

        Recipe recipe = recipeMapper.toEntity(recipeDTO);
        Set<Ingredient> ingredients = new HashSet<>();
        for(IngredientDTO ingredientDTO : recipeDTO.getIngredients()) {
            Ingredient ingredient = ingredientMapper.toEntity(ingredientDTO);
            ingredient.setRecipe(recipe);
            // either use the existing food product or create a new one if it is missing
            FoodProduct newFoodProduct = foodProductMapper.toEntity(ingredientDTO.getFoodProduct());
            FoodProduct foodProduct = findExistingFoodProduct(newFoodProduct)
                    .orElse(newFoodProduct);
            ingredient.setFoodProduct(foodProduct);
            ingredients.add(ingredient);
        }
        recipe.setIngredients(ingredients);
        Recipe managedRecipe = recipeRepository.save(recipe); // cascading causes ingredients and (new) food products to be saved
        return recipeMapper.toDTO(managedRecipe);
    }

    private boolean isDuplicateRecipe(RecipeDTO newRecipe, List<RecipeDTO> existingRecipes) {
        // Instead of equals for DTOs use comparator to check for duplicate to keep equals pure
        Comparator<IngredientDTO> ingredientComparator = Comparator
                .comparing((IngredientDTO i) -> i.getFoodProduct().getName())
                .thenComparing(IngredientDTO::getQuantity, Utils::compareDouble);
        List<IngredientDTO> sortedNewIngredients = newRecipe.getIngredients().stream()
                .sorted(ingredientComparator)
                .toList();
        return existingRecipes.stream().anyMatch(existingRecipe ->
                existingRecipe.getName().equals(newRecipe.getName()) &&
                        existingRecipe.getInstructions().equals(newRecipe.getInstructions()) &&
                        existingRecipe.getServings() == newRecipe.getServings() &&
                        areIngredientsEqual(existingRecipe.getIngredients(), sortedNewIngredients, ingredientComparator)
        );
    }

    private boolean areIngredientsEqual(Set<IngredientDTO> existingIngredients,
                                        List<IngredientDTO> sortedNewIngredients,
                                        Comparator<IngredientDTO> comparator) {

        if (existingIngredients.size() != sortedNewIngredients.size()) {
            return false;
        }
        List<IngredientDTO> sortedExisting = existingIngredients.stream()
                .sorted(comparator)
                .toList();
        for (int i = 0; i < sortedExisting.size(); i++) {
            if (comparator.compare(sortedExisting.get(i), sortedNewIngredients.get(i)) != 0) {
                return false;
            }
        }
        return true;
    }

    private Optional<FoodProduct> findExistingFoodProduct(FoodProduct newFoodProduct) {
        return foodProductRepository.findByNameAndCaloriesAndCarbohydratesAndProteinAndFat(
                newFoodProduct.getName(),
                newFoodProduct.getCalories(),
                newFoodProduct.getCarbohydrates(),
                newFoodProduct.getProtein(),
                newFoodProduct.getFat()
        );
    }

    @Override
    public ReportDTO reportRecipe(ReportDTO reportDTO) {
        Optional<Recipe> recipeToReport = recipeRepository.findById(reportDTO.getRecipeId());
        if (recipeToReport.isEmpty()) {
            throw new RecipeNotFoundException(String.format(AdminServiceImpl.NOT_FOUND_MESSAGE,
                    "Recipe", reportDTO.getRecipeId()));
        }
        Report report = reportMapper.toEntity(reportDTO);
        Report managedReport = reportRepository.save(report);
        return reportMapper.toDTO(managedReport);
    }

}
