package com.nutricheck.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealDTO {
    private String id;
    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;
    private Set<MealItemDTO> items;
}
