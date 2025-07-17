package com.nutricheck.backend;

import com.nutricheck.backend.dto.*;

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

    public static String createDefaultSwissFoodCDResponse() {
        return "[ \n" +
                "{\n" +
                "    \"id\": 349447,\n" +
                "    \"foodName\": \"Mashed potatoes, instant, prepared (with water and butter)\",\n" +
                "    \"generic\": true,\n" +
                "    \"categoryNames\": \"Potatoes and other starchy tubers\",\n" +
                "    \"amount\": 0,\n" +
                "    \"foodid\": 866,\n" +
                "    \"valueTypeCode\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 350440,\n" +
                "    \"foodName\": \"Mashed potatoes, prepared (with cream and butter)\",\n" +
                "    \"generic\": true,\n" +
                "    \"categoryNames\": \"Potatoes and other starchy tubers\",\n" +
                "    \"amount\": 0,\n" +
                "    \"foodid\": 1519,\n" +
                "    \"valueTypeCode\": \"\"\n" +
                "  },\n" +
                "]";
    }

    public static String createDefaultSwissFoodCDFoodProductOne() {
        return "{\n" +
                "  \"name\": \"Mashed potatoes, instant, prepared (with water and butter)\",\n" +
                "  \"id\": 349447,\n" +
                "  \"values\": [\n" +
                "    {\n"+
                "       \"id\": 6256919,\n" +
                "       \"value\": 2, \n" +
                "       \"component\": {\n" +
                "         \"name\": \"Protein\",\n" +
                "         }\n" +
                "    },\n" +
                "    {\n" +
                "       \"id\": 6256920,\n" +
                "       \"value\": 2.8, \n" +
                "       \"component\": {\n" +
                "         \"name\": \"Fat, total\"\n" +
                "         }\n" +
                "    },\n" +
                "    {\n" +
                "       \"id\": 6256906,\n" +
                "       \"value\": 11.5, \n" +
                "       \"component\": {\n" +
                "         \"name\": \"Carbohydrates, available\"\n" +
                "         }\n" +
                "    },\n" +
                "    {\n" +
                "       \"id\": 6256888,\n" +
                "       \"value\": 80, \n" +
                "       \"component\": {\n" +
                "         \"name\": \"Energy, kilocalories\"\n" +
                "         }\n" +
                "    }\n" +
                "  ],\n" +
                "}";

    }

    public static String createDefaultSwissFoodCDFoodProductTwo() {
        return "{\n" +
                "  \"name\": \"Mashed potatoes, prepared (with cream and butter)\",\n" +
                "  \"id\": 350440,\n" +
                "  \"values\": [\n" +
                "    {\n"+
                "       \"id\": 6301201,\n" +
                "       \"value\": 2, \n" +
                "       \"component\": {\n" +
                "         \"name\": \"Protein\",\n" +
                "         }\n" +
                "    },\n" +
                "    {\n" +
                "       \"id\": 6301176,\n" +
                "       \"value\": 8.4, \n" +
                "       \"component\": {\n" +
                "         \"name\": \"Fat, total\"\n" +
                "         }\n" +
                "    },\n" +
                "    {\n" +
                "       \"id\": 6301175,\n" +
                "       \"value\": 12.9, \n" +
                "       \"component\": {\n" +
                "         \"name\": \"Carbohydrates, available\"\n" +
                "         }\n" +
                "    },\n" +
                "    {\n" +
                "       \"id\": 6301188,\n" +
                "       \"value\": 139, \n" +
                "       \"component\": {\n" +
                "         \"name\": \"Energy, kilocalories\"\n" +
                "         }\n" +
                "    }\n" +
                "  ],\n" +
                "}";

    }

    public static int createDefaultSwissFoodCDFoodProductOneId() {
        return 349447;
    }

    public static int createDefaultSwissFoodCDFoodProductTwoId() {
        return 350440;
    }
}
