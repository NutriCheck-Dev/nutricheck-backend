package com.nutricheck.backend.layer.client.mapper;

import com.nutricheck.backend.dto.external.AIMealDTO;
import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.MealDTO;
import com.nutricheck.backend.dto.MealItemDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.UUID;

/**
 * Mapper interface for converting AI-estimated meal information to a MealDTO, which can
 * be sent to the frontend.
 * This interface uses MapStruct to automatically generate the implementation.
 */
@Mapper(componentModel = "spring")
public interface AIMealMapper {
    @Mapping(target = "items", ignore = true)
    MealDTO toMealDTO(AIMealDTO aiEstimatedMeal);

    /*
     * Convert AIMealDTO to MealDTO by setting the whole meal as one ingredient.
     * In this way, we do not need a new dialog in the frontend for logging an AI estimated meal.
     */
    @AfterMapping
    default void setItems(AIMealDTO aiEstimatedMeal, @MappingTarget MealDTO estimatedMeal) {
        FoodProductDTO wholeMeal = FoodProductDTO.builder()
                .name(aiEstimatedMeal.getName() + " (AI)")
                .id(UUID.randomUUID().toString())
                .calories(aiEstimatedMeal.getCalories())
                .carbohydrates(aiEstimatedMeal.getCarbohydrates())
                .fat(aiEstimatedMeal.getFat())
                .protein(aiEstimatedMeal.getProtein())
                .build();
        estimatedMeal.setItems(Set.of(new MealItemDTO(wholeMeal.getId(), wholeMeal)));
    }
}
