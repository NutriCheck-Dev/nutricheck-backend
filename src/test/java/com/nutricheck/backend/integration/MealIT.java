package com.nutricheck.backend.integration;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDto;
import com.nutricheck.backend.dto.MealDto;
import com.nutricheck.backend.dto.RecipeDto;
import com.nutricheck.backend.layer.client.AiModelClient;
import com.nutricheck.backend.layer.client.FoodDbClient;
import com.nutricheck.backend.layer.model.repository.FoodProductRepository;
import com.nutricheck.backend.layer.model.repository.RecipeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(IntegrationTestConfig.class)
@ActiveProfiles("test")
class MealIT {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private RecipeRepository recipeRepo;
    @Autowired
    private FoodProductRepository foodProductRepo;
    @MockitoBean
    @Qualifier("openFoodFacts")
    private FoodDbClient openFoodFactsMock;
    @MockitoBean
    @Qualifier("swiss")
    private FoodDbClient swissMock;
    @MockitoBean
    private AiModelClient aiModelClientMock;

    @AfterEach
    void tearDown() {
        recipeRepo.deleteAll();
    }

    @Test
    void searchRecipeWithoutRecipesTest() {
        ResponseEntity<List<RecipeDto>> response = restTemplate.exchange(
                "/user/search/recipes?name=any",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RecipeDto>>() {
                });
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<RecipeDto> recipes = response.getBody();
        assertEquals(0, recipes.size());
    }

    @Test
    void searchRecipeWithExistingTest() {
        recipeRepo.save(TestDataFactory.createDefaultRecipe());

        ResponseEntity<List<RecipeDto>> response = restTemplate.exchange(
                "/user/search/recipes?name={recipeName}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RecipeDto>>() {
                },
                "Test Recipe");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<RecipeDto> recipes = response.getBody();
        assertEquals(1, recipes.size());
        assertEquals(TestDataFactory.createDefaultRecipeDTO(), recipes.get(0));
    }

    @Test
    void searchRecipeWithNonExistingTest() {
        recipeRepo.save(TestDataFactory.createDefaultRecipe());

        ResponseEntity<List<RecipeDto>> response = restTemplate.exchange(
                "/user/search/recipes?name={recipeName}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RecipeDto>>() {
                },
                "Non Existing Recipe");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<RecipeDto> recipes = response.getBody();
        assertEquals(0, recipes.size());
    }

    @Test
    void searchFoodProductsTest() {
        when(openFoodFactsMock.search(any(), any())).thenReturn(List.of(TestDataFactory.createDefaultFoodProductDTO()));
        when(swissMock.search(any(), any())).thenReturn(List.of(TestDataFactory.createDefaultFoodProductDTO2()));

        ResponseEntity<List<FoodProductDto>> response = restTemplate.exchange(
                "/user/search/products?name={productName}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FoodProductDto>>() {
                },
                "Test");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<FoodProductDto> products = response.getBody();
        assertEquals(2, products.size());
        assertThat(products)
                .containsExactlyInAnyOrderElementsOf(List.of(
                        TestDataFactory.createDefaultFoodProductDTO(),
                        TestDataFactory.createDefaultFoodProductDTO2()
                ));
    }

    @Test
    void searchFoodProductsWithInternalTest() {
        foodProductRepo.save(TestDataFactory.createDefaultFoodProduct());
        when(openFoodFactsMock.search(any(), any())).thenReturn(List.of());
        when(swissMock.search(any(), any())).thenReturn(List.of(TestDataFactory.createDefaultFoodProductDTO2()));

        ResponseEntity<List<FoodProductDto>> response = restTemplate.exchange(
                "/user/search/products?name={productName}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FoodProductDto>>() {
                },
                "Test");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<FoodProductDto> products = response.getBody();
        assertEquals(2, products.size());
        assertThat(products)
                .containsExactlyInAnyOrderElementsOf(List.of(
                        TestDataFactory.createDefaultFoodProductDTO(),
                        TestDataFactory.createDefaultFoodProductDTO2()
                ));
    }

    @Test
    void estimateMealTest() throws IOException {
        ClassPathResource resource = new ClassPathResource("spaghetti.jpg");
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", resource);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        when(aiModelClientMock.estimateMeal(any(), any())).thenReturn(TestDataFactory.createMealDTOFromGemini());

        ResponseEntity<MealDto> response = restTemplate.postForEntity(
                "/user/meal",
                requestEntity,
                MealDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        MealDto meal = response.getBody();
        assertEquals(TestDataFactory.createMealDTOFromGemini(), meal);
    }

    @Test
    void estimateInvalidMealTest() {
        ClassPathResource resource = new ClassPathResource("spaghetti.jpg");
        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.setContentType(MediaType.IMAGE_PNG); // wrong image type
        HttpEntity<Resource> fileEntity = new HttpEntity<>(resource, fileHeaders);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileEntity);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<MealDto> response = restTemplate.postForEntity(
                "/user/meal",
                requestEntity,
                MealDto.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


}
