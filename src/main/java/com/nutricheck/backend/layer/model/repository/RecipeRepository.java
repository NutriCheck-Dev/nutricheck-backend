package com.nutricheck.backend.layer.model.repository;

import com.nutricheck.backend.layer.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {
    List<Recipe> findByNameAndInstructions(String name, String instructions);
}
