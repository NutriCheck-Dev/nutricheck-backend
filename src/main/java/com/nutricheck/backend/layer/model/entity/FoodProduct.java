package com.nutricheck.backend.layer.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;


/**
 * Entity representing a food product in the system.
 * This class is used to map food product data to the database.
 */
@Entity
public class FoodProduct extends Nutriment {
    @Id
    private String id;
    private String name;

    public FoodProduct() {
    }

    public FoodProduct(String id, String name, double calories, double carbohydrates, double protein, double fat) {
        super(calories, carbohydrates, protein, fat);
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodProduct that = (FoodProduct) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
