package com.nutricheck.backend.layer.model.repository;

import com.nutricheck.backend.layer.model.entity.FoodProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    /**
     * Finds a food product by all properties except the ID because the ID is generated for the
     * swiss food products and might differ for the same product.
     *
     * Is used to check whether a food product already exists in the database.
     *
     * @param name the name of the food product
     * @param calories the calories of the food product
     * @param carbohydrates the carbohydrates of the food product
     * @param protein the protein of the food product
     * @param fat the fat of the food product
     */
    Optional<FoodProduct> findByNameAndCaloriesAndCarbohydratesAndProteinAndFat(
            String name, Double calories, Double carbohydrates, Double protein, Double fat);
}
