package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.dto.IngredientDTO;
import com.nutricheck.backend.layer.model.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = FoodProductMapper.class)
public interface IngredientMapper {

    @Mapping(source = "id.foodProductId", target = "foodProductId")
    @Mapping(source = "id.recipeId", target = "foodId")
    IngredientDTO toDTO(Ingredient ingredient);

    @Mapping(source = "foodProductId", target = "id.foodProductId") // FIXME: does this work?
    @Mapping(source = "foodId", target = "id.recipeId")
    @Mapping(target = "foodProduct", ignore = true) // FoodProduct will be set in RecipeService
    Ingredient toEntity(IngredientDTO ingredientDTO);

    List<IngredientDTO> toDTO(List<Ingredient> ingredients);
    List<Ingredient> toEntity(List<IngredientDTO> ingredientDTOS);

}
