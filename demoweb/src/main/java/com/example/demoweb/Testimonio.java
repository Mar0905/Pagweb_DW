package com.example.demoweb;

public class Testimonio {
    private String nombre;
    private String mensaje;

    // Constructor vacío
    public Testimonio() {}

    public Testimonio(String nombre, String mensaje) {
        this.nombre = nombre;
        this.mensaje = mensaje;
    }

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
