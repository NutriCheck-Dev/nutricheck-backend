package com.nutricheck.backend.layer.model.repository;

import com.nutricheck.backend.layer.model.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Report entities.
 * Provides methods to perform CRUD operations on Report data.
 */
@Repository
public interface ReportRepository extends JpaRepository<Report, String> {
}
