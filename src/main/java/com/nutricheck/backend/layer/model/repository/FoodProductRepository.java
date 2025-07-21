package com.nutricheck.backend.layer.model.repository;

import com.nutricheck.backend.layer.model.entity.FoodProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing FoodProduct entities.
 * Provides methods to perform CRUD operations and custom queries on FoodProduct data.
 */
@Repository
public interface FoodProductRepository extends JpaRepository<FoodProduct, String> {
    /**
     * Finds food products whose name contains the specified name, ignoring case.
     *
     * @param name the name to search for
     * @return a list of food products containing the specified name
     */
    List<FoodProduct> findByNameContainingIgnoreCase(String name);
}
