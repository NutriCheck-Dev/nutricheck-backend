package com.nutricheck.backend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AIMealDTO {
    private String name;
    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;
}
