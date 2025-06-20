package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

}
