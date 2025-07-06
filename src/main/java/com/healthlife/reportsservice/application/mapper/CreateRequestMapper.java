package com.healthlife.reportsservice.application.mapper;

import com.healthlife.reportsservice.application.dto.CreateReportRequestDto;
import com.healthlife.reportsservice.domain.models.agregates.Reportes;

public class CreateRequestMapper {

    public Reportes toEntity(CreateReportRequestDto dto) {
        return new Reportes(
                dto.getTitulo()
        );
    }
    /*public CreateReportRequestDto toDto(Reportes entity) {
        CreateReportRequestDto dto = new CreateReportRequestDto();
        dto.setDni(entity.getDni());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        dto.setGenero(entity.getGenero());
        dto.setTipoCuerpo(entity.getTipoCuerpo());
        dto.setImc(entity.getImc());
        return dto;
    }*/
}
