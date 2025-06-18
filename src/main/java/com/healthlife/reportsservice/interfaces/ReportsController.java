package com.healthlife.reportsservice.interfaces;

import com.healthlife.reportsservice.application.dto.ReportRequestDto;
import com.healthlife.reportsservice.application.services.NutritionDataService;
import com.healthlife.reportsservice.application.services.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/reports", produces = APPLICATION_JSON_VALUE)
public class ReportsController {
    private final ReportService reportService;
    private final NutritionDataService nutritionDataService;

    public ReportsController(ReportService reportService, NutritionDataService nutritionDataService) {
        this.reportService = reportService;
        this.nutritionDataService = nutritionDataService;
    }

    @PostMapping("/generate/{dni}")
    public ResponseEntity<Map<String, Object>> generateReport(@PathVariable String dni) {
        Map<String, Object> report = reportService.generateReport(dni);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/personal-info/{dni}")
    public ResponseEntity<Object> getPersonalInfo(@PathVariable String dni) {
        Object data = nutritionDataService.getPersonalInfo(dni);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/medical-notes/{dni}")
    public ResponseEntity<List<Object>> getMedicalNotes(@PathVariable String dni) {
        List<Object> data = nutritionDataService.getMedicalNotes(dni);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/allergies/{dni}")
    public ResponseEntity<List<Object>> getAllergies(@PathVariable String dni) {
        List<Object> data = nutritionDataService.getAllergies(dni);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/prescriptions/{dni}")
    public ResponseEntity<List<Object>> getPrescriptions(@PathVariable String dni) {
        List<Object> data = nutritionDataService.getPrescriptions(dni);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/vaccines/{dni}")
    public ResponseEntity<List<Object>> getVaccines(@PathVariable String dni) {
        List<Object> data = nutritionDataService.getVaccines(dni);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/weight-height/{dni}")
    public ResponseEntity<List<Object>> getWeightHeight(@PathVariable String dni) {
        List<Object> data = nutritionDataService.getWeightHeight(dni);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/history/{dni}")
    public ResponseEntity<List<ReportRequestDto>> getReportHistory(@PathVariable String dni) {
        List<ReportRequestDto> reports = reportService.getReportsByDni(dni);
        return ResponseEntity.ok(reports);
    }
}