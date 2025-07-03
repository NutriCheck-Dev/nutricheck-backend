package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.layer.service.AdminService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/reports")
    public ResponseEntity<List<ReportDTO>> getAllReports() {
        List<ReportDTO> reports = adminService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @DeleteMapping("/reports/delete/{reportId}")
    public ResponseEntity<ReportDTO> deleteReport(@PathVariable @NotBlank String reportId) {
        ReportDTO deletedReport = adminService.deleteReport(reportId);
        return ResponseEntity.ok(deletedReport);
    }

    @DeleteMapping("/reports/delete/all")
    public ResponseEntity<List<ReportDTO>> deleteAllReports() {
        List<ReportDTO> deletedReports = adminService.deleteAllReports();
        return ResponseEntity.ok(deletedReports);
    }

    @DeleteMapping("/recipe/delete/{recipeId}")
    public ResponseEntity<RecipeDTO> deleteRecipe(@PathVariable @NotBlank String recipeId) {
        RecipeDTO deletedRecipe = adminService.deleteRecipe(recipeId);
        return ResponseEntity.ok(deletedRecipe);

    }
}
