package com.nutricheck.backend.controller;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.IngredientDTO;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.exception.RecipeNotFoundException;
import com.nutricheck.backend.exception.ReportNotFoundException;
import com.nutricheck.backend.layer.controller.AdminController;
import com.nutricheck.backend.layer.model.entity.Report;
import com.nutricheck.backend.layer.service.AdminService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminController.class)
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private AdminService adminService;

    private ReportDTO reportDTO;

    private RecipeDTO recipeDTO;

    @BeforeAll
    public void setUp() {
        reportDTO = TestDataFactory.createDefaultReportDTO();
        recipeDTO = TestDataFactory.createDefaultRecipeDTO();
    }

    @Test
    public void getAllReportsTest() throws Exception {
        // Precondition the service to return a list of reports
        List<ReportDTO> reports = List.of(reportDTO, reportDTO);
        given(adminService.getAllReports()).willReturn(reports);
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
    public void deleteReportTest() throws Exception {
        given(adminService.deleteReport(reportDTO.getId())).willReturn(reportDTO);

        ResultActions response = mockMvc.perform(delete("/admin/reports/delete/{reportId}", reportDTO.getId()));

        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(reportDTO.getId()))
                .andExpect(jsonPath("$.description").value(reportDTO.getDescription()))
                .andExpect(jsonPath("$.recipeId").value(reportDTO.getRecipeId()))
                .andExpect(jsonPath("$.recipeName").value(reportDTO.getRecipeName()))
                .andExpect(jsonPath("$.recipeInstructions").value(reportDTO.getRecipeInstructions()));
    }

    @Test
    public void deleteAllReportsTest() throws Exception {
        List<ReportDTO> reports = List.of(reportDTO, reportDTO);
        given(adminService.deleteAllReports()).willReturn(reports);
        ResultActions response = mockMvc.perform(delete("admin/reports/delete/all"));

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
    public void deleteRecipeTest() throws Exception {
        given(adminService.deleteRecipe(recipeDTO.getId())).willReturn(recipeDTO);

        ResultActions response = mockMvc.perform(delete("/admin/recipe/delete/{recipeId}", recipeDTO.getId()));

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
    public void deleteMissingReportTest() throws Exception {
        given(adminService.deleteReport("missingReportId")).willThrow(ReportNotFoundException.class);

        mockMvc.perform(delete("/admin/reports/delete/{reportId}", "missingReportId"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteMissingRecipeTest() throws Exception {
        given(adminService.deleteRecipe("missingRecipeId")).willThrow(RecipeNotFoundException.class);

        mockMvc.perform(delete("/admin/recipe/delete/{recipeId}", "missingRecipeId"))
                .andExpect(status().isNotFound());
    }
}
