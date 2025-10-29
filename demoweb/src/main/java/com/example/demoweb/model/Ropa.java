package com.example.demoweb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ropa")
public class Ropa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;   // Ejemplo: "GET1 - Polo", "POST2 - Zapato"
    private String color;
    private String marca;

    public Ropa() {}

    public Ropa(String tipo, String color, String marca) {
        this.tipo = tipo;
        this.color = color;
        this.marca = marca;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
}
