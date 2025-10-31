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

    public Long getId_testimonio() { return id_testimonio; }
    public void setId_testimonio(Long id_testimonio) { this.id_testimonio = id_testimonio; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public Boolean getVisible() { return visible; }
    public void setVisible(Boolean visible) { this.visible = visible; }

    @Override
    public String toString() {
        return nombre + " dijo: \"" + mensaje + "\"";
    }
}
