package com.nutricheck.backend.model.entity;

import jakarta.persistence.*;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String instructions;

    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;

    private int ratingCount;
    private double averageRating;
}
