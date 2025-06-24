package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.dto.RecipeResponseDTO;
import com.nutricheck.backend.layer.model.entity.Recipe;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = IngredientMapper.class)
public interface RecipeResponseMapper {
    RecipeResponseDTO toDTO(Recipe recipe);
    List<RecipeResponseDTO> toDTO(List<Recipe> recipes);
}
