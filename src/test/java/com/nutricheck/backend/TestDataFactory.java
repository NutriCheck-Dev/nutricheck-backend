package com.nutricheck.backend;

import com.nutricheck.backend.dto.*;
import com.nutricheck.backend.dto.external.AiMealDto;
import com.nutricheck.backend.layer.model.entity.*;

import java.util.Set;

public class TestDataFactory {
    public static RecipeDto createDefaultRecipeDTO() {
        RecipeDto dto = new RecipeDto();
        dto.setId("testRecipeId");
        dto.setName("Test Recipe");
        dto.setInstructions("This is a test recipe");
        dto.setServings(1);
        dto.setCalories(200);
        dto.setCarbohydrates(50);
        dto.setProtein(10);
        dto.setFat(5);
        dto.setIngredients(Set.of(
                createDefaultIngredientDTO(),
                createDefaultIngredientDTO2())
        );
        return dto;
    }
    public static IngredientDto createDefaultIngredientDTO() {
        IngredientDto dto = new IngredientDto();
        dto.setRecipeId("testRecipeId");
        dto.setFoodProductId("testFoodProductId");
        dto.setFoodProduct(createDefaultFoodProductDTO());
        dto.setQuantity(100);
        return dto;
    }

    public static IngredientDto createDefaultIngredientDTO2() {
        IngredientDto dto = new IngredientDto();
        dto.setRecipeId("testRecipeId");
        dto.setFoodProductId("testFoodProductId2");
        dto.setFoodProduct(createDefaultFoodProductDTO2());
        dto.setQuantity(200);
        return dto;
    }

    public static ReportDto createDefaultReportDTO() {
        ReportDto dto = new ReportDto();
        dto.setId("testReportId");
        dto.setDescription("This is a test report");
        dto.setRecipeId("testRecipeId");
        dto.setRecipeName("Test Recipe");
        dto.setRecipeInstructions("This is a test recipe instruction");
        return dto;
    }


    public static FoodProductDto createDefaultFoodProductDTO() {
        FoodProductDto dto = new FoodProductDto();
        dto.setId("testFoodProductId");
        dto.setName("Test Food Product");
        dto.setCalories(100);
        dto.setCarbohydrates(25);
        dto.setProtein(5);
        dto.setFat(2);
        return dto;
    }

    public static FoodProductDto createDefaultFoodProductDTO2() {
        FoodProductDto dto = new FoodProductDto();
        dto.setId("testFoodProductId2");
        dto.setName("Test Food Product 2");
        dto.setCalories(100);
        dto.setCarbohydrates(25);
        dto.setProtein(5);
        dto.setFat(3);
        return dto;
    }

    public static MealDto createDefaultMealDTO() {
        MealDto dto = new MealDto();
        dto.setCalories(100);
        dto.setCarbohydrates(25);
        dto.setProtein(5);
        dto.setFat(2);
        MealItemDto item = new MealItemDto();
        item.setFoodProductId("testFoodProductId");
        item.setFoodProduct(createDefaultFoodProductDTO());
        dto.setItems(Set.of(item));
        return dto;
    }
    public static AiMealDto createDefaultAIMealDTO() {
        AiMealDto dto = new AiMealDto();
        dto.setName("Test AI Meal");
        dto.setCalories(100);
        dto.setCarbohydrates(25);
        dto.setProtein(5);
        dto.setFat(2);
        return dto;
    }

    public static Report createDefaultReport() {
        Report r = new Report();
        r.setDescription("This is a test report");
        r.setRecipeId("testRecipeId");
        return r;
    }

    public static FoodProduct createDefaultFoodProduct() {
        FoodProduct p = new FoodProduct();
        p.setId("testFoodProductId");
        p.setName("Test Food Product");
        p.setCalories(100);
        p.setCarbohydrates(25);
        p.setProtein(5);
        p.setFat(2);
        return p;
    }
    public static FoodProduct createDefaultFoodProduct2() {
        FoodProduct p = new FoodProduct();
        p.setId("testFoodProductId2");
        p.setName("Test Food Product 2");
        p.setCalories(100);
        p.setCarbohydrates(25);
        p.setProtein(5);
        p.setFat(3);
        return p;
    }
    public static Ingredient createDefaultIngredient() {
        Ingredient ing = new Ingredient();
        IngredientId id = new IngredientId();
        id.setRecipeId("testRecipeId");
        id.setFoodProductId("testFoodProductId");
        ing.setId(id);
        ing.setQuantity(100);
        ing.setFoodProduct(createDefaultFoodProduct());
        return ing;
    }

    public static Ingredient createDefaultIngredient2() {
        Ingredient ing = new Ingredient();
        IngredientId id = new IngredientId();
        id.setRecipeId("testRecipeId");
        id.setFoodProductId("testFoodProductId2");
        ing.setId(id);
        ing.setQuantity(200);
        ing.setFoodProduct(createDefaultFoodProduct2());
        return ing;
    }

    public static Recipe createDefaultRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId("testRecipeId");
        recipe.setName("Test Recipe");
        recipe.setInstructions("This is a test recipe");
        recipe.setIngredients(Set.of(
                createDefaultIngredient(),
                createDefaultIngredient2())
        );
        recipe.setServings(1);
        recipe.setCalories(200);
        recipe.setCarbohydrates(50);
        recipe.setProtein(10);
        recipe.setFat(5);
        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredient.setRecipe(recipe);
        }
        return recipe;
    }

    public static FoodProductDto createFoodProductDTOOneFromSwissDB() {
        FoodProductDto dto = new FoodProductDto();
        dto.setName("Mashed potatoes, instant, prepared (with water and butter)");
        dto.setCarbohydrates(11.5);
        dto.setFat(2.8);
        dto.setCalories(80);
        dto.setProtein(2);
        return dto;
    }

    public static FoodProductDto createFoodProductDTOTwoFromSwissDB() {
        FoodProductDto dto = new FoodProductDto();
        dto.setName("Mashed potatoes, prepared (with cream and butter)");
        dto.setCarbohydrates(12.9);
        dto.setFat(8.4);
        dto.setCalories(139);
        dto.setProtein(2);
        return dto;
    }


    public static FoodProductDto createFoodProductDTOOneFromOpenFoodFacts() {
        FoodProductDto dto = new FoodProductDto();
        dto.setId("5060042641000");
        dto.setName("Lightly Sea Salted");
        dto.setCalories(476);
        dto.setCarbohydrates(49);
        dto.setProtein(6.2);
        dto.setFat(27);
        return dto;
    }

    public static FoodProductDto createFoodProductDTOTwoFromOpenFoodFacts() {
        FoodProductDto dto = new FoodProductDto();
        dto.setId("5053990101597");
        dto.setName("Sour Cream & Onion");
        dto.setCalories(519);
        dto.setCarbohydrates(54);
        dto.setProtein(6.3);
        dto.setFat(30);
        return dto;
    }

    public static MealDto createMealDTOFromGemini() {
        MealDto dto = new MealDto();
        dto.setCalories(780);
        dto.setCarbohydrates(91);
        dto.setProtein(53);
        dto.setFat(23);
        MealItemDto item = new MealItemDto();
        item.setFoodProductId("testFoodProductId");
        FoodProductDto fp = new FoodProductDto();
        fp.setId("testFoodProductId");
        fp.setName("Spaghetti Bolognese");
        fp.setCalories(780);
        fp.setCarbohydrates(91);
        fp.setProtein(53);
        fp.setFat(23);
        item.setFoodProduct(fp);
        dto.setItems(Set.of(item));
        return dto;
    }

}
