package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.exception.RecipeNotFoundException;
import com.nutricheck.backend.exception.ReportNotFoundException;

import java.util.List;

public interface AdminService {
    List<ReportDTO> getAllReports();
    ReportDTO deleteReport(String reportId) throws ReportNotFoundException;
    List<ReportDTO> deleteAllReports();
    RecipeDTO deleteRecipe(String recipeId) throws RecipeNotFoundException;
}
