package com.nutricheck.backend.layer.service.impl;

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
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public RecipeDTO uploadRecipe(RecipeDTO recipeDTO) {
        List<Recipe> existingRecipes = recipeRepository.findByNameAndInstructions(recipeDTO.getName(), recipeDTO.getInstructions());
        if(isDuplicateRecipe(recipeDTO, recipeMapper.toDTO(existingRecipes))) {
            throw new DuplicateRecipeException(
                    String.format("Recipe with name '%s', instructions '%s' and the given ingredients already exists.",
                            recipeDTO.getName(), recipeDTO.getInstructions()));
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
        return existingRecipes.stream()
                .anyMatch(existingRecipe -> existingRecipe.equals(newRecipe));
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
