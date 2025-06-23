package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.dto.RecipeRequestDTO;
import com.nutricheck.backend.layer.model.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, uses = IngredientMapper.class)
public interface RecipeRequestMapper {

    @Mapping(target = "ingredients", ignore = true) // Ingredients will be set in RecipeService
    Recipe toEntity(RecipeRequestDTO recipeRequestDTO);

    List<Recipe> toEntity(List<RecipeRequestDTO> recipeRequestDTOs);

}
