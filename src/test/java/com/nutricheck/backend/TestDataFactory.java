package com.nutricheck.backend;

import com.nutricheck.backend.dto.*;
import com.nutricheck.backend.layer.model.entity.Report;

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
                .ingredients(Set.of(IngredientDTO.builder()
                                .recipeId("testRecipeId")
                                .foodProductId("testFoodProductId")
                                .foodProduct(createDefaultFoodProductDTO())
                                .quantity(100)
                                .build(),
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
