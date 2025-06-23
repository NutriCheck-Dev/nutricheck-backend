package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import com.nutricheck.backend.layer.model.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final ReportRepository reportRepository;
    private final RecipeRepository recipeRepository;

    public AdminServiceImpl(ReportRepository reportRepository, RecipeRepository recipeRepository) {
        this.reportRepository = reportRepository;
        this.recipeRepository = recipeRepository;
    }
    @Override
    public List<ReportDTO> getAllReports() {
        return List.of();
    }

    @Override
    public void deleteReport(Long id) {
        return;
    }

    @Override
    public void deleteAllReports() {
        return;
    }
}
