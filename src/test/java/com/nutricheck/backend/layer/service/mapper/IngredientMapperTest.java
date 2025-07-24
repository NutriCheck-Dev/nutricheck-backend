package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.IngredientDTO;
import com.nutricheck.backend.layer.model.entity.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class IngredientMapperTest {

    private IngredientMapper mapper;
    @Mock
    private FoodProductMapper foodProductMapper;
    private Ingredient ingredient;
    private IngredientDTO ingredientDTO;


    @BeforeEach
    void setUp() {
         mapper = new IngredientMapperImpl(foodProductMapper);

        this.ingredientDTO = TestDataFactory.createDefaultIngredientDTO();
        this.ingredient = TestDataFactory.createDefaultIngredient();
    }

    @Test
    void toIngredientDTOTest() {
        given(foodProductMapper.toDTO(ingredient.getFoodProduct()))
                .willReturn(ingredientDTO.getFoodProduct());

        IngredientDTO mappedIngredientDTO = mapper.toDTO(ingredient);
        assertEquals(ingredientDTO, mappedIngredientDTO);
    }

    @Test
    void toIngredientTest() {
        Ingredient mappedIngredient = mapper.toEntity(ingredientDTO);

        assertEquals(mappedIngredient.getId().getRecipeId(), ingredient.getId().getRecipeId());
        assertEquals(mappedIngredient.getId().getFoodProductId(), ingredient.getId().getFoodProductId());
        assertEquals(mappedIngredient.getQuantity(), ingredient.getQuantity());
    }

}