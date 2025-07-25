package com.nutricheck.backend.model;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.layer.model.entity.Recipe;
import com.nutricheck.backend.layer.model.repository.FoodProductRepository;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.assertj.core.api.Assertions;

import java.util.List;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RecipeRepositoryTest {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private FoodProductRepository foodProductRepository;

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

    @Test
    @Order(2)
    void findRecipeByIdTest() {
        Assertions.assertThat(recipeRepository.findById(recipe.getId()))
                .as("Check if recipe can be found by id")
                .isPresent();
    }

    @Test
    @Order(3)
    void findRecipeByNameTest() {
        List<Recipe> recipes = recipeRepository.findByNameContainingIgnoreCase(recipe.getName());
        Assertions.assertThat(recipes)
                .as("Check if recipe can be found by name")
                .hasSize(1);
        Assertions.assertThat(recipes.get(0).getId())
                .as("Check that the saved recipe is the one we expect")
                .isEqualTo(recipe.getId());
    }

    @Test
    @Order(4)
    void findRecipeByNameAndInstructionsTest() {
        Assertions.assertThat(recipeRepository.findByNameAndInstructions(recipe.getName(), recipe.getInstructions()))
                .as("Check if recipe can be found by name and instructions")
                .anyMatch(r -> r.getId().equals(recipe.getId()));
    }

    @Test
    @Order(5)
    @Rollback(false)
    void deleteRecipeByIdTest() {
        recipeRepository.deleteById(recipe.getId());
        // clean up food products associated with the recipe
        foodProductRepository.deleteAll();
        Assertions.assertThat(recipeRepository.findById(recipe.getId()))
                .as("Check if recipe table still contains deleted recipe")
                .isEmpty();
    }
}
