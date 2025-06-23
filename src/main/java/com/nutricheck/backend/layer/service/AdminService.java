package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.ReportDTO;

import java.util.List;

public interface AdminService {
    List<ReportDTO> getAllReports();
    void deleteReport(Long id);
    void deleteAllReports();
}
