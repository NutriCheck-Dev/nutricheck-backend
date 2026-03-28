package com.nutricheck.backend.dto.external;

import java.util.Objects;

/**
 * Represents a component in the Swiss Food CD system, which specifies the name (e.g. "Energy, kilocalories")
 * and other details of the component that a nutritional value is associated with.
 * It is used to map the component data from the Swiss Food CD API to a Java object.
 */
public class SwissFoodDbComponentDto {
    private String name;
    private String code;

    public SwissFoodDbComponentDto() {
    }

    public SwissFoodDbComponentDto(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SwissFoodDbComponentDto{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SwissFoodDbComponentDto that = (SwissFoodDbComponentDto) o;
        return Objects.equals(name, that.name) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
    }
}
