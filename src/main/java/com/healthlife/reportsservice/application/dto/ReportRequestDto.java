package com.healthlife.reportsservice.application.dto;

import java.time.LocalDate;

public class ReportRequestDto {
    private String dni;
    private String reportType;
    private LocalDate requestDate;
    private String status;
    private String url;
    private String resumenJson;

    public ReportRequestDto() {
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