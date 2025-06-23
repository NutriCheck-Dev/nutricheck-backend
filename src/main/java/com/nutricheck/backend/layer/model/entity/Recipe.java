package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    // TODO: Replace deprecated method with different approach for UUID generation
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String name;
    private String instructions;
    private int servings;

    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;

    private int ratingCount;
    private double averageRating;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Ingredient> ingredients = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        // TODO: Implement a proper equals method for duplicate checking
        return false;
    }
    @Override
    public int hashCode() {
        // TODO: Implement proper hash
        return 0;
    }
}
