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
@RequestMapping("nutricheck/admin")
@Validated
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/reports")
    public ResponseEntity<List<ReportDTO>> getAllReports() {
        return null;
    }

    @DeleteMapping("/reports/delete/{reportId}")
    public ResponseEntity<ReportDTO> deleteReport(@PathVariable @NotBlank String reportId) {
        return null;
    }

    @DeleteMapping("/reports/delete/all")
    public ResponseEntity<List<ReportDTO>> deleteAllReports() {
        return null;
    }

    @DeleteMapping("/recipe/delete/{recipeId}")
    public ResponseEntity<RecipeDTO> deleteRecipe(@PathVariable @NotBlank String recipeId) {
        return null;
    }
}
