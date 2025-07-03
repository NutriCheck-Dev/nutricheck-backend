package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;

import java.util.List;

public interface AdminService {
    List<ReportDTO> getAllReports();
    ReportDTO deleteReport(String reportId);
    List<ReportDTO> deleteAllReports();
    RecipeDTO deleteRecipe(String recipeId);
}
