package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.exception.DuplicateRecipeException;
import com.nutricheck.backend.exception.RecipeNotFoundException;
import com.nutricheck.backend.layer.model.entity.Recipe;
import com.nutricheck.backend.layer.model.repository.FoodProductRepository;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import com.nutricheck.backend.layer.service.mapper.IngredientMapper;
import com.nutricheck.backend.layer.service.mapper.RecipeMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final FoodProductRepository foodProductRepository;
    private final RecipeMapper recipeMapper;
    private final IngredientMapper ingredientMapper;

    @Override
    public RecipeDTO uploadRecipe(RecipeDTO recipeDTO) throws DuplicateRecipeException {
        return null;
    }

    @Override
    public ReportDTO reportRecipe(ReportDTO reportDTO) {
        return null;
    }

    @Override
    public RecipeDTO downloadRecipe(String recipeId) throws RecipeNotFoundException {
        return null;
    }

    @Transactional
    private Recipe createRecipe(RecipeDTO recipeDTO) {
        // Logic to create a new recipe for complex business logic needed as foodProducts need to be looked up in
        // db and potentially created if they do not exist.
        return null;
    }
}
