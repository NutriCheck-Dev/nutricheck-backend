package com.nutricheck.backend;

import com.nutricheck.backend.dto.*;

import java.util.List;
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

    public static String createRawSwissFoodCDResponse() {
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
                "  }\n" +
                "]";
    }

    public static String createRawSwissFoodCDFoodProductOne() {
        return "{\n" +
                "  \"name\": \"Mashed potatoes, instant, prepared (with water and butter)\",\n" +
                "  \"id\": 349447,\n" +
                "  \"values\": [\n" +
                "    {\n"+
                "       \"id\": 6256919,\n" +
                "       \"value\": 2, \n" +
                "       \"component\": {\n" +
                "         \"name\": \"Protein\"\n" +
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
                "  ]\n" +
                "}";

    }

    public static String createRawSwissFoodCDFoodProductTwo() {
        return "{\n" +
                "  \"name\": \"Mashed potatoes, prepared (with cream and butter)\",\n" +
                "  \"id\": 350440,\n" +
                "  \"values\": [\n" +
                "    {\n"+
                "       \"id\": 6301201,\n" +
                "       \"value\": 2, \n" +
                "       \"component\": {\n" +
                "         \"name\": \"Protein\"\n" +
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
                "  ]\n" +
                "}";

    }

    public static FoodProductDTO createExternalFoodProductDTOOne() {
        return FoodProductDTO.builder()
                .id("349447")
                .name("Mashed potatoes, instant, prepared (with water and butter)")
                .carbohydrates(11.5)
                .fat(2.8)
                .calories(80)
                .protein(2)
                .build();
    }

    public static FoodProductDTO createExternalFoodProductDTOTwo() {
        return FoodProductDTO.builder()
                .id("350440")
                .name("Mashed potatoes, prepared (with cream and butter)")
                .carbohydrates(12.9)
                .fat(8.4)
                .calories(139)
                .protein(2)
                .build();
    }

    public static String createRawOpenFoodFactsResponse() {
        return "{\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"_id\": \"5060042641000\",\n" +
                "      \"product_name\": \"Lightly Sea Salted\",\n" +
                "      \"nutriments\": {\n" +
                "        \"energy-kcal\": 476,\n" +
                "        \"fat\": 27,\n" +
                "        \"carbohydrates\": 49,\n" +
                "        \"proteins\": 6.2\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"5053990101597\",\n" +
                "      \"product_name\": \"Sour Cream & Onion\",\n" +
                "      \"nutriments\": {\n" +
                "        \"energy-kcal\": 519,\n" +
                "        \"fat\": 30,\n" +
                "        \"carbohydrates\": 54,\n" +
                "        \"proteins\": 6.3\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static FoodProductDTO createOpenFoodFactsFoodProductOne() {
        return FoodProductDTO.builder()
                .id("5060042641000")
                .name("Lightly Sea Salted")
                .calories(476)
                .carbohydrates(49)
                .protein(6.2)
                .fat(27)
                .build();
    }

    public static FoodProductDTO createOpenFoodFactsFoodProductTwo() {
        return FoodProductDTO.builder()
                .id("5053990101597")
                .name("Sour Cream & Onion")
                .calories(519)
                .carbohydrates(54)
                .protein(6.3)
                .fat(30)
                .build();
    }

    public static List<OpenFoodFactsFoodProductDTO> createOpenFoodFactsFoodProducts() {
        return List.of(
                OpenFoodFactsFoodProductDTO.builder()
                        .id("5060042641000")
                        .name("Lightly Sea Salted")
                        .nutriments(OpenFoodFactsNutrimentsDTO.builder()
                                .energyKcal(476)
                                .fat(27)
                                .carbohydrates(49)
                                .proteins(6.2)
                                .build())
                        .build(),
                OpenFoodFactsFoodProductDTO.builder()
                        .id("5053990101597")
                        .name("Sour Cream & Onion")
                        .nutriments(OpenFoodFactsNutrimentsDTO.builder()
                                .energyKcal(519)
                                .fat(30)
                                .carbohydrates(54)
                                .proteins(6.3)
                                .build())
                        .build()
        );
    }

    public static List<FoodProductDTO> createFoodProductsFromOpenFoodFacts() {
        return List.of(
                createOpenFoodFactsFoodProductOne(),
                createOpenFoodFactsFoodProductTwo()
        );
    }

    public static List<SwissFoodCDFoodProductDTO> createSwissFoodCDFoodProducts() {
        return List.of(
                SwissFoodCDFoodProductDTO.builder()
                        .id("349447")
                        .name("Mashed potatoes, instant, prepared (with water and butter)")
                        .values(List.of(
                                SwissFoodCDValueDTO.builder()
                                        .id(6256919)
                                        .value(2.0)
                                        .component(SwissFoodCDComponentDTO.builder().name("Protein").build())
                                        .build(),
                                SwissFoodCDValueDTO.builder()
                                        .id(6256920)
                                        .value(2.8)
                                        .component(SwissFoodCDComponentDTO.builder().name("Fat, total").build())
                                        .build(),
                                SwissFoodCDValueDTO.builder()
                                        .id(1000)
                                        .value(112.5)
                                        .component(SwissFoodCDComponentDTO.builder().name("Test, test").build())
                                        .build(),
                                SwissFoodCDValueDTO.builder()
                                        .id(6256906)
                                        .value(11.5)
                                        .component(SwissFoodCDComponentDTO.builder().name("Carbohydrates, available").build())
                                        .build(),
                                SwissFoodCDValueDTO.builder()
                                        .id(6256888)
                                        .value(80.0)
                                        .component(SwissFoodCDComponentDTO.builder().name("Energy, kilocalories").build())
                                        .build()
                        ))
                        .build(),
                SwissFoodCDFoodProductDTO.builder()
                        .id("350440")
                        .name("Mashed potatoes, prepared (with cream and butter)")
                        .values(List.of(

                                SwissFoodCDValueDTO.builder()
                                        .id(1767822)
                                        .value(2.0)
                                        .component(SwissFoodCDComponentDTO.builder().name("Test, another").build())
                                        .build(),
                                SwissFoodCDValueDTO.builder()
                                        .id(6301176)
                                        .value(8.4)
                                        .component(SwissFoodCDComponentDTO.builder().name("Fat, total").build())
                                        .build(),
                                SwissFoodCDValueDTO.builder()
                                        .id(6301175)
                                        .value(12.9)
                                        .component(SwissFoodCDComponentDTO.builder().name("Carbohydrates, available").build())
                                        .build(),
                                SwissFoodCDValueDTO.builder()
                                        .id(6301188)
                                        .value(139.0)
                                        .component(SwissFoodCDComponentDTO.builder().name("Energy, kilocalories").build())
                                        .build(),
                                SwissFoodCDValueDTO.builder()
                                        .id(6301201)
                                        .value(2.0)
                                        .component(SwissFoodCDComponentDTO.builder().name("Protein").build())
                                        .build()
                        ))
                        .build()
        );
    }

    public static List<FoodProductDTO> createFoodProductsFromSwissFoodCD() {
        return List.of(
                createExternalFoodProductDTOOne(),
                createExternalFoodProductDTOTwo()
        );
    }
}
