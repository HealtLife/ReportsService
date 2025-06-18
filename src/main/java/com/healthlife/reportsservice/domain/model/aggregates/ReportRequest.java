package com.healthlife.reportsservice.domain.model.aggregates;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "report_requests")
public class ReportRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;

    @Column(name = "report_type")
    private String reportType;

    @Column(name = "request_date")
    private LocalDate requestDate;

    private String status;

    private String url;

    private String resumenJson;

    public ReportRequest(String dni, String reportType, LocalDate requestDate, String status, String url, String resumenJson) {}

    public ReportRequest(Long id, String dni, String reportType, LocalDate requestDate, String status, String url, String resumenJson) {
        this.id = id;
        this.dni = dni;
        this.reportType = reportType;
        this.requestDate = requestDate;
        this.status = status;
        this.url = url;
        this.resumenJson = resumenJson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResumenJson() {
        return resumenJson;
    }

    public void setResumenJson(String resumenJson) {
        this.resumenJson = resumenJson;
    }
}