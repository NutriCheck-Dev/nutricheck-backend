package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.layer.model.entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeMapperTest {

    @Mock
    private IngredientMapper ingredientMapper;
    @InjectMocks
    private RecipeMapperImpl mapper;
    private List<Recipe> recipes;
    private List<RecipeDTO> recipeDTOs;

    @BeforeEach
    void setUp() {
        this.recipes = List.of(TestDataFactory.createDefaultRecipe(),
                               TestDataFactory.createDefaultRecipe());
        this.recipeDTOs = List.of(TestDataFactory.createDefaultRecipeDTO(),
                                  TestDataFactory.createDefaultRecipeDTO());

    }

    @Test
    void toRecipeDTOTest() {
        when(ingredientMapper.toDTO(recipes.get(0).getIngredients()))
                .thenReturn(recipeDTOs.get(0).getIngredients());

        RecipeDTO mappedRecipeDTO = mapper.toDTO(recipes.get(0));
        assertEquals(mappedRecipeDTO, recipeDTOs.get(0));

    }

    @Test
    void toRecipeTest() {
        Recipe mappedRecipe = mapper.toEntity(recipeDTOs.get(0));
        assertThat(mappedRecipe)
                .usingRecursiveComparison()
                .ignoringFields("ingredients") // ingredients are handled separately
                .isEqualTo(recipes.get(0));
    }

    @Test
    void toRecipeDTOListTest() {
        when(ingredientMapper.toDTO(recipes.get(0).getIngredients()))
                .thenReturn(recipeDTOs.get(0).getIngredients());
        when(ingredientMapper.toDTO(recipes.get(1).getIngredients()))
                .thenReturn(recipeDTOs.get(1).getIngredients());

        List<RecipeDTO> mappedRecipeDTOs = mapper.toDTO(recipes);
        assertEquals(mappedRecipeDTOs, recipeDTOs);
    }
}