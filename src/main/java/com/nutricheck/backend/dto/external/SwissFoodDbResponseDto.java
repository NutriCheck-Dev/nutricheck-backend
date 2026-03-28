package com.nutricheck.backend.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Represents a response from the <code>/webresources/BLV-api/foods</code> endpoint of the Swiss Food CD API
 * containing basic food information matching the search string.
 * This class is used to map the JSON response from the Swiss Food CD API to a Java object.
 */
public class SwissFoodDbResponseDto {
    private String id;
    @JsonProperty("foodName")
    private String name;

    public SwissFoodDbResponseDto() {
    }

    public SwissFoodDbResponseDto(String id, String name) {
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
    public String toString() {
        return "SwissFoodDbResponseDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SwissFoodDbResponseDto that = (SwissFoodDbResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
