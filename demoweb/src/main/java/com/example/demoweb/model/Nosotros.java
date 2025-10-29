package com.example.demoweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nosotros")
public class Nosotros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vision;
    private String mision;

    // ===== Constructores =====
    public Nosotros() {}

    public Nosotros(String vision, String mision) {
        this.vision = vision;
        this.mision = mision;
    }

    // ===== Getters y Setters =====
    public Long getId() {
        return id;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }
}
