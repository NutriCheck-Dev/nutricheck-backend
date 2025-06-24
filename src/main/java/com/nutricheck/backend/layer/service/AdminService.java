package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.RecipeResponseDTO;
import com.nutricheck.backend.dto.ReportDTO;

import java.util.List;

public interface AdminService {
    List<ReportDTO> getAllReports();
    ReportDTO deleteReport(String reportId);
    List<ReportDTO> deleteAllReports();
    RecipeResponseDTO deleteRecipe(String recipeId);
}
