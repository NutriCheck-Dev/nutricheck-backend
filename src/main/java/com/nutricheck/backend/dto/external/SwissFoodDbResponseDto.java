package com.nutricheck.backend.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Represents a response from the <code>/webresources/BLV-api/foods</code> endpoint of the Swiss Food CD API
 * containing basic food information matching the search string.
 * This class is used to map the JSON response from the Swiss Food CD API to a Java object.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwissFoodDbResponseDto {
    private String id;
    @JsonProperty("foodName")
    private String name;
}
