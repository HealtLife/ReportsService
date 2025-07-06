package com.healthlife.reportsservice.interfaces.rest;

import com.healthlife.reportsservice.application.dto.CreateReportRequestDto;
import com.healthlife.reportsservice.application.dto.ReportDto;
import com.healthlife.reportsservice.application.services.ReportService;
import com.healthlife.reportsservice.domain.models.agregates.Reportes;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<Reportes> generarReporte(@RequestBody CreateReportRequestDto dto) {
        System.out.println("DNI: " + dto.getUserDni());
        Reportes reporte = reportService.crearReporte(dto);
        return ResponseEntity.ok(reporte);
    }
    @GetMapping("/descargar")
    public ResponseEntity<Resource> descargarReporte(@RequestParam String filename) {
        try {
            Path filePath = Paths.get("src/main/resources/pdfs").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = Files.probeContentType(filePath);
            if (contentType == null) contentType = "application/pdf";

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/usuario/{dni}")
    public ResponseEntity<List<ReportDto>> obtenerReportesPorUsuario(@PathVariable("dni") String dni) {
        List<ReportDto> reportes = reportService.obtenerReportesPorUsuario(dni);
        if (reportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reportes);
    }

}
