package com.nutricheck.backend.layer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.RecipeDto;
import com.nutricheck.backend.dto.ReportDto;
import com.nutricheck.backend.exception.DuplicateRecipeException;
import com.nutricheck.backend.exception.GlobalExceptionHandler;
import com.nutricheck.backend.exception.RecipeNotFoundException;
import com.nutricheck.backend.layer.service.RecipeService;
import org.junit.jupiter.api.*;
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

@WebMvcTest(controllers = {RecipeController.class, GlobalExceptionHandler.class}, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class RecipeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private RecipeService recipeService;

    private RecipeDto recipeDTO;

    private ReportDto reportDTO;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        recipeDTO = TestDataFactory.createDefaultRecipeDTO();
        reportDTO = TestDataFactory.createDefaultReportDTO();
        objectMapper = new ObjectMapper();
    }

    @Test
    void uploadRecipeTest() throws Exception {
        when(recipeService.uploadRecipe(any(RecipeDto.class))).thenReturn(recipeDTO);

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
    void uploadDuplicateRecipeTest() throws Exception {
        when(recipeService.uploadRecipe(any(RecipeDto.class)))
                .thenThrow(DuplicateRecipeException.class);

        mockMvc.perform(post("/user/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    void reportRecipeTest() throws Exception {
        when(recipeService.reportRecipe(any(ReportDto.class))).thenReturn(reportDTO);

        ResultActions response = mockMvc.perform(post("/user/recipes/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reportDTO)));

        response
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(reportDTO.getId()))
                .andExpect(jsonPath("$.description").value(reportDTO.getDescription()))
                .andExpect(jsonPath("$.recipeId").value(reportDTO.getRecipeId()))
                .andExpect(jsonPath("$.recipeName").value(reportDTO.getRecipeName()))
                .andExpect(jsonPath("$.recipeInstructions").value(reportDTO.getRecipeInstructions()));
    }

    @Test
    void reportMissingRecipeTest() throws Exception {
        when(recipeService.reportRecipe(any(ReportDto.class)))
                .thenThrow(RecipeNotFoundException.class);

        mockMvc.perform(post("/user/recipes/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reportDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void reportRecipeWithInvalidDataTest() throws Exception {
        ReportDto invalidReport = reportDTO;
        invalidReport.setRecipeId("");

        mockMvc.perform(post("/user/recipes/report")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidReport)))
                .andExpect(status().isBadRequest());
    }
}
