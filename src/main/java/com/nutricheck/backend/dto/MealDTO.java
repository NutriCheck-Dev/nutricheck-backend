package com.nutricheck.backend.dto;


import lombok.*;

import java.util.Set;


@Getter
@Setter
@Builder
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
