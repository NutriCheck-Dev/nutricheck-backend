package com.nutricheck.backend.model.repository;

import com.nutricheck.backend.model.entity.FoodProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodProductRepository extends JpaRepository<FoodProduct, Long> {
}
