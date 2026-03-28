package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.RecipeDto;
import com.nutricheck.backend.dto.ReportDto;

import java.util.List;

/**
 * Service interface for actions performed by an admin.
 * This interface defines methods for managing reports and recipes.
 */
public interface AdminService {
    /**
     * Retrieves all reports from the database.
     *
     * @return a list of ReportDto objects representing all reports.
     */
    List<ReportDto> getAllReports();

    /**
     * Deletes a report by its ID.
     *
     * @param reportId the ID of the report to be deleted.
     * @return the deleted ReportDto object.
     */
    ReportDto deleteReport(String reportId);
    /**
     * Deletes all reports from the database.
     *
     * @return a list of ReportDto objects representing the deleted reports.
     */
    List<ReportDto> deleteAllReports();
    /**
     * Deletes a recipe by its ID.
     *
     * @param recipeId the ID of the recipe to be deleted.
     * @return the deleted RecipeDto object.
     */
    RecipeDto deleteRecipe(String recipeId);
}
