package com.backend.nutricheck.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReportEntity {

    private @Id Long id;
    private Long recipeId;

}
