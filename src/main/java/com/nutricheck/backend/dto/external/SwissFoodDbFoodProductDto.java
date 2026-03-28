package com.nutricheck.backend.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a food product from the Swiss Food CD API containing detailed information about the food product.
 */
public class SwissFoodDbFoodProductDto {
    @JsonIgnore
    private String id = UUID.randomUUID().toString();
    private String name;
    private List<SwissFoodDbValueDto> values;

    public SwissFoodDbFoodProductDto() {
        this.id = UUID.randomUUID().toString();
    }

    public SwissFoodDbFoodProductDto(String id, String name, List<SwissFoodDbValueDto> values) {
        this.id = id == null ? UUID.randomUUID().toString() : id;
        this.name = name;
        this.values = values;
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

    public List<SwissFoodDbValueDto> getValues() {
        return values;
    }

    public void setValues(List<SwissFoodDbValueDto> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "SwissFoodDbFoodProductDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", values=" + values +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SwissFoodDbFoodProductDto that = (SwissFoodDbFoodProductDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, values);
    }
}
