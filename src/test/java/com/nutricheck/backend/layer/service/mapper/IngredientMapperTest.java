package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.IngredientDTO;
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
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class IngredientMapperTest {
    @Mock
    private FoodProductMapper foodProductMapper;
    @InjectMocks
    private IngredientMapperImpl mapper;
    private Set<Ingredient> ingredients;
    private Set<IngredientDTO> ingredientDTOs;
    private Ingredient firstIngredient;
    private IngredientDTO firstIngredientDTO;


    @BeforeEach
    void setup() {
        this.ingredientDTOs = Set.of(TestDataFactory.createDefaultIngredientDTO(),
                                      TestDataFactory.createDefaultIngredientDTO2());
        this.ingredients = Set.of(TestDataFactory.createDefaultIngredient(),
                                  TestDataFactory.createDefaultIngredient2());
        this.firstIngredient = ingredients.iterator().next();
        this.firstIngredientDTO = ingredientDTOs.iterator().next();
    }

    @Test
    void toIngredientDTOTest() {

        given(foodProductMapper.toDTO(firstIngredient.getFoodProduct()))
                .willReturn(firstIngredientDTO.getFoodProduct());

        IngredientDTO mappedIngredientDTO = mapper.toDTO(firstIngredient);
        assertEquals(firstIngredientDTO, mappedIngredientDTO);
    }

    @Test
    void toIngredientTest() {
        Ingredient mappedIngredient = mapper.toEntity(firstIngredientDTO);

        assertEquals(firstIngredient.getId(), mappedIngredient.getId());
        assertEquals(firstIngredient.getQuantity(), mappedIngredient.getQuantity());
        assertNull(mappedIngredient.getRecipe());
        assertNull(mappedIngredient.getFoodProduct());
    }

    @Test
    void toDTOSetTest() {
        given(foodProductMapper.toDTO(firstIngredient.getFoodProduct()))
                .willReturn(firstIngredientDTO.getFoodProduct());
        given(foodProductMapper.toDTO(new ArrayList<>(ingredients).get(1).getFoodProduct()))
                .willReturn(new ArrayList<>(ingredientDTOs).get(1).getFoodProduct());

        Set<IngredientDTO> mappedIngredientDTOs = mapper.toDTO(ingredients);
        assertEquals(ingredientDTOs, mappedIngredientDTOs);
    }

}