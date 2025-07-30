package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.MealDTO;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.layer.client.AIModelClient;
import com.nutricheck.backend.layer.client.FoodDBClient;
import com.nutricheck.backend.layer.model.entity.FoodProduct;
import com.nutricheck.backend.layer.model.repository.FoodProductRepository;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import com.nutricheck.backend.layer.service.impl.MealServiceImpl;
import com.nutricheck.backend.layer.service.mapper.FoodProductMapper;
import com.nutricheck.backend.layer.service.mapper.RecipeMapper;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MealServiceTest {

    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private FoodProductRepository foodProductRepository;
    @Mock
    private RecipeMapper recipeMapper;
    @Mock
    private FoodProductMapper foodProductMapper;
    @Mock
    private FoodDBClient openFoodFactsClient;
    @Mock
    private FoodDBClient swissFoodCDClient;
    @Mock
    private AIModelClient aiModelClient;

    private MealServiceImpl mealService;

    private String recipeName;

    private String foodProductName;

    private String language;

    @BeforeEach
    void setup() {
        foodProductName = "potato";
        language = "en";
        recipeName = "test recipe";
        mealService = new MealServiceImpl(
                recipeRepository,
                foodProductRepository,
                recipeMapper,
                foodProductMapper,
                openFoodFactsClient,
                swissFoodCDClient,
                aiModelClient
        );
    }

    @Test
    void searchFoodProductTest() {
        given(foodProductRepository.findByNameContainingIgnoreCase(foodProductName))
                .willReturn(List.of());
        given(foodProductMapper.toDTO(anyList())).willReturn(List.of());
        List<FoodProductDTO> expectedSwissProducts = List.of(
                TestDataFactory.createFoodProductDTOOneFromSwissDB(),
                TestDataFactory.createFoodProductDTOTwoFromSwissDB()
        );
        given(swissFoodCDClient.search(foodProductName, language))
                .willReturn(expectedSwissProducts);
        List<FoodProductDTO> expectedOpenProducts = List.of(
                TestDataFactory.createFoodProductDTOOneFromOpenFoodFacts(),
                TestDataFactory.createFoodProductDTOTwoFromOpenFoodFacts()
        );
        given(openFoodFactsClient.search(foodProductName, language))
                .willReturn(expectedOpenProducts);


        Comparator<FoodProductDTO> nameLengthComparator = (product1, product2) ->
                Integer.compare(product1.getName().length(), product2.getName().length());
        List<FoodProductDTO> expectedProducts = Stream.concat(
                expectedSwissProducts.stream(),
                expectedOpenProducts.stream()
        ).collect(Collectors.toList());
        Collections.sort(expectedProducts, nameLengthComparator);

        List<FoodProductDTO> actualProducts = mealService.searchFoodProduct(foodProductName, language);
        assertEquals(expectedProducts, actualProducts);

    }

    @Test
    void searchFoodProductOnlyInternalTest() {
        List<FoodProduct> internalProducts = new ArrayList<>();
        for(int i = 0; i < MealServiceImpl.MAX_SEARCH_RESULTS; i++) {
            FoodProduct internalProduct = TestDataFactory.createDefaultFoodProduct();
            internalProduct.setId(String.valueOf(i));
            internalProducts.add(internalProduct);
        }
        given(foodProductRepository.findByNameContainingIgnoreCase(foodProductName))
                .willReturn(internalProducts);

        List<FoodProductDTO> expectedProducts = Mappers.getMapper(FoodProductMapper.class)
                .toDTO(internalProducts);
        given(foodProductMapper.toDTO(anyList()))
                .willReturn(expectedProducts);

        List<FoodProductDTO> actualProducts = mealService.searchFoodProduct(foodProductName, language);
        assertEquals(actualProducts, expectedProducts);

    }

    @Test
    void searchRecipeTest() {
        given(recipeRepository.findByNameContainingIgnoreCase(recipeName))
                .willReturn(List.of(TestDataFactory.createDefaultRecipe()));
        List<RecipeDTO> expectedRecipes = List.of(TestDataFactory.createDefaultRecipeDTO());
        given(recipeMapper.toDTO(anyList()))
                .willReturn(expectedRecipes);

        List<RecipeDTO> actualRecipes = mealService.searchRecipe(recipeName);
        assertEquals(actualRecipes, expectedRecipes);
    }

    @Test
    void estimateMealTest() throws Exception {
        ClassPathResource resource = new ClassPathResource("spaghetti.png");
        MockMultipartFile image = new MockMultipartFile(
                "file",
                "spaghetti.png",
                MediaType.IMAGE_PNG_VALUE,
                FileUtils.readFileToByteArray(resource.getFile()));

        given(aiModelClient.estimateMeal(image.getBytes()))
                .willReturn(TestDataFactory.createDefaultMealDTO());

        MealDTO actualMeal = mealService.estimateMeal(image);
        assertEquals(actualMeal, TestDataFactory.createDefaultMealDTO());
    }
}
