package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import com.nutricheck.backend.layer.model.repository.ReportRepository;
import com.nutricheck.backend.layer.service.mapper.RecipeMapper;
import com.nutricheck.backend.layer.service.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ReportRepository reportRepository;
    private final RecipeRepository recipeRepository;
    private final ReportMapper reportMapper;
    private final RecipeMapper recipeMapper;

    @Override
    public List<ReportDTO> getAllReports() {
        return List.of();
    }

    @Override
    public ReportDTO deleteReport(String reportId) {
        return null;
    }

    @Override
    public List<ReportDTO> deleteAllReports() {
        return null;
    }
    @Override
    public RecipeDTO deleteRecipe(String recipeId) {
        return null;
    }
}
