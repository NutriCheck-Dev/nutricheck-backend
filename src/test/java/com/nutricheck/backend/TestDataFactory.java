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
                        createDefaultIngredientDTO2())
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

    public static IngredientDTO createDefaultIngredientDTO2() {
        return IngredientDTO.builder()
                .recipeId("testRecipeId")
                .foodProductId("testFoodProductId2")
                .foodProduct(createDefaultFoodProductDTO2())
                .quantity(200)
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

    public static FoodProductDTO createDefaultFoodProductDTO2() {
        return FoodProductDTO.builder()
                .id("testFoodProductId2")
                .name("Test Food Product 2")
                .calories(100)
                .carbohydrates(25)
                .protein(5)
                .fat(3)
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

    public static FoodProductDTO createFoodProductDTOOneFromSwissDB() {
        return FoodProductDTO.builder()
                .name("Mashed potatoes, instant, prepared (with water and butter)")
                .carbohydrates(11.5)
                .fat(2.8)
                .calories(80)
                .protein(2)
                .build();
    }

    public static FoodProductDTO createFoodProductDTOTwoFromSwissDB() {
        return FoodProductDTO.builder()
                .name("Mashed potatoes, prepared (with cream and butter)")
                .carbohydrates(12.9)
                .fat(8.4)
                .calories(139)
                .protein(2)
                .build();
    }


    public static FoodProductDTO createFoodProductDTOOneFromOpenFoodFacts() {
        return FoodProductDTO.builder()
                .id("5060042641000")
                .name("Lightly Sea Salted")
                .calories(476)
                .carbohydrates(49)
                .protein(6.2)
                .fat(27)
                .build();
    }

    public static FoodProductDTO createFoodProductDTOTwoFromOpenFoodFacts() {
        return FoodProductDTO.builder()
                .id("5053990101597")
                .name("Sour Cream & Onion")
                .calories(519)
                .carbohydrates(54)
                .protein(6.3)
                .fat(30)
                .build();
    }

    public static Ingredient createDefaultIngredient() {
        return Ingredient.builder()
                .id(IngredientID.builder()
                        .recipeId("testRecipeId")
                        .foodProductId("testFoodProductId")
                        .build())
                .quantity(100)
                .foodProduct(createDefaultFoodProduct())
                .build();
    }

    public static Ingredient createDefaultIngredient2() {
        return Ingredient.builder()
                .id(IngredientID.builder()
                        .recipeId("testRecipeId")
                        .foodProductId("testFoodProductId2")
                        .build())
                .quantity(200)
                .foodProduct(createDefaultFoodProduct2())
                .build();
    }

    private static FoodProduct createDefaultFoodProduct2() {
        return FoodProduct.builder()
                .id("testFoodProductId2")
                .name("Test Food Product 2")
                .calories(100)
                .carbohydrates(25)
                .protein(5)
                .fat(3)
                .build();
    }
}