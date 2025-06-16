package com.backend.nutricheck.model.repository;

import com.backend.nutricheck.model.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
}
