package com.nutricheck.backend.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenFoodFactsResponseDTO {
    private List<OpenFoodFactsFoodProductDTO> products;
}
