package com.mycompany.prueba;

import java.util.HashMap;
import java.util.Map;

// Cambiado a extends RegistroBase
public class Higiene extends RegistroBase {
    private int frecuenciaBaño;       // veces por semana
    private int lavadoManos;          // veces por día
    private int cepilladoDientes;     // veces por día
    private int cuidadoCabello;       // veces por semana
    private int cuidadoUñas;          // veces por mes
    private String estadoGeneral;     // buena, regular, deficiente
    
    
    public Higiene(String nombre, String rut, int frecuenciaBaño, int lavadoManos, int cepilladoDientes, int cuidadoCabello, int cuidadoUñas, String estadoGeneral) {
        // Llamada al constructor padre
        super(nombre, rut); 
        this.frecuenciaBaño = frecuenciaBaño;
        this.lavadoManos = lavadoManos;
        this.cepilladoDientes = cepilladoDientes;
        this.cuidadoCabello = cuidadoCabello;
        this.cuidadoUñas = cuidadoUñas;
        this.estadoGeneral = estadoGeneral;

    }


    public int getFrecuenciaBaño() {
        return frecuenciaBaño;
    }

    public int getLavadoManos() {
        return lavadoManos;
    }

    public int getCepilladoDientes() {
        return cepilladoDientes;
    }

    public int getCuidadoCabello() {
        return cuidadoCabello;
    }

    public int getCuidadoUñas() {
        return cuidadoUñas;
    }

    public String getEstadoGeneral() {
        return estadoGeneral;
    }

    // getNombre() y getRut() ELIMINADOS, se heredan

    @Override
    public Map<String, String> getCampos() {
        Map<String, String> campos = new HashMap<>();
        campos.put("nombre", nombre);
        campos.put("rut", rut);
        campos.put("frecuencia de aseo", String.valueOf(frecuenciaBaño)+" veces por semana");
        campos.put("lavado de manos",String.valueOf(lavadoManos)+" veces por día");
        campos.put("cepillado de dientes",String.valueOf(cepilladoDientes)+" veces por día");
        campos.put("cuidado de cabello",String.valueOf(cuidadoCabello)+" veces por semana");
        campos.put("cuidado de uñas",String.valueOf(cuidadoUñas)+" veces por mes");
        campos.put("estado general de higiene", estadoGeneral);
        return campos;
    }
}