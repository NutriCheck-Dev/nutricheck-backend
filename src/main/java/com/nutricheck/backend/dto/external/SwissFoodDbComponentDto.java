package com.nutricheck.backend.dto.external;

import lombok.*;

/**
 * Represents a component in the Swiss Food CD system, which specifies the name (e.g. "Energy, kilocalories")
 * and other details of the component that a nutritional value is associated with.
 * It is used to map the component data from the Swiss Food CD API to a Java object.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwissFoodDbComponentDto {
    private String name;
    private String code;
}
