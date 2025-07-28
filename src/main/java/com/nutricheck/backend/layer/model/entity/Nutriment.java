package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Abstract class representing the nutritional information of a food product or recipe.
 * Prevents code duplication by providing common nutritional fields.
 */
@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Nutriment {
    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;
}
