package com.mycompany.prueba;

import java.util.HashMap;
import java.util.Map;

// Cambiado a extends RegistroBase
public class Antecedentes extends RegistroBase { 
    private String fumador;
    private String medicacion;
    private String ConsumoDeAlcohol;
    private String ConsumoDeDrogas;
    private String ProblemasCardiovascularesAnteriores;

    public Antecedentes(String nombre, String rut,String fumador, String medicacion,String ConsumoDeAlcohol,String ConsumoDeDrogas,String ProblemasCardiovascularesAnteriores) {
        // Llamada al constructor padre
        super(nombre, rut); 
        this.fumador =fumador;
        this.medicacion = medicacion;
        this.ConsumoDeAlcohol = ConsumoDeAlcohol;
        this.ConsumoDeDrogas = ConsumoDeDrogas;
        this.ProblemasCardiovascularesAnteriores = ProblemasCardiovascularesAnteriores;
    }

    // getNombre() y getRut() ELIMINADOS, se heredan

    @Override
    public Map<String, String> getCampos() {
        Map<String, String> campos = new HashMap<>();
        campos.put("Nombre", nombre);
        campos.put("RUT", rut);
        campos.put("Fumador",fumador);
        campos.put("Consumo de Alcohol", ConsumoDeAlcohol);
        campos.put("Consumo de Drogas", ConsumoDeDrogas);
        campos.put("Medicacion", medicacion);
        campos.put("Problemas Cardiovasculares Anteriores", ProblemasCardiovascularesAnteriores);
        return campos;
    }
}