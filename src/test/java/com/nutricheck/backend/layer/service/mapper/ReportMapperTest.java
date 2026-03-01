package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.ReportDto;
import com.nutricheck.backend.layer.model.entity.Report;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReportMapperTest {

    private ReportMapper mapper;
    private Report report;
    private ReportDto reportDTO;

    @BeforeEach
    void setUp() {
        this.mapper = Mappers.getMapper(ReportMapper.class);
        this.report = TestDataFactory.createDefaultReport();
        this.reportDTO = TestDataFactory.createDefaultReportDTO();
    }

    @Test
    void toReportDTOTest() {
        ReportDto mappedReportDto = mapper.toDTO(report);
        assertThat(mappedReportDto).usingRecursiveComparison()
                // id is not set for default report as it is generated, recipe-specific fields are also not set
                .ignoringFields("id", "recipeName", "recipeInstructions")
                .isEqualTo(reportDTO);
    }

    @Test
    void toReportTest() {
        Report mappedReport = mapper.toEntity(reportDTO);
        assertEquals(report, mappedReport);
    }

    @Test
    void toReportDTOListTest() {
        List<Report> reports = List.of(report, report);
        List<ReportDto> expectedReportDtos = List.of(reportDTO, reportDTO);
        List<ReportDto> mappedReportDtos = mapper.toDTO(reports);
        assertThat(mappedReportDtos)
                .usingRecursiveComparison()
                .ignoringFields("id", "recipeName", "recipeInstructions")
                .isEqualTo(expectedReportDtos);
    }

    @Test
    void toReportListTest() {
        List<ReportDto> reportDtos = List.of(reportDTO, reportDTO);
        List<Report> expectedReports = List.of(report, report);
        List<Report> mappedReports = mapper.toEntity(reportDtos);
        assertEquals(expectedReports, mappedReports);
    }
}