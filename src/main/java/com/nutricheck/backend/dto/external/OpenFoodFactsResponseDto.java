package com.nutricheck.backend.dto.external;

import java.util.List;
import java.util.Objects;

/**
 * Represents the highest-level response from the Open Food Facts API consisting of list of food products.
 */
public class OpenFoodFactsResponseDto {
    private List<OpenFoodFactsFoodProductDto> products;

    public OpenFoodFactsResponseDto() {
    }

    public OpenFoodFactsResponseDto(List<OpenFoodFactsFoodProductDto> products) {
        this.products = products;
    }

    public List<OpenFoodFactsFoodProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<OpenFoodFactsFoodProductDto> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "OpenFoodFactsResponseDto{" +
                "products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenFoodFactsResponseDto that = (OpenFoodFactsResponseDto) o;
        return Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }
}
