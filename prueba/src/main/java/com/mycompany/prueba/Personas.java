package com.mycompany.prueba;

import java.util.HashMap;
import java.util.Map;

// Cambiado a extends RegistroBase
public class Personas extends RegistroBase {
    private int edad;
    private float peso;
    private float altura;
    private String genero;
    private String nacionalidad;

    public Personas(String nombre, int edad, String rut, float peso, float altura, String genero, String nacionalidad) {
        // Llamada al constructor padre
        super(nombre, rut); 
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.genero = genero;
        this.nacionalidad = nacionalidad;
    }

    // getNombre() y getRut() ELIMINADOS, se heredan

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
        campos.put("Peso", String.valueOf(peso) + " kg");
        campos.put("Altura", String.valueOf(altura) + " m");
        campos.put("Género", genero);
        campos.put("Nacionalidad", nacionalidad);
        return campos;
    }
}