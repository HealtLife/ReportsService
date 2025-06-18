package com.healthlife.reportsservice.application.services;

import com.healthlife.reportsservice.application.dto.ReportRequestDto;
import com.healthlife.reportsservice.application.mapper.ReportRequestMapper;
import com.healthlife.reportsservice.domain.model.aggregates.ReportRequest;
import com.healthlife.reportsservice.infrastructure.persistance.jpa.ReportRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    private final ReportRequestRepository repository;
    private final ReportRequestMapper mapper;
    private final NutritionDataService nutritionDataService;

    public ReportService(ReportRequestRepository repository, ReportRequestMapper mapper, NutritionDataService nutritionDataService) {
        this.repository = repository;
        this.mapper = mapper;
        this.nutritionDataService = nutritionDataService;
    }

    @Transactional
    public Map<String, Object> generateReport(String dni) {
        // Save report request
        ReportRequest reportRequest = new ReportRequest(dni, "GENERAL", LocalDate.now(), "COMPLETED");
        repository.save(reportRequest);

        // Get all medical data from NutritionService
        return nutritionDataService.getAllMedicalData(dni);
    }

    public List<ReportRequestDto> getReportsByDni(String dni) {
        List<ReportRequest> reports = repository.findByDni(dni);
        if (reports.isEmpty()) {
            throw new EntityNotFoundException("No hay reportes para el DNI " + dni);
        }
        return reports.stream()
                .map(mapper::toDto)
                .toList();
    }
}