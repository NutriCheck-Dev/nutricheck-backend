package com.nutricheck.backend;

import com.nutricheck.backend.dto.*;
import com.nutricheck.backend.layer.model.entity.*;

import java.util.Set;

public class TestDataFactory {
    public static RecipeDTO createDefaultRecipeDTO() {
        return RecipeDTO.builder()
                .id("testRecipeId")
                .name("Test Recipe")
                .instructions("This is a test recipe")
                .servings(1)
                .calories(200)
                .carbohydrates(50)
                .protein(10)
                .fat(5)
                .ingredients(Set.of(
                        createDefaultIngredientDTO(),
                        IngredientDTO.builder()
                                .recipeId("testRecipeId")
                                .foodProductId("testFoodProductId2")
                                .foodProduct(FoodProductDTO.builder()
                                        .id("testFoodProductId2")
                                        .name("Test Food Product 2")
                                        .calories(100)
                                        .carbohydrates(25)
                                        .protein(5)
                                        .fat(3)
                                        .build())
                                .quantity(200)
                                .build())
                )
                .build();
    }
    public static IngredientDTO createDefaultIngredientDTO() {
        return IngredientDTO.builder()
                .recipeId("testRecipeId")
                .foodProductId("testFoodProductId")
                .foodProduct(createDefaultFoodProductDTO())
                .quantity(100)
                .build();
    }
    public static ReportDTO createDefaultReportDTO() {
        return ReportDTO.builder()
                .id("testReportId")
                .description("This is a test report")
                .recipeId("testRecipeId")
                .recipeName("Test Recipe")
                .recipeInstructions("This is a test recipe instruction")
                .build();
    }


    public static FoodProductDTO createDefaultFoodProductDTO() {
        return FoodProductDTO.builder()
                .id("testFoodProductId")
                .name("Test Food Product")
                .calories(100)
                .carbohydrates(25)
                .protein(5)
                .fat(2)
                .build();
    }


    public static MealDTO createDefaultMealDTO() {
        return MealDTO.builder()
                .calories(500)
                .carbohydrates(100)
                .protein(30)
                .fat(20)
                .items(Set.of(MealItemDTO.builder()
                                .foodProductId("testFoodProductId")
                                .foodProduct(createDefaultFoodProductDTO())
                                .build()
                        )
                )
                .build();
    }
    public static Report createDefaultReport() {
        return Report.builder()
                .description("This is a test report")
                .recipeId("testRecipeId")
                .build();
    }

    public static FoodProduct createDefaultFoodProduct() {
        return FoodProduct.builder()
                .id("testFoodProductId")
                .name("Test Food Product")
                .calories(100)
                .carbohydrates(25)
                .protein(5)
                .fat(2)
                .build();
    }

    public static Recipe createDefaultRecipe() {
        Recipe recipe = Recipe.builder()
                .id("testRecipeId")
                .name("Test Recipe")
                .instructions("This is a test recipe")
                .servings(1)
                .calories(200)
                .carbohydrates(50)
                .protein(10)
                .fat(5)
                .build();
        FoodProduct foodProduct = createDefaultFoodProduct();
        IngredientID ingredientID = IngredientID.builder()
                .recipeId(recipe.getId())
                .foodProductId(foodProduct.getId())
                .build();
        Ingredient ingredient = Ingredient.builder()
                .id(ingredientID)
                .recipe(recipe)
                .foodProduct(foodProduct)
                .quantity(100)
                .build();
        recipe.setIngredients(Set.of(ingredient));
        return recipe;
    }
    public static String createDefaultEncodedImage() {
        return "iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAIAAADTED8xAAADMElEQVR4nOzVwQnAIBQFQYXff81RUkQCOyDj1YOPnbXWPmeTRef+/3O/OyB" +
                "jzh3CD95BfqICMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CM" +
                "K0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0C" +
                "MK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0C" +
                "MK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0C" +
                "MK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CM" +
                "K0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0C" +
                "MK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0" +
                "CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CM" +
                "K0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CM" +
                "K0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMO0TAAD//2Anhf4QtqobAAAAAElFTkSuQmCC";
    }

}
