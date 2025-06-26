package com.nutricheck.backend.layer.model.repository;

import com.nutricheck.backend.layer.model.entity.FoodProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodProductRepository extends JpaRepository<FoodProduct, String> {
}
