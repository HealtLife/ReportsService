package com.healthlife.reportsservice.application.mapper;

import com.healthlife.reportsservice.application.dto.ReportRequestDto;
import com.healthlife.reportsservice.domain.model.aggregates.ReportRequest;
import org.springframework.stereotype.Component;

@Component
public class ReportRequestMapper {
    public ReportRequest toEntity(ReportRequestDto dto) {
        return  new ReportRequest(
                dto.getDni(),
                dto.getReportType(),
                dto.getRequestDate(),
                dto.getStatus(),
                dto.getUrl(),
                dto.getResumenJson()
        );
    }

    public ReportRequestDto toDto(ReportRequest entity) {
        ReportRequestDto dto = new ReportRequestDto();
        dto.setDni(entity.getDni());
        dto.setReportType(entity.getReportType());
        dto.setRequestDate(entity.getRequestDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}