package com.nutricheck.backend.layer.service;


import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.exception.RecipeNotFoundException;
import com.nutricheck.backend.exception.ReportNotFoundException;
import com.nutricheck.backend.layer.model.entity.Recipe;
import com.nutricheck.backend.layer.model.entity.Report;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import com.nutricheck.backend.layer.model.repository.ReportRepository;
import com.nutricheck.backend.layer.service.impl.AdminServiceImpl;
import com.nutricheck.backend.layer.service.mapper.RecipeMapper;
import com.nutricheck.backend.layer.service.mapper.ReportMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {
    @Mock
    private ReportRepository reportRepository;
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private ReportMapper reportMapper;
    @Mock
    private RecipeMapper recipeMapper;
    @InjectMocks
    private AdminServiceImpl adminService;

    private String reportId;

    private String recipeId;

    @BeforeEach
    void setup() {
        reportId = "testReportId";
        recipeId = "testRecipeId";
    }

    @Test
    void getAllReportsTest() {
        List<ReportDTO> allExpectedReportDTOs = List.of(
                TestDataFactory.createDefaultReportDTO(),
                TestDataFactory.createDefaultReportDTO()
        );
        List<Report> allExpectedReports = List.of(
                TestDataFactory.createDefaultReport(),
                TestDataFactory.createDefaultReport()
        );

        when(reportRepository.findAll())
                .thenReturn(allExpectedReports);
        when(reportMapper.toDTO(allExpectedReports))
                .thenReturn(allExpectedReportDTOs);

        List<ReportDTO> actualReportDTOs = adminService.getAllReports();
        assertEquals(allExpectedReportDTOs, actualReportDTOs);

    }

    @Test
    void deleteReportTest() {
        ReportDTO expectedReportDTO = TestDataFactory.createDefaultReportDTO();
        Report expectedReport = TestDataFactory.createDefaultReport();

        when(reportRepository.findById(reportId))
                .thenReturn(Optional.of(expectedReport));
        when(reportMapper.toDTO(expectedReport))
                .thenReturn(expectedReportDTO);

        ReportDTO actualReportDTO = adminService.deleteReport(reportId);
        assertEquals(expectedReportDTO, actualReportDTO);
    }

    @Test
    void deleteMissingReportTest() {
        when(reportRepository.findById(reportId))
                .thenReturn(Optional.empty());

        assertThrows(ReportNotFoundException.class, () -> {
            adminService.deleteReport(reportId);
        });
    }

    @Test
    void deleteAllReportsTest() {
        List<ReportDTO> allExpectedReportDTOs = List.of(
                TestDataFactory.createDefaultReportDTO(),
                TestDataFactory.createDefaultReportDTO()
        );
        List<Report> allExpectedReports = List.of(
                TestDataFactory.createDefaultReport(),
                TestDataFactory.createDefaultReport()
        );

        when(reportRepository.findAll())
                .thenReturn(allExpectedReports);
        when(reportMapper.toDTO(allExpectedReports))
                .thenReturn(allExpectedReportDTOs);

        List<ReportDTO> actualReportDTOs = adminService.deleteAllReports();
        assertEquals(allExpectedReportDTOs, actualReportDTOs);
    }

    @Test
    void deleteRecipeTest() {
        RecipeDTO expectedRecipeDTO = TestDataFactory.createDefaultRecipeDTO();
        Recipe expectedRecipe = TestDataFactory.createDefaultRecipe();

        when(recipeRepository.findById(recipeId))
                .thenReturn(Optional.of(expectedRecipe));
        when(recipeMapper.toDTO(expectedRecipe))
                .thenReturn(expectedRecipeDTO);

        RecipeDTO actualRecipeDTO = adminService.deleteRecipe(recipeId);
        assertEquals(expectedRecipeDTO, actualRecipeDTO);
    }

    @Test
    void deleteMissingRecipeTest() {
        when(recipeRepository.findById(recipeId))
                .thenReturn(Optional.empty());

        assertThrows(RecipeNotFoundException.class, () -> {
            adminService.deleteRecipe(recipeId);
        });
    }
}
