package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.layer.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("nutricheck/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/reports")
    public ResponseEntity<List<ReportDTO>> getAllReports() {
        return null;
    }

    @DeleteMapping("/reports/delete/{id}")
    public void deleteReport(@PathVariable Long id) {
        return;
    }

    @DeleteMapping("/reports/delete/all")
    public void deleteAllReports() {
        return;
    }
}
