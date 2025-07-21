package com.nutricheck.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwissFoodCDFoodProductDTO {
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private List<SwissFoodCDValueDTO> values;
}
