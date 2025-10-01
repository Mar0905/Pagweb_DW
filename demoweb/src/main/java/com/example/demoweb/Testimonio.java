package com.example.demoweb;

public class Testimonio {
    private String nombre;
    private String comentario;

    public Testimonio() {}

    public Testimonio(String nombre, String comentario) {
        this.nombre = nombre;
        this.comentario = comentario;
    }

    // Getter y Setter de nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter de comentario
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
