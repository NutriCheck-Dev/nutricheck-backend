package com.backend.nutricheck.model.repository;

import com.backend.nutricheck.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
}
