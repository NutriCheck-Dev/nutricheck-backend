package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.dto.ReportDTO;
import com.nutricheck.backend.layer.model.entity.Report;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface ReportMapper {
    ReportDTO toDTO(Report report);
    Report toEntity(ReportDTO reportDTO);
    List<ReportDTO> toDTO(List<Report> reports);
    List<Report> toEntity(List<ReportDTO> reportDTOs);
}
