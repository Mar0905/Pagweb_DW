package com.example.demoweb;

public class Dato {
    private String tipo;   // GET1, GET2, POST1, POST2
    private String valor;

    public Dato() {}

    public Dato(String tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
