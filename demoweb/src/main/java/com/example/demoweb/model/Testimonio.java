package com.example.demoweb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "testimonios")
public class Testimonio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_testimonio;

    private String nombre;
    private String mensaje;
    private Boolean visible;

    // Getters
    public String getNombre() { return nombre; }
    public String getMensaje() { return mensaje; }

    @Override
    public String toString() {
        return nombre + " dijo: \"" + mensaje + "\"";
    }
}