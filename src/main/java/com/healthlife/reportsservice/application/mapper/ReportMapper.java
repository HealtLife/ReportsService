package com.healthlife.reportsservice.application.mapper;

import com.healthlife.reportsservice.application.dto.CreateReportRequestDto;
import com.healthlife.reportsservice.application.dto.ReportDto;
import com.healthlife.reportsservice.domain.models.agregates.Reportes;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {
//String userDni, String titulo, String fecha_creacion, String url, String estado
    public Reportes toEntity(ReportDto dto) {
        return new Reportes(
                dto.getUserDni(),
                dto.getTitulo(),
                dto.getFecha_creacion(),
                dto.getUrl(),
                dto.getEstado()
        );
    }
    public ReportDto toDto(Reportes entity) {
        ReportDto dto = new ReportDto();
        dto.setUserDni(entity.getUserDni());
        dto.setTitulo(entity.getTitulo());
        dto.setFecha_creacion(entity.getFecha_creacion());
        dto.setUrl(entity.getUrl());
        dto.setEstado(entity.getEstado());
        return dto;
    }

}
