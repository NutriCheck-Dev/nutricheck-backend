package com.nutricheck.backend.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * Represents a food product from the <code>/webresources/BLV-api/food/{DBID}</code> endpoint
 * of the Swiss Food CD API containing detailed information about the food product.
 * This class is used to map the JSON response from the Swiss Food CD API to a Java object.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwissFoodCDFoodProductDTO {
    @JsonIgnore
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String name;
    private List<SwissFoodCDValueDTO> values;
}
