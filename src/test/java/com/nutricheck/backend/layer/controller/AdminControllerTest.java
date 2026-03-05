package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.RecipeDto;
import com.nutricheck.backend.dto.ReportDto;
import com.nutricheck.backend.exception.RecipeNotFoundException;
import com.nutricheck.backend.exception.ReportNotFoundException;
import com.nutricheck.backend.layer.service.AdminService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private AdminService adminService;

    private ReportDto reportDTO;

    private RecipeDto recipeDTO;

    @BeforeAll
    void setup() {
        reportDTO = TestDataFactory.createDefaultReportDTO();
        recipeDTO = TestDataFactory.createDefaultRecipeDTO();
    }

    @Test
    void getAllReportsTest() throws Exception {
        // Given (expected data)
        List<ReportDto> reports = List.of(reportDTO, reportDTO);

        // When (mocking the service call)
        when(adminService.getAllReports()).thenReturn(reports);

        // Perform the GET request and verify the response
        ResultActions response = mockMvc.perform(get("/admin/reports"));

        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(reports.size()))
                .andExpect(jsonPath("$[0].id").value(reportDTO.getId()))
                .andExpect(jsonPath("$[0].description").value(reportDTO.getDescription()))
                .andExpect(jsonPath("$[0].recipeId").value(reportDTO.getRecipeId()))
                .andExpect(jsonPath("$[0].recipeName").value(reportDTO.getRecipeName()))
                .andExpect(jsonPath("$[0].recipeInstructions").value(reportDTO.getRecipeInstructions()))
                .andExpect(jsonPath("$[1].id").value(reportDTO.getId()))
                .andExpect(jsonPath("$[1].description").value(reportDTO.getDescription()))
                .andExpect(jsonPath("$[1].recipeId").value(reportDTO.getRecipeId()))
                .andExpect(jsonPath("$[1].recipeName").value(reportDTO.getRecipeName()))
                .andExpect(jsonPath("$[1].recipeInstructions").value(reportDTO.getRecipeInstructions()));


    }

    @Test
    void deleteReportTest() throws Exception {
        when(adminService.deleteReport(reportDTO.getId())).thenReturn(reportDTO);

        ResultActions response = mockMvc.perform(delete("/admin/reports/{reportId}", reportDTO.getId()));

        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(reportDTO.getId()))
                .andExpect(jsonPath("$.description").value(reportDTO.getDescription()))
                .andExpect(jsonPath("$.recipeId").value(reportDTO.getRecipeId()))
                .andExpect(jsonPath("$.recipeName").value(reportDTO.getRecipeName()))
                .andExpect(jsonPath("$.recipeInstructions").value(reportDTO.getRecipeInstructions()));
    }

    @Test
    void deleteAllReportsTest() throws Exception {
        List<ReportDto> reports = List.of(reportDTO, reportDTO);

        when(adminService.deleteAllReports()).thenReturn(reports);

        ResultActions response = mockMvc.perform(delete("/admin/reports"));
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(reports.size()))
                .andExpect(jsonPath("$[0].id").value(reportDTO.getId()))
                .andExpect(jsonPath("$[0].description").value(reportDTO.getDescription()))
                .andExpect(jsonPath("$[0].recipeId").value(reportDTO.getRecipeId()))
                .andExpect(jsonPath("$[0].recipeName").value(reportDTO.getRecipeName()))
                .andExpect(jsonPath("$[0].recipeInstructions").value(reportDTO.getRecipeInstructions()))
                .andExpect(jsonPath("$[1].id").value(reportDTO.getId()))
                .andExpect(jsonPath("$[1].description").value(reportDTO.getDescription()))
                .andExpect(jsonPath("$[1].recipeId").value(reportDTO.getRecipeId()))
                .andExpect(jsonPath("$[1].recipeName").value(reportDTO.getRecipeName()))
                .andExpect(jsonPath("$[1].recipeInstructions").value(reportDTO.getRecipeInstructions()));


    }

    @Test
    void deleteRecipeTest() throws Exception {
        when(adminService.deleteRecipe(recipeDTO.getId())).thenReturn(recipeDTO);

        ResultActions response = mockMvc.perform(delete("/admin/recipes/{recipeId}", recipeDTO.getId()));

        response
                .andExpect(status().isOk())
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
    void deleteMissingReportTest() throws Exception {
        when(adminService.deleteReport("missingReportId")).thenThrow(ReportNotFoundException.class);

        mockMvc.perform(delete("/admin/reports/{reportId}", "missingReportId"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteMissingRecipeTest() throws Exception {
        when(adminService.deleteRecipe("missingRecipeId")).thenThrow(RecipeNotFoundException.class);

        mockMvc.perform(delete("/admin/recipes/{recipeId}", "missingRecipeId"))
                .andExpect(status().isNotFound());
    }
}
