package com.nutricheck.backend.layer.service.impl;

import com.nutricheck.backend.dto.RecipeDto;
import com.nutricheck.backend.dto.ReportDto;
import com.nutricheck.backend.exception.RecipeNotFoundException;
import com.nutricheck.backend.exception.ReportNotFoundException;
import com.nutricheck.backend.layer.model.entity.Recipe;
import com.nutricheck.backend.layer.model.entity.Report;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import com.nutricheck.backend.layer.model.repository.ReportRepository;
import com.nutricheck.backend.layer.service.AdminService;
import com.nutricheck.backend.layer.service.mapper.RecipeMapper;
import com.nutricheck.backend.layer.service.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    static final String NOT_FOUND_MESSAGE = "%s with id %s cannot be found.";

    private final ReportRepository reportRepository;
    private final RecipeRepository recipeRepository;
    private final ReportMapper reportMapper;
    private final RecipeMapper recipeMapper;

    @Override
    public List<ReportDto> getAllReports() {
        List<Report> allReports = reportRepository.findAll();
        List<ReportDto> mappedReports = reportMapper.toDTO(allReports);
        for(ReportDto report : mappedReports) {
            Optional<Recipe> recipe = recipeRepository.findById(report.getRecipeId());
            if(recipe.isPresent()) {
                report.setRecipeName(recipe.get().getName());
                report.setRecipeInstructions(recipe.get().getInstructions());
            }
        }
        return mappedReports;
    }

    @Override
    public ReportDto deleteReport(String reportId) {
        Optional<Report> reportToDelete = reportRepository.findById(reportId);
        if(reportToDelete.isEmpty()) {
            throw new ReportNotFoundException(String.format(NOT_FOUND_MESSAGE, "Report", reportId));
        }
        reportRepository.deleteById(reportId);

        ReportDto mappedReport = reportMapper.toDTO(reportToDelete.get());
        Optional<Recipe> reportedRecipe = recipeRepository.findById(mappedReport.getRecipeId());
        if(reportedRecipe.isPresent()) {
            mappedReport.setRecipeId(reportedRecipe.get().getId());
            mappedReport.setRecipeName(reportedRecipe.get().getName());
        }
        return mappedReport;
    }

    @Override
    public List<ReportDto> deleteAllReports() {
        List<Report> allReports = reportRepository.findAll();
        if(allReports.isEmpty()) {
            return List.of();
        }
        reportRepository.deleteAll();
        return reportMapper.toDTO(allReports);
    }
    @Override
    @CacheEvict(value = "recipes", allEntries = true)
    public RecipeDto deleteRecipe(String recipeId) {
        Optional<Recipe> recipeToDelete = recipeRepository.findById(recipeId);
        if(recipeToDelete.isEmpty()) {
            throw new RecipeNotFoundException(String.format(NOT_FOUND_MESSAGE, "Recipe", recipeId));
        }
        recipeRepository.deleteById(recipeId);
        return recipeMapper.toDTO(recipeToDelete.get());
    }
}
