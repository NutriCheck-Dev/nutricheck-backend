package com.backend.nutricheck.model.repository;

import com.backend.nutricheck.model.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<FoodEntity, Long> {
}
