package com.nutricheck.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private String id;
    private String description;
    @NotBlank(message = "Recipe ID for a Report cannot be blank")
    private String recipeId;
    // TODO: Annotate the properties so that Jackson can even deserialize when they are missing
    private String recipeName;
    private String recipeInstructions;
}
