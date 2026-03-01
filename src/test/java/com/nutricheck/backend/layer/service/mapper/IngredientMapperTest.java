package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.IngredientDto;
import com.nutricheck.backend.layer.model.entity.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientMapperTest {
    @Mock
    private FoodProductMapper foodProductMapper;
    @InjectMocks
    private IngredientMapperImpl mapper;
    private Set<Ingredient> ingredients;
    private Set<IngredientDto> ingredientDtos;
    private Ingredient firstIngredient;
    private IngredientDto firstIngredientDto;


    @BeforeEach
    void setup() {
        this.ingredientDtos = Set.of(TestDataFactory.createDefaultIngredientDTO(),
                                      TestDataFactory.createDefaultIngredientDTO2());
        this.ingredients = Set.of(TestDataFactory.createDefaultIngredient(),
                                  TestDataFactory.createDefaultIngredient2());
        this.firstIngredient = ingredients.iterator().next();
        this.firstIngredientDto = ingredientDtos.iterator().next();
    }

    @Test
    void toIngredientDTOTest() {
        when(foodProductMapper.toDTO(firstIngredient.getFoodProduct()))
                .thenReturn(firstIngredientDto.getFoodProduct());

        IngredientDto mappedIngredientDto = mapper.toDTO(firstIngredient);
        assertEquals(firstIngredientDto, mappedIngredientDto);
    }

    @Test
    void toIngredientTest() {
        Ingredient mappedIngredient = mapper.toEntity(firstIngredientDto);

        assertEquals(firstIngredient.getId(), mappedIngredient.getId());
        assertEquals(firstIngredient.getQuantity(), mappedIngredient.getQuantity());
        assertNull(mappedIngredient.getRecipe());
        assertNull(mappedIngredient.getFoodProduct());
    }

    @Test
    void toDTOSetTest() {
        when(foodProductMapper.toDTO(firstIngredient.getFoodProduct()))
                .thenReturn(firstIngredientDto.getFoodProduct());
        when(foodProductMapper.toDTO(new ArrayList<>(ingredients).get(1).getFoodProduct()))
                .thenReturn(new ArrayList<>(ingredientDtos).get(1).getFoodProduct());

        Set<IngredientDto> mappedIngredientDtos = mapper.toDTO(ingredients);
        assertEquals(ingredientDtos, mappedIngredientDtos);
    }

}