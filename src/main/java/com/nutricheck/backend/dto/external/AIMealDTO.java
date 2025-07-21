package com.nutricheck.backend.dto.external;

import lombok.*;

/**
 * Data Transfer Object (DTO) for AI-generated meal information.
 * This class is used to transfer meal data, including nutritional information,
 * between different layers of the application.
 */
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
