package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.dto.IngredientDto;
import com.nutricheck.backend.layer.model.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

/**
 * Mapper interface for converting between Ingredient entities and IngredientDTOs.
 * Utilizes MapStruct for automatic mapping generation.
 */
@Mapper(componentModel = "spring", uses = FoodProductMapper.class)
public interface IngredientMapper {

    @Mapping(source = "id.foodProductId", target = "foodProductId")
    @Mapping(source = "id.recipeId", target = "recipeId")
    IngredientDto toDTO(Ingredient ingredient);

    @Mapping(source = "foodProductId", target = "id.foodProductId")
    @Mapping(source = "recipeId", target = "id.recipeId")
    @Mapping(target = "recipe", ignore = true)
    @Mapping(target = "foodProduct", ignore = true) // FoodProduct and Recipe will be set/created manually in RecipeService
    Ingredient toEntity(IngredientDto ingredientDTO);

    Set<IngredientDto> toDTO(Set<Ingredient> ingredients);

}
