package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.dto.RecipeDto;
import com.nutricheck.backend.layer.model.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper interface for converting between Recipe entities and RecipeDTOs.
 * Utilizes MapStruct for automatic mapping generation.
 */
@Mapper(componentModel = "spring", uses = IngredientMapper.class)
public interface RecipeMapper {

    @Mapping(target = "ingredients", ignore = true) // Ingredients will be created manually in RecipeService
    Recipe toEntity(RecipeDto recipeDTO);
    RecipeDto toDTO(Recipe recipe);
    List<RecipeDto> toDTO(List<Recipe> recipes);

}
