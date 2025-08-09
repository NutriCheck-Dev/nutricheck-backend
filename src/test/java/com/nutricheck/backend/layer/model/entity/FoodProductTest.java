package com.nutricheck.backend.layer.model.entity;

import com.nutricheck.backend.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodProductTest {

    private FoodProduct foodProduct;

    @BeforeEach
    void setup() {
        foodProduct = TestDataFactory.createDefaultFoodProduct();
    }

    @Test
    void hashCodeConsistencyTest() {
        int hashCode1 = foodProduct.hashCode();
        int hashCode2 = foodProduct.hashCode();
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void hashCodeEqualityTest() {
        FoodProduct expectedFoodProduct = TestDataFactory.createDefaultFoodProduct();
        assertEquals(expectedFoodProduct.hashCode(), foodProduct.hashCode());
    }
}
