package com.nutricheck.backend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwissFoodCDFoodProductDTO {
    private String id;
    private String name;
    private List<SwissFoodCDValueDTO> values;
}
