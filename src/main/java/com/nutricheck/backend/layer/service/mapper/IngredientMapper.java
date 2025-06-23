package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.dto.IngredientDTO;
import com.nutricheck.backend.layer.model.entity.Ingredient;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface IngredientMapper {
    IngredientDTO toDTO(Ingredient ingredient);
    Ingredient toEntity(IngredientDTO ingredientDTO);
    List<IngredientDTO> toDTO(List<Ingredient> ingredients);
    List<Ingredient> toEntity(List<IngredientDTO> ingredientDTOS);

    // after mapping for Ingredient on FoodProduct?
}
