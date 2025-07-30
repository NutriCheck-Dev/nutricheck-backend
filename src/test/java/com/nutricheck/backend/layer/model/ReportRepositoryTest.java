package com.nutricheck.backend.layer.model;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.layer.model.entity.Report;
import com.nutricheck.backend.layer.model.repository.ReportRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReportRepositoryTest {

    @Autowired
    private ReportRepository reportRepository;

    private Report report;

    @Test
    @Order(1)
    @Rollback(false)
    void saveReportTest() {
        Report tempReport = TestDataFactory.createDefaultReport();
        report = reportRepository.save(tempReport);
        assertNotNull(report.getId(), "Report should have a generated id after saving");
    }

    @Test
    @Order(2)
    void findAllReportsTest() {
        List<Report> reports = reportRepository.findAll();
        assertThat(reports)
                .as("Check if report table only contains the saved report")
                .hasSize(1);
        assertThat(reports.get(0).getId())//
                .as("Check that the saved report is the one we expect")
                .isEqualTo(report.getId());
    }

    @Test
    @Order(3)
    void deleteReportbyIdTest() {
        reportRepository.deleteById(report.getId());
        Optional<Report> deletedReport = reportRepository.findById(report.getId());
        assertThat(deletedReport)
                .as("Check if report table still contains deleted report")
                .isEmpty();
    }

    @Test
    @Order(4)
    @Rollback(false)
    void deleteAllReportsTest() {
        reportRepository.deleteAll();
        List<Report> reports = reportRepository.findAll();
        assertThat(reports)
                .as("Check if report table is empty after deleting all reports")
                .isEmpty();
    }

}
