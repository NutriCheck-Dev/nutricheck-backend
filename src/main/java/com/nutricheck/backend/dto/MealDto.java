package com.nutricheck.backend.dto;


import lombok.*;

import java.util.Set;


/**
 * Data Transfer Object (DTO) for meal information.
 * This class is used to transfer meal data over the REST API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealDto {
    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;
    private Set<MealItemDto> items;
}
