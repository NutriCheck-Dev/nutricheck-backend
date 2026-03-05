package com.nutricheck.backend.layer.client.mapper;

import com.nutricheck.backend.dto.MealDto;
import com.nutricheck.backend.dto.external.AiMealDto;
import com.nutricheck.backend.dto.FoodProductDto;
import com.nutricheck.backend.dto.MealItemDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.UUID;

/**
 * Mapper interface for converting AI-estimated meal information to a MealDto, which can
 * be sent to the frontend.
 * This interface uses MapStruct to automatically generate the implementation.
 */
@Mapper(componentModel = "spring")
public interface AiMealMapper {
    @Mapping(target = "items", ignore = true)
    MealDto toMealDto(AiMealDto aiEstimatedMeal);

    /*
     * Convert AiMealDto to MealDto by setting the whole meal as one ingredient.
     * In this way, we do not need a new dialog in the frontend for logging an AI estimated meal.
     */
    @AfterMapping
    default void setItems(AiMealDto aiEstimatedMeal, @MappingTarget MealDto estimatedMeal) {
        FoodProductDto wholeMeal = FoodProductDto.builder()
                .name(aiEstimatedMeal.getName() + " (AI)")
                .id(UUID.randomUUID().toString())
                .calories(aiEstimatedMeal.getCalories())
                .carbohydrates(aiEstimatedMeal.getCarbohydrates())
                .fat(aiEstimatedMeal.getFat())
                .protein(aiEstimatedMeal.getProtein())
                .build();
        estimatedMeal.setItems(Set.of(new MealItemDto(wholeMeal.getId(), wholeMeal)));
    }
}
