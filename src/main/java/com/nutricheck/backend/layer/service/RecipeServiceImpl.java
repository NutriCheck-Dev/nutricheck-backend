package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.RecipeRequestDTO;
import com.nutricheck.backend.dto.RecipeResponseDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.layer.model.entity.Recipe;
import com.nutricheck.backend.layer.model.repository.FoodProductRepository;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import com.nutricheck.backend.layer.service.mapper.IngredientMapper;
import com.nutricheck.backend.layer.service.mapper.RecipeRequestMapper;
import com.nutricheck.backend.layer.service.mapper.RecipeResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final FoodProductRepository foodProductRepository;
    private final RecipeRequestMapper recipeRequestMapper;
    private final IngredientMapper ingredientMapper;
    private final RecipeResponseMapper recipeResponseMapper;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             FoodProductRepository foodProductRepository,
                             RecipeRequestMapper recipeRequestMapper,
                             IngredientMapper ingredientMapper,
                             RecipeResponseMapper recipeResponseMapper) {
        this.recipeRepository = recipeRepository;
        this.foodProductRepository = foodProductRepository;
        this.recipeRequestMapper = recipeRequestMapper;
        this.ingredientMapper = ingredientMapper;
        this.recipeResponseMapper = recipeResponseMapper;
    }

    @Override
    public RecipeResponseDTO uploadRecipe(RecipeRequestDTO recipeRequestDTO) {
        return null;
    }

    @Override
    public ReportDTO reportRecipe(ReportDTO reportDTO) {
        return null;
    }

    @Override
    public RecipeResponseDTO rateRecipe(Long recipeId, int rating) {
        return null;
    }

    @Override
    public RecipeResponseDTO downloadRecipe(Long recipeId) {
        return null;
    }

    private Recipe createRecipe(RecipeRequestDTO recipeRequestDTO) {
        // Logic to create a new recipe for complex business logic needed as foodProducts need to be looked up in
        // db and potentially created if they do not exist.
        return null;
    }
}
