package com.nutricheck.backend.dto.external;

import com.nutricheck.backend.Utils;

import java.util.Objects;

/**
 * Represents a nutritional value in the Swiss Food CD system.
 */
public class SwissFoodDbValueDto {
    private int id;
    private Double value;
    private SwissFoodDbComponentDto component;

    public SwissFoodDbValueDto() {
    }

    public SwissFoodDbValueDto(int id, Double value, SwissFoodDbComponentDto component) {
        this.id = id;
        this.value = value;
        this.component = component;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public SwissFoodDbComponentDto getComponent() {
        return component;
    }

    public void setComponent(SwissFoodDbComponentDto component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return "SwissFoodDbValueDto{" +
                "id=" + id +
                ", value=" + value +
                ", component=" + component +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SwissFoodDbValueDto that = (SwissFoodDbValueDto) o;
        return id == that.id &&
                Utils.compareDouble(value, that.value) == 0 &&
                Objects.equals(component, that.component);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, component);
    }
}
