package com.healthlife.reportsservice.domain.models.agregates;


import jakarta.persistence.*;

@Entity
@Table(name = "reportes")
public class Reportes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userDni;
    private String titulo;
    private String fecha_creacion;
    private String url; //ubicacion del archivo
    private String estado; //binario o pdf

    public Reportes() {
    }

    public Reportes(String titulo) {
        this.titulo = titulo;

    }

    public Reportes(String userDni, String titulo, String fecha_creacion, String url, String estado) {
        this.userDni = userDni;
        this.titulo = titulo;
        this.fecha_creacion = fecha_creacion;
        this.url = url;
        this.estado = estado;
    }

    public String getUserDni() {
        return userDni;
    }

    public void setUserDni(String userDni) {
        this.userDni = userDni;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
