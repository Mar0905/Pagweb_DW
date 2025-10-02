package com.example.demoweb;

public class Testimonio2 {
	private String nombre;
    private String mensaje;

    public Testimonio2() {}

    public Testimonio2(String nombre, String mensaje) {
        this.nombre = nombre;
        this.mensaje = mensaje;
    }

    // Getter y Setter de nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter de mensaje
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
