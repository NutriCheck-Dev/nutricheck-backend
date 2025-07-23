package com.nutricheck.backend.layer.model.repository;

import com.nutricheck.backend.layer.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Recipe entities.
 * Provides methods to perform CRUD operations and custom queries on Recipe data.
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {
    /**
     * Finds recipes whose name contains the specified name, ignoring case.
     *
     * @param name the name to search for
     * @return a list of recipes containing the specified name
     */
    List<Recipe> findByNameContainingIgnoreCase(String name);
    /**
     * Finds recipes whose name and instructions match the specified values.
     *
     * @param name the name of the recipe
     * @param instructions the instructions of the recipe
     * @return a list of recipes matching the specified name and instructions
     */
    List<Recipe> findByNameAndInstructions(String name, String instructions);
}
