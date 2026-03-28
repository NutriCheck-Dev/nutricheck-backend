package com.nutricheck.backend.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Represents a food product from the Open Food Facts API.
 */
public class OpenFoodFactsFoodProductDto {
    @JsonProperty("_id")
    private String id;
    @JsonProperty("product_name")
    private String name;
    @JsonProperty("product_name_de")
    private String germanName;
    @JsonProperty("product_name_en")
    private String englishName;
    private OpenFoodFactsNutrimentsDto nutriments;

    public OpenFoodFactsFoodProductDto() {
    }

    public OpenFoodFactsFoodProductDto(String id, String name, String germanName, String englishName, OpenFoodFactsNutrimentsDto nutriments) {
        this.id = id;
        this.name = name;
        this.germanName = germanName;
        this.englishName = englishName;
        this.nutriments = nutriments;
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

    public String getGermanName() {
        return germanName;
    }

    public void setGermanName(String germanName) {
        this.germanName = germanName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public OpenFoodFactsNutrimentsDto getNutriments() {
        return nutriments;
    }

    public void setNutriments(OpenFoodFactsNutrimentsDto nutriments) {
        this.nutriments = nutriments;
    }

    @Override
    public String toString() {
        return "OpenFoodFactsFoodProductDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", germanName='" + germanName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", nutriments=" + nutriments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenFoodFactsFoodProductDto that = (OpenFoodFactsFoodProductDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(germanName, that.germanName) &&
                Objects.equals(englishName, that.englishName) &&
                Objects.equals(nutriments, that.nutriments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, germanName, englishName, nutriments);
    }
}
