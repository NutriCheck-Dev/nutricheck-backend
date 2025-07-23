package com.nutricheck.backend.layer.service.impl;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
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
    public List<ReportDTO> getAllReports() {
        List<Report> allReports = reportRepository.findAll();
        return reportMapper.toDTO(allReports);
    }

    @Override
    public ReportDTO deleteReport(String reportId) {
        Optional<Report> reportToDelete = reportRepository.findById(reportId);
        if(reportToDelete.isEmpty()) {
            throw new ReportNotFoundException(String.format(NOT_FOUND_MESSAGE, "Report", reportId));
        }
        reportRepository.deleteById(reportId);
        return reportMapper.toDTO(reportToDelete.get());
    }

    @Override
    public List<ReportDTO> deleteAllReports() {
        List<Report> allReports = reportRepository.findAll();
        if(allReports.isEmpty()) {
            return List.of();
        }
        reportRepository.deleteAll();
        return reportMapper.toDTO(allReports);
    }
    @Override
    public RecipeDTO deleteRecipe(String recipeId) {
        Optional<Recipe> recipeToDelete = recipeRepository.findById(recipeId);
        if(recipeToDelete.isEmpty()) {
            throw new RecipeNotFoundException(String.format(NOT_FOUND_MESSAGE, "Recipe", recipeId));
        }
        recipeRepository.deleteById(recipeId);
        return recipeMapper.toDTO(recipeToDelete.get());
    }
}
