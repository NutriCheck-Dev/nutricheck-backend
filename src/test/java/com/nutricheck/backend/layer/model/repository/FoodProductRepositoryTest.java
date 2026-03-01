package com.nutricheck.backend.layer.model.repository;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.layer.model.entity.FoodProduct;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FoodProductRepositoryTest {
    @Autowired
    private FoodProductRepository foodProductRepository;

    private FoodProduct foodProduct;

    @Test
    @Order(1)
    @Rollback(false)
    void saveFoodProductTest() {
        FoodProduct tempFoodProduct = TestDataFactory.createDefaultFoodProduct();
        foodProduct = foodProductRepository.save(tempFoodProduct);
        assertThat(foodProductRepository.findById(foodProduct.getId()))
                .as("Check if food product table contains the saved food product")
                .isPresent();
    }

    @Test
    @Order(2)
    void findFoodProductByIdTest() {
        Optional<FoodProduct> foundFoodProduct = foodProductRepository.findById(foodProduct.getId());
        assertThat(foundFoodProduct)
                .as("Check if food product can be found by id")
                .isPresent();
        // Verify that the found food product matches the one we saved
        assertEquals(foodProduct, foundFoodProduct.get());
    }

    @Test
    @Order(3)
    void findFoodProductByNameTest() {
        List<FoodProduct> foodProducts = foodProductRepository.findByNameContainingIgnoreCase(foodProduct.getName());
        assertThat(foodProducts)
                .as("Check if food product can be found by name")
                .hasSize(1);
        // Verify that the found food product matches the one we saved
        assertEquals(foodProducts.getFirst(), foodProduct);
    }

    @Test
    @Order(4)
    void findFoodProductByAllPropertiesExceptIDTest() {
        Optional<FoodProduct> foundFoodProduct = foodProductRepository.findByNameAndCaloriesAndCarbohydratesAndProteinAndFat(
                foodProduct.getName(),
                foodProduct.getCalories(),
                foodProduct.getCarbohydrates(),
                foodProduct.getProtein(),
                foodProduct.getFat()
        );
        assertThat(foundFoodProduct)
                .as("Check if food product can be found by all properties except id")
                .isPresent();
        assertEquals(foodProduct, foundFoodProduct.get());
    }

    @Test
    @Order(5)
    @Rollback(false)
    void deleteFoodProductByIdTest() {
        foodProductRepository.deleteById(foodProduct.getId());
        Optional<FoodProduct> deletedFoodProduct = foodProductRepository.findById(foodProduct.getId());
        assertThat(deletedFoodProduct)
                .as("Check if food product table still contains deleted food product")
                .isEmpty();
    }

}
