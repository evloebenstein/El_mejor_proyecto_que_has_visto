package com.mycompany.prueba;

import java.util.HashMap;
import java.util.Map;

public class Persona implements Registro {
    private String nombre;
    private int edad;
    private String rut;
    private float peso;
    private float altura;
    private String genero;
    private String nacionalidad;

    public Persona(String nombre, int edad, String rut, float peso, float altura, String genero, String nacionalidad) {
        this.nombre = nombre;
        this.edad = edad;
        this.rut = rut;
        this.peso = peso;
        this.altura = altura;
        this.genero = genero;
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String getNombre() { return nombre; }

    @Override
    public String getRut() { return rut; }

    public int getEdad() {
        return edad;
    }

    public float getPeso() {
        return peso;
    }

    public float getAltura() {
        return altura;
    }

    public String getGenero() {
        return genero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    @Override
    public Map<String, String> getCampos() {
        Map<String, String> campos = new HashMap<>();
        campos.put("Nombre", nombre);
        campos.put("RUT", rut);
        campos.put("Edad", String.valueOf(edad));
        campos.put("Genero", genero);
        campos.put("Altura", String.valueOf(altura) + " m");
        campos.put("Peso", String.valueOf(peso) + " kg");
        campos.put("Nacionalidad", nacionalidad);
        return campos;
    }
}
