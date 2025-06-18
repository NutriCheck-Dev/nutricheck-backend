package com.nutricheck.backend.service;

import com.nutricheck.backend.model.repository.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

}
