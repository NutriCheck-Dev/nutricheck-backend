package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.layer.model.entity.Report;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

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
        assertEquals(reportDTO.getRecipeId(), mappedReportDTO.getRecipeId());
    }

    @Test
    void toReportTest() {
        Report mappedReport = mapper.toEntity(reportDTO);
        assertEquals(report.getId(), mappedReport.getId());
    }
}