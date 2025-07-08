package com.nutricheck.backend.controller;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.IngredientDTO;
import com.nutricheck.backend.dto.RecipeDTO;
import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.layer.controller.AdminController;
import com.nutricheck.backend.layer.model.entity.Report;
import com.nutricheck.backend.layer.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

@WebMvcTest(AdminController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private AdminService adminService;

    private ReportDTO reportDTO;

    private RecipeDTO recipeDTO;

    // TODO: Can this be set up in a cleaner way?
    @BeforeEach
    public void setUp() {
        reportDTO = ReportDTO.builder()
                .id("testId")
                .description("This is a test report")
                .recipeId("testRecipeId")
                .recipeName("Test Recipe")
                .recipeInstructions("This is a test recipe instruction")
                .build();
        recipeDTO = RecipeDTO.builder()
                .id("testId")
                .name("Test Recipe")
                .instructions("This is a test recipe")
                .servings(1)
                .calories(200)
                .carbohydrates(50)
                .protein(10)
                .fat(5)
                .ingredients(Set.of(IngredientDTO.builder()
                        .recipeId("testId")
                        .foodProductId("testFoodProductId")
                        .foodProduct(FoodProductDTO.builder()
                                .id("testFoodProductId")
                                .name("Test Food Product")
                                .calories(100)
                                .carbohydrates(25)
                                .protein(5)
                                .fat(2)
                                .build())
                        .quantity(100)
                        .build(),
                        IngredientDTO.builder()
                                .recipeId("testId")
                                .foodProductId("testFoodProductId2")
                                .foodProduct(FoodProductDTO.builder()
                                        .id("testFoodProductId2")
                                        .name("Test Food Product 2")
                                        .calories(100)
                                        .carbohydrates(25)
                                        .protein(5)
                                        .fat(3)
                                        .build())
                                .quantity(200)
                        .build())
                )
                .build();
    }
}
