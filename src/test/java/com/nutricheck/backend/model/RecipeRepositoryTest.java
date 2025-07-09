package com.nutricheck.backend.model;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.layer.model.entity.Recipe;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.assertj.core.api.Assertions;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RecipeRepositoryTest {
    @Autowired
    private RecipeRepository recipeRepository;

    private Recipe recipe;

    @Test
    @Order(1)
    @Rollback(false)
    void saveRecipeTest() {
        Recipe tempRecipe = TestDataFactory.createDefaultRecipe();
        recipe = recipeRepository.save(tempRecipe);
        Assertions.assertThat(recipeRepository.findById(recipe.getId()))
                .as("Check if recipe table contains the saved recipe")
                .isPresent();
    }
}
