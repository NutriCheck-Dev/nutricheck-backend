package com.nutricheck.backend.layer.service;


import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.RecipeDto;
import com.nutricheck.backend.dto.ReportDto;
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
        List<ReportDto> allExpectedReportDtos = List.of(
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
                .thenReturn(allExpectedReportDtos);

        List<ReportDto> actualReportDtos = adminService.getAllReports();
        assertEquals(allExpectedReportDtos, actualReportDtos);

    }

    @Test
    void getAllReportsEmptyTest() {
        when(reportRepository.findAll())
                .thenReturn(List.of());
        when(reportMapper.toDTO(List.of()))
                .thenReturn(List.of());

        List<ReportDto> actualReportDtos = adminService.getAllReports();
        assertEquals(List.of(), actualReportDtos);
    }

    @Test
    void getAllReportsWithRecipeInfoTest() {
        Recipe expectedRecipe = TestDataFactory.createDefaultRecipe();
        ReportDto firstReportDto = TestDataFactory.createDefaultReportDTO();
        firstReportDto.setRecipeName(expectedRecipe.getName());
        firstReportDto.setRecipeInstructions(expectedRecipe.getInstructions());
        ReportDto secondReportDto = TestDataFactory.createDefaultReportDTO();
        secondReportDto.setRecipeName(expectedRecipe.getName());
        secondReportDto.setRecipeInstructions(expectedRecipe.getInstructions());

        List<ReportDto> allExpectedReportDtos = List.of(
                firstReportDto,
                secondReportDto
        );
        List<Report> allExpectedReports = List.of(
                TestDataFactory.createDefaultReport(),
                TestDataFactory.createDefaultReport()
        );

        when(reportRepository.findAll())
                .thenReturn(allExpectedReports);
        when(reportMapper.toDTO(allExpectedReports))
                .thenReturn(allExpectedReportDtos);
        when(recipeRepository.findById(recipeId))
                .thenReturn(Optional.of(expectedRecipe));

        List<ReportDto> actualReportDtos = adminService.getAllReports();
        assertEquals(allExpectedReportDtos, actualReportDtos);
    }

    @Test
    void deleteReportTest() {
        ReportDto expectedReportDto = TestDataFactory.createDefaultReportDTO();
        Report expectedReport = TestDataFactory.createDefaultReport();

        when(reportRepository.findById(reportId))
                .thenReturn(Optional.of(expectedReport));
        when(reportMapper.toDTO(expectedReport))
                .thenReturn(expectedReportDto);

        ReportDto actualReportDto = adminService.deleteReport(reportId);
        assertEquals(expectedReportDto, actualReportDto);
    }

    @Test
    void deleteReportWithRecipeInfoTest() {
        Recipe expectedRecipe = TestDataFactory.createDefaultRecipe();
        ReportDto expectedReportDto = TestDataFactory.createDefaultReportDTO();
        expectedReportDto.setRecipeName(expectedRecipe.getName());
        expectedReportDto.setRecipeInstructions(expectedRecipe.getInstructions());
        Report expectedReport = TestDataFactory.createDefaultReport();

        when(reportRepository.findById(reportId)).thenReturn(Optional.of(expectedReport));
        when(reportMapper.toDTO(expectedReport)).thenReturn(expectedReportDto);
        when(recipeRepository.findById(expectedReport.getRecipeId()))
                .thenReturn(Optional.of(expectedRecipe));

        ReportDto actualReportDto = adminService.deleteReport(reportId);
        assertEquals(expectedReportDto, actualReportDto);
    }


    @Test
    void deleteMissingReportTest() {
        when(reportRepository.findById(reportId))
                .thenReturn(Optional.empty());

        assertThrows(ReportNotFoundException.class, () ->
            adminService.deleteReport(reportId)
        );
    }

    @Test
    void deleteAllReportsTest() {
        List<ReportDto> allExpectedReportDtos = List.of(
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
                .thenReturn(allExpectedReportDtos);

        List<ReportDto> actualReportDtos = adminService.deleteAllReports();
        assertEquals(allExpectedReportDtos, actualReportDtos);
    }

    @Test
    void deleteRecipeTest() {
        RecipeDto expectedRecipeDto = TestDataFactory.createDefaultRecipeDTO();
        Recipe expectedRecipe = TestDataFactory.createDefaultRecipe();

        when(recipeRepository.findById(recipeId))
                .thenReturn(Optional.of(expectedRecipe));
        when(recipeMapper.toDTO(expectedRecipe))
                .thenReturn(expectedRecipeDto);

        RecipeDto actualRecipeDto = adminService.deleteRecipe(recipeId);
        assertEquals(expectedRecipeDto, actualRecipeDto);
    }

    @Test
    void deleteMissingRecipeTest() {
        when(recipeRepository.findById(recipeId))
                .thenReturn(Optional.empty());

        assertThrows(RecipeNotFoundException.class, () ->
            adminService.deleteRecipe(recipeId)
        );
    }
}
