package com.backend.nutricheck.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FoodEntity {

    private @Id Long id;
    private String name;
    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;
}
