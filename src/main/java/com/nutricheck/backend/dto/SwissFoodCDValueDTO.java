package com.nutricheck.backend.dto;

import lombok.*;

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
