package com.example.demoweb;

public class Usuario {
    
    private String username;
    private String password;
    private String rol; // ADMINISTRADOR o CLIENTE
    private String nombreCompleto;
    
    public Usuario() {
    }
    
    public Usuario(String username, String password, String rol, String nombreCompleto) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.nombreCompleto = nombreCompleto;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRol() {
        return rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", rol='" + rol + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                '}';
    }
}
