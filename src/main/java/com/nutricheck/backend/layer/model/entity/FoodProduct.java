package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a food product in the system.
 * This class is used to map food product data to the database.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodProduct {
    @Id
    private String id;
    private String name;

    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;

    @Builder.Default
    @OneToMany(mappedBy = "foodProduct", fetch = FetchType.LAZY)
    private Set<Ingredient> references = new HashSet<>();

}
