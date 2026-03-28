package com.nutricheck.backend.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nutricheck.backend.Utils;

import java.util.Objects;

/**
 * DTO representing the nutriments of a food product from Open Food Facts.
 */
public class OpenFoodFactsNutrimentsDto {
    private double fat;
    @JsonProperty("energy-kcal")
    private double energyKcal;
    private double carbohydrates;
    private double proteins;

    public OpenFoodFactsNutrimentsDto() {
    }

    public OpenFoodFactsNutrimentsDto(double fat, double energyKcal, double carbohydrates, double proteins) {
        this.fat = fat;
        this.energyKcal = energyKcal;
        this.carbohydrates = carbohydrates;
        this.proteins = proteins;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getEnergyKcal() {
        return energyKcal;
    }

    public void setEnergyKcal(double energyKcal) {
        this.energyKcal = energyKcal;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    @Override
    public String toString() {
        return "OpenFoodFactsNutrimentsDto{" +
                "fat=" + fat +
                ", energyKcal=" + energyKcal +
                ", carbohydrates=" + carbohydrates +
                ", proteins=" + proteins +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenFoodFactsNutrimentsDto that = (OpenFoodFactsNutrimentsDto) o;
        return Utils.compareDouble(that.fat, fat) == 0 &&
                Utils.compareDouble(that.energyKcal, energyKcal) == 0 &&
                Utils.compareDouble(that.carbohydrates, carbohydrates) == 0 &&
                Utils.compareDouble(that.proteins, proteins) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fat, energyKcal, carbohydrates, proteins);
    }
}
