package com.nutricheck.backend.layer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.exception.RecipeNotFoundException;
import com.nutricheck.backend.layer.service.RecipeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = RecipeController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RecipeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private RecipeService recipeService;

    private RecipeDTO recipeDTO;

    private ReportDTO reportDTO;

    private ObjectMapper objectMapper;

    @BeforeAll
    void setup() {
        recipeDTO = TestDataFactory.createDefaultRecipeDTO();
        reportDTO = TestDataFactory.createDefaultReportDTO();
        objectMapper = new ObjectMapper();
    }

    @Test
    void uploadRecipeTest() throws Exception {
        when(recipeService.uploadRecipe(any(RecipeDTO.class))).thenReturn(recipeDTO);

        ResultActions response = mockMvc.perform(post("/user/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeDTO)));

        response
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(recipeDTO.getId()))
                .andExpect(jsonPath("$.name").value(recipeDTO.getName()))
                .andExpect(jsonPath("$.instructions").value(recipeDTO.getInstructions()))
                .andExpect(jsonPath("$.servings").value(recipeDTO.getServings()))
                .andExpect(jsonPath("$.calories").value(recipeDTO.getCalories()))
                .andExpect(jsonPath("$.carbohydrates").value(recipeDTO.getCarbohydrates()))
                .andExpect(jsonPath("$.protein").value(recipeDTO.getProtein()))
                .andExpect(jsonPath("$.fat").value(recipeDTO.getFat()))
                .andExpect(jsonPath("$.ingredients.size()").value(recipeDTO.getIngredients().size()));
    }

    @Test
    void reportRecipeTest() throws Exception {
        when(recipeService.reportRecipe(any(ReportDTO.class))).thenReturn(reportDTO);

        ResultActions response = mockMvc.perform(post("/user/recipes/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reportDTO)));

        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(reportDTO.getId()))
                .andExpect(jsonPath("$.description").value(reportDTO.getDescription()))
                .andExpect(jsonPath("$.recipeId").value(reportDTO.getRecipeId()))
                .andExpect(jsonPath("$.recipeName").value(reportDTO.getRecipeName()))
                .andExpect(jsonPath("$.recipeInstructions").value(reportDTO.getRecipeInstructions()));
    }

    void reportMissingRecipeTest() throws Exception {
        given(recipeService.reportRecipe(any(ReportDTO.class)))
                .willThrow(RecipeNotFoundException.class);

        mockMvc.perform(post("/user/recipes/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reportDTO)))
                .andExpect(status().isNotFound());
    }
}
