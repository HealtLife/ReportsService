package com.healthlife.reportsservice.application.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class NutritionDataService {
    private final WebClient webClient;

    public NutritionDataService() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8088/api/v1/nutrition")
                .build();
    }

    public Object getPersonalInfo(String dni) {
        return webClient.get()
                .uri("/personal-info/{dni}", dni)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    public List<Object> getMedicalNotes(String dni) {
        return webClient.get()
                .uri("/medical-note/{dni}", dni)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }

    public List<Object> getAllergies(String dni) {
        return webClient.get()
                .uri("/allergies/{dni}", dni)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }

    public List<Object> getPrescriptions(String dni) {
        return webClient.get()
                .uri("/prescription/{dni}", dni)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }

    public List<Object> getVaccines(String dni) {
        return webClient.get()
                .uri("/vaccine/{dni}", dni)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }

    public List<Object> getWeightHeight(String dni) {
        return webClient.get()
                .uri("/weightheight/{dni}", dni)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }

    public Map<String, Object> getAllMedicalData(String dni) {
        return Map.of(
                "personalInfo", getPersonalInfo(dni),
                "medicalNotes", getMedicalNotes(dni),
                "allergies", getAllergies(dni),
                "prescriptions", getPrescriptions(dni),
                "vaccines", getVaccines(dni),
                "weightHeight", getWeightHeight(dni)
        );
    }
}