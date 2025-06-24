package com.nutricheck.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AIMealDTO {
    private String name;
    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;
}
