package com.nutricheck.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)  // id, recipeName and recipeInstructions can be null when receiving
public class ReportDTO {
    private String id;
    private String description;
    @NotBlank(message = "Recipe ID for a Report cannot be blank")
    private String recipeId;
    private String recipeName;
    private String recipeInstructions;
}
