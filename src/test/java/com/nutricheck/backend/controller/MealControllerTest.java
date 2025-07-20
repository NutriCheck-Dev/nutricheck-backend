package com.nutricheck.backend.controller;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.MealDTO;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.layer.controller.MealController;
import com.nutricheck.backend.layer.service.MealService;
import com.nutricheck.backend.util.FileUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Base64;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(controllers = MealController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
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
        given(mealService.searchFoodProduct(foodProductDTO.getName(), "en"))
                .willReturn(foodProducts);

        ResultActions response = mockMvc.perform(get("/user/search/product/{name}/{language}",
                foodProductDTO.getName(), "en"));
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
    void searchRecipeTest() throws Exception {
        List<RecipeDTO> recipes = List.of(recipeDTO, recipeDTO);
        given(mealService.searchRecipe(recipeDTO.getName()))
                .willReturn(recipes);
        ResultActions response = mockMvc.perform(get("/user/search/recipe/{name}",
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
        MockMultipartFile image = new MockMultipartFile(
                "file",
                "test.png",
                MediaType.IMAGE_PNG_VALUE,
                Base64.getMimeDecoder().decode(FileUtil.readFileAsString("encoded-image.txt")));

        given(mealService.estimateMeal(image)).willReturn(mealDTO);

        ResultActions response = mockMvc.perform(multipart("/user/meal/estimate")
                .file(image)
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

        ResultActions response = mockMvc.perform(multipart("/user/meal/estimate")
                .file(invalidImage)
                .contentType(MediaType.MULTIPART_FORM_DATA));

        response
                .andExpect(status().isBadRequest());
    }
}
