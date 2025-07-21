package com.nutricheck.backend.dto.external;

import lombok.*;

/**
 * Represents a nutritional value in the Swiss Food CD system.
 * It is used to map the value data from the Swiss Food CD API to a Java object.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwissFoodCDValueDTO {
    private int id;
    private Double value;
    private SwissFoodCDComponentDTO component;
}
