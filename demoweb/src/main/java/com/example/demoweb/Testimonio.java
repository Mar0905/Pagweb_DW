package com.example.demoweb;

public class Testimonio {
    private String color;
    private String marca;

    public Testimonio() {}

    public Testimonio(String color, String marca) {
        this.color = color;
        this.marca = marca;
    }

    // Getter y Setter de color
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Getter y Setter de marca
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}