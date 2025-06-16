package com.backend.nutricheck.service;

import com.backend.nutricheck.interfaces.IRecipeService;
import com.backend.nutricheck.model.repository.RecipeRepository;

public class RecipeService implements IRecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

}
