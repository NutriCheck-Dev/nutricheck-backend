package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;

import java.util.List;

/**
 * Service interface for actions performed by an admin.
 * This interface defines methods for managing reports and recipes.
 */
public interface AdminService {
    /**
     * Retrieves all reports from the database.
     *
     * @return a list of ReportDTO objects representing all reports.
     */
    List<ReportDTO> getAllReports();

    /**
     * Deletes a report by its ID.
     *
     * @param reportId the ID of the report to be deleted.
     * @return the deleted ReportDTO object.
     */
    ReportDTO deleteReport(String reportId);
    /**
     * Deletes all reports from the database.
     *
     * @return a list of ReportDTO objects representing the deleted reports.
     */
    List<ReportDTO> deleteAllReports();
    /**
     * Deletes a recipe by its ID.
     *
     * @param recipeId the ID of the recipe to be deleted.
     * @return the deleted RecipeDTO object.
     */
    RecipeDTO deleteRecipe(String recipeId);
}
