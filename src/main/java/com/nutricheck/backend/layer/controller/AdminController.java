package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.layer.service.AdminService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Maps admin-related HTTP requests to the appropriate service methods.
 * Provides endpoints for managing reports and recipes.
 */
@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * This endpoint retrieves all reports submitted by users.
     *
     * @return a list of ReportDTO objects representing the reports.
     */
    @GetMapping("/reports")
    public ResponseEntity<List<ReportDTO>> getAllReports() {
        List<ReportDTO> reports = adminService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    /**
     * This endpoint deletes a specific report by its ID.
     *
     * @param reportId the ID of the report to be deleted.
     * @return the deleted ReportDTO object.
     */
    @DeleteMapping("/reports/delete/{reportId}")
    public ResponseEntity<ReportDTO> deleteReport(@PathVariable @NotBlank String reportId) {
        ReportDTO deletedReport = adminService.deleteReport(reportId);
        return ResponseEntity.ok(deletedReport);
    }

    /**
     * This endpoint deletes all reports submitted by the users.
     *
     * @return a list of ReportDTO objects representing the deleted reports.
     */
    @DeleteMapping("/reports/delete/all")
    public ResponseEntity<List<ReportDTO>> deleteAllReports() {
        List<ReportDTO> deletedReports = adminService.deleteAllReports();
        return ResponseEntity.ok(deletedReports);
    }

    /**
     * This endpoint deletes a specific recipe by its ID.
     *
     * @param recipeId the ID of the recipe to be deleted.
     * @return the deleted RecipeDTO object.
     */
    @DeleteMapping("/recipe/delete/{recipeId}")
    public ResponseEntity<RecipeDTO> deleteRecipe(@PathVariable @NotBlank String recipeId) {
        RecipeDTO deletedRecipe = adminService.deleteRecipe(recipeId);
        return ResponseEntity.ok(deletedRecipe);

    }
}
