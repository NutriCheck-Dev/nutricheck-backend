package com.nutricheck.backend.layer.model.repository;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.layer.model.entity.Recipe;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
        assertThat(recipeRepository.findById(recipe.getId()))
                .as("Check if recipe table contains the saved recipe")
                .isPresent();
    }

    @Test
    @Order(2)
    void findRecipeByIdTest() {
        Optional<Recipe> foundRecipe = recipeRepository.findById(recipe.getId());
        assertThat(foundRecipe)
                .as("Check if recipe can be found by id")
                .isPresent();
        // Verify that the found recipe matches the one we saved
        assertEquals(recipe, foundRecipe.get());
    }

    @Test
    @Order(3)
    void findRecipeByNameTest() {
        List<Recipe> recipes = recipeRepository.findByNameContainingIgnoreCase(recipe.getName());
        assertThat(recipes)
                .as("Check if recipe can be found by name")
                .hasSize(1);
        // Verify that the found recipe matches the one we saved
        assertEquals(recipes.getFirst(), recipe);
    }

    @Test
    @Order(4)
    void findRecipeByNameAndInstructionsTest() {
        assertThat(recipeRepository.findByNameAndInstructions(recipe.getName(), recipe.getInstructions()))
                .as("Check if recipe can be found by name and instructions")
                .anyMatch(r -> r.equals(recipe));
    }

    @Test
    @Order(5)
    @Rollback(false)
    void deleteRecipeByIdTest() {
        recipeRepository.deleteById(recipe.getId());
        // food products need to be deleted manually (no cascade delete)
        // to prevent errors in FoodProductRepositoryTest and isolate test classes
        foodProductRepository.deleteAll();
        assertThat(recipeRepository.findById(recipe.getId()))
                .as("Check if recipe table still contains deleted recipe")
                .isEmpty();
    }
}
