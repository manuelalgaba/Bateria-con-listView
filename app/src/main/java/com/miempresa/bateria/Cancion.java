package com.miempresa.bateria;

public class Cancion {
    private String nombre;
    private int idSonido;

    public Cancion(String nombre, int idSonido) {
        this.nombre = nombre;
        this.idSonido = idSonido;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdSonido() {
        return idSonido;
    }
}