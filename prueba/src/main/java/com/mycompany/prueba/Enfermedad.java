package com.mycompany.prueba;

import java.util.HashMap;
import java.util.Map;

// Cambiado a extends RegistroBase
public class Enfermedad extends RegistroBase {
    private String nombre_enfermedad;
    private String sintomas_fisicos;
    private String sintomas_psicologicos;
    private String contagiosa;
    private String hereditaria;
    
    public Enfermedad(String nombre, String rut, String nombre_enfermedad, String sintomas_fisicos, String sintomas_psicologicos, String contagiosa, String hereditaria) {
        // Llamada al constructor padre
        super(nombre, rut); 
        this.nombre_enfermedad = nombre_enfermedad;
        this.sintomas_fisicos = sintomas_fisicos;
        this.sintomas_psicologicos = sintomas_psicologicos;
        this.contagiosa = contagiosa;
        this.hereditaria = hereditaria;
    }
    
    public String getNombreEnfermedad()  {
        return nombre_enfermedad;
    }
    
    public String getSintomasFisicos()  {
        return sintomas_fisicos;
    }
    
    public String getSintomasPsicologicos()  {
        return sintomas_psicologicos;
    }
    
    public String getContagiosa()  {
        return contagiosa;
    }
    
    public String getHereditaria()  {
        return hereditaria;
    }
    
    // getNombre() y getRut() ELIMINADOS, se heredan

    @Override
    public Map<String, String> getCampos() {
        Map<String, String> campos = new HashMap<>();
        campos.put("nombre", nombre);
        campos.put("rut", rut);
        campos.put("Nombre de la Enfermedad", nombre_enfermedad);
        campos.put("Síntomas Físicos", sintomas_fisicos);
        campos.put("Síntomas Psicológicos", sintomas_psicologicos);
        campos.put("Contagiosa", contagiosa);
        campos.put("Hereditaria", hereditaria);
        return campos;
    }
}