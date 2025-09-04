package com.example.demoweb;

// Clase modelo simple para representar un testimonio
public class Testimonio {
    private String nombre;
    private String mensaje;

    // Constructor vacío (necesario para Spring y formularios)
    public Testimonio() {}

    // Constructor con parámetros
    public Testimonio(String nombre, String mensaje) {
        this.nombre = nombre;
        this.mensaje = mensaje;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
