package com.healthlife.reportsservice.application.dto;

import java.time.LocalDate;

public class MedicalNotesRequestDto {

    private String dni;
    private LocalDate fecha_nota;
    private String notes;
    private String autor;


    public MedicalNotesRequestDto() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFecha_nota() {
        return fecha_nota;
    }

    public void setFecha_nota(LocalDate fecha_nota) {
        this.fecha_nota = fecha_nota;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
