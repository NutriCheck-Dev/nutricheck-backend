package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.ReportDTO;
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
    private ReportDTO reportDTO;

    @BeforeEach
    void setUp() {
        this.mapper = Mappers.getMapper(ReportMapper.class);
        this.report = TestDataFactory.createDefaultReport();
        this.reportDTO = TestDataFactory.createDefaultReportDTO();
    }

    @Test
    void toReportDTOTest() {
        ReportDTO mappedReportDTO = mapper.toDTO(report);
        assertThat(mappedReportDTO).usingRecursiveComparison()
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
        List<ReportDTO> expectedReportDTOs = List.of(reportDTO, reportDTO);
        List<ReportDTO> mappedReportDTOs = mapper.toDTO(reports);
        assertThat(mappedReportDTOs)
                .usingRecursiveComparison()
                .ignoringFields("id", "recipeName", "recipeInstructions")
                .isEqualTo(expectedReportDTOs);
    }

    @Test
    void toReportListTest() {
        List<ReportDTO> reportDTOs = List.of(reportDTO, reportDTO);
        List<Report> expectedReports = List.of(report, report);
        List<Report> mappedReports = mapper.toEntity(reportDTOs);
        assertEquals(expectedReports, mappedReports);
    }
}