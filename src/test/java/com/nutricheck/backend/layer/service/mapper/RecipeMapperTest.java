package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.layer.model.entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

class RecipeMapperTest {

    private RecipeMapper mapper;
    @Mock
    private IngredientMapper ingredientMapper;
    private Recipe recipe;
    private RecipeDTO recipeDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mapper = new RecipeMapperImpl(ingredientMapper);
        this.recipe = TestDataFactory.createDefaultRecipe();
        this.recipeDTO = TestDataFactory.createDefaultRecipeDTO();
    }

    @Test
    void toRecipeDTOTest() {
        given(ingredientMapper.toDTO(recipe.getIngredients()))
                .willReturn(recipeDTO.getIngredients());

        RecipeDTO mappedRecipeDTO = mapper.toDTO(recipe);
        assertEquals(mappedRecipeDTO, recipeDTO);

    }

    @Test
    void toRecipeTest() {
        Recipe mappedRecipe = mapper.toEntity(recipeDTO);
        assertEquals(mappedRecipe.getId(), recipe.getId());
    }
}