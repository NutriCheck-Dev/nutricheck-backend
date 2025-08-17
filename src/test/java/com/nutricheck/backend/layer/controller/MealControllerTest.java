package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.MealDTO;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.exception.GlobalExceptionHandler;
import com.nutricheck.backend.layer.service.MealService;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(controllers = {MealController.class, GlobalExceptionHandler.class}, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MealControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private MealService mealService;

    private MealDTO mealDTO;

    private RecipeDTO recipeDTO;

    private FoodProductDTO foodProductDTO;

    @BeforeAll
    void setup() {
        mealDTO = TestDataFactory.createDefaultMealDTO();
        recipeDTO = TestDataFactory.createDefaultRecipeDTO();
        foodProductDTO = TestDataFactory.createDefaultFoodProductDTO();
    }
    @Test
    void searchFoodProductTest() throws Exception {
        List<FoodProductDTO> foodProducts = List.of(foodProductDTO, foodProductDTO);

        when(mealService.searchFoodProduct(foodProductDTO.getName(), "en"))
                .thenReturn(foodProducts);

        ResultActions response = mockMvc.perform(get("/user/search/products/{name}",
                foodProductDTO.getName())
                .param("language", "en"));
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(foodProducts.size()))
                .andExpect(jsonPath("$[0].id").value(foodProductDTO.getId()))
                .andExpect(jsonPath("$[0].name").value(foodProductDTO.getName()))
                .andExpect(jsonPath("$[0].calories").value(foodProductDTO.getCalories()))
                .andExpect(jsonPath("$[0].carbohydrates").value(foodProductDTO.getCarbohydrates()))
                .andExpect(jsonPath("$[0].protein").value(foodProductDTO.getProtein()))
                .andExpect(jsonPath("$[0].fat").value(foodProductDTO.getFat()))
                .andExpect(jsonPath(("$[1].id")).value(foodProductDTO.getId()))
                .andExpect(jsonPath("$[1].name").value(foodProductDTO.getName()));
    }

    @Test
    void searchFoodProductWithInvalidLanguageTest() throws Exception {
        mockMvc.perform(get("/user/search/products/{name}", foodProductDTO.getName())
                        .param("language", "fr"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.body.detail", containsString("searchFoodProduct.language: Only german (de) and english (en) are allowed")));
    }

    @Test
    void searchRecipeTest() throws Exception {
        List<RecipeDTO> recipes = List.of(recipeDTO, recipeDTO);

        when(mealService.searchRecipe(recipeDTO.getName()))
                .thenReturn(recipes);
        ResultActions response = mockMvc.perform(get("/user/search/recipes/{name}",
                recipeDTO.getName()));

        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(recipes.size()))
                .andExpect(jsonPath("$[0].id").value(recipeDTO.getId()))
                .andExpect(jsonPath("$[0].name").value(recipeDTO.getName()))
                .andExpect(jsonPath("$[0].instructions").value(recipeDTO.getInstructions()))
                .andExpect(jsonPath("$[0].servings").value(recipeDTO.getServings()))
                .andExpect(jsonPath("$[0].calories").value(recipeDTO.getCalories()))
                .andExpect(jsonPath("$[0].carbohydrates").value(recipeDTO.getCarbohydrates()))
                .andExpect(jsonPath("$[0].protein").value(recipeDTO.getProtein()))
                .andExpect(jsonPath("$[0].fat").value(recipeDTO.getFat()))
                .andExpect(jsonPath("$[1].id").value(recipeDTO.getId()))
                .andExpect(jsonPath("$[1].name").value(recipeDTO.getName()));
    }
    @Test
    void estimateMealTest() throws Exception {
        ClassPathResource resource = new ClassPathResource("spaghetti.png");
        MockMultipartFile image = new MockMultipartFile(
                "file",
                "spaghetti.png",
                MediaType.IMAGE_PNG_VALUE,
                FileUtils.readFileToByteArray(resource.getFile()));
        String language = "en";

        when(mealService.estimateMeal(image, language)).thenReturn(mealDTO);

        ResultActions response = mockMvc.perform(multipart("/user/meal")
                .file(image)
                .param("language", language)
                .contentType(MediaType.MULTIPART_FORM_DATA));

        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.calories").value(mealDTO.getCalories()))
                .andExpect(jsonPath("$.carbohydrates").value(mealDTO.getCarbohydrates()))
                .andExpect(jsonPath("$.protein").value(mealDTO.getProtein()))
                .andExpect(jsonPath("$.fat").value(mealDTO.getFat()))
                .andExpect(jsonPath("$.items.size()").value(mealDTO.getItems().size()));
    }

    @Test
    void estimateMealWithInvalidFileTest() throws Exception {
        MockMultipartFile invalidImage = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "This is not an image".getBytes());

        ResultActions response = mockMvc.perform(multipart("/user/meal")
                .file(invalidImage)
                .contentType(MediaType.MULTIPART_FORM_DATA));

        response
                .andExpect(status().isBadRequest());
    }

    @Test
    void estimateMealWithInvalidLanguageTest() throws Exception {
        ClassPathResource resource = new ClassPathResource("spaghetti.png");
        MockMultipartFile image = new MockMultipartFile(
                "file",
                "spaghetti.png",
                MediaType.IMAGE_PNG_VALUE,
                FileUtils.readFileToByteArray(resource.getFile()));

        mockMvc.perform(multipart("/user/meal")
                        .file(image)
                        .param("language", "fr")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.body.detail", containsString("estimateMeal.language: " +
                        "Only german (de) and english (en) are allowed")));
    }


    /*
     * Assumes a JSON parsing error occurs during deserialzation of the response from the
     * AI model. Verifies that GlobalExceptionHandler handles the error.
     */
    @Test
    void estimateMealWithIOExceptionTest() throws Exception {
        ClassPathResource resource = new ClassPathResource("spaghetti.png");
        MockMultipartFile image = new MockMultipartFile(
                "file",
                "spaghetti.png",
                MediaType.IMAGE_PNG_VALUE,
                FileUtils.readFileToByteArray(resource.getFile()));
        String language = "en";

        when(mealService.estimateMeal(image, language)).thenThrow(IOException.class);

        mockMvc.perform(multipart("/user/meal")
                        .file(image)
                        .param("language", language)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isInternalServerError());
    }
}
