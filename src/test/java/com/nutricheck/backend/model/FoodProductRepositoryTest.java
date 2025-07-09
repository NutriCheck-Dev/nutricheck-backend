package com.nutricheck.backend.model;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.layer.model.entity.FoodProduct;
import com.nutricheck.backend.layer.model.repository.FoodProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Optional;


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
        Assertions.assertThat(foodProductRepository.findById(foodProduct.getId()))
                .as("Check if food product table contains the saved food product")
                .isPresent();
    }

    @Test
    @Order(2)
    void findFoodProductByNameTest() {
        List<FoodProduct> foodProducts = foodProductRepository.findByNameContainingIgnoreCase(foodProduct.getName());
        Assertions.assertThat(foodProducts)
                .as("Check if food product table contains the saved food product")
                .hasSize(1);
        Assertions.assertThat(foodProducts.get(0).getId())
                .as("Check that the saved food product is the one we expect")
                .isEqualTo(foodProduct.getId());
    }

    @Test
    @Order(3)
    @Rollback(false)
    void deleteFoodProductByIdTest() {
        foodProductRepository.deleteById(foodProduct.getId());
        Optional<FoodProduct> deletedFoodProduct = foodProductRepository.findById(foodProduct.getId());
        Assertions.assertThat(deletedFoodProduct)
                .as("Check if food product table still contains deleted food product")
                .isEmpty();
    }
}
