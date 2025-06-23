package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.layer.model.entity.Recipe;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, uses = {IngredientMapper.class})
public interface RecipeMapper {
    RecipeDTO toDTO(Recipe recipe);
    Recipe toEntity(RecipeDTO recipeDTO);
    List<RecipeDTO> toDTO(List<Recipe> recipes);
    List<Recipe> toEntity(List<RecipeDTO> recipeDTOs);

    // after mapping for Recipe on Ingredients
}
