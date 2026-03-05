package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.dto.RecipeDto;
import com.nutricheck.backend.dto.ReportDto;
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
     * @return a list of ReportDto objects representing the reports.
     */
    @GetMapping("/reports")
    public ResponseEntity<List<ReportDto>> getAllReports() {
        List<ReportDto> reports = adminService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    /**
     * This endpoint deletes a specific report by its ID.
     *
     * @param reportId the ID of the report to be deleted.
     * @return the deleted ReportDto object.
     */
    @DeleteMapping("/reports/{reportId}")
    public ResponseEntity<ReportDto> deleteReport(@PathVariable @NotBlank String reportId) {
        ReportDto deletedReport = adminService.deleteReport(reportId);
        return ResponseEntity.ok(deletedReport);
    }

    /**
     * This endpoint deletes all reports submitted by the users.
     *
     * @return a list of ReportDto objects representing the deleted reports.
     */
    @DeleteMapping("/reports")
    public ResponseEntity<List<ReportDto>> deleteAllReports() {
        List<ReportDto> deletedReports = adminService.deleteAllReports();
        return ResponseEntity.ok(deletedReports);
    }

    /**
     * This endpoint deletes a specific recipe by its ID.
     *
     * @param recipeId the ID of the recipe to be deleted.
     * @return the deleted RecipeDto object.
     */
    @DeleteMapping("/recipes/{recipeId}")
    public ResponseEntity<RecipeDto> deleteRecipe(@PathVariable @NotBlank String recipeId) {
        RecipeDto deletedRecipe = adminService.deleteRecipe(recipeId);
        return ResponseEntity.ok(deletedRecipe);

    }
}
