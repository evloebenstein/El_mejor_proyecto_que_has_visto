package com.mycompany.prueba;

import java.util.HashMap;
import java.util.Map;

// Cambiado a extends RegistroBase
public class ActividadFisica extends RegistroBase { 
    private int tiempo_semanal;
    private String deporte;
    private String actividad_fisica;
    
    public ActividadFisica(String nombre, String rut,String deporte, String actividad_fisica, int tiempo_semanal) {
        // Llamada al constructor padre
        super(nombre, rut); 
        this.deporte = deporte;
        this.actividad_fisica = actividad_fisica;
        this.tiempo_semanal = tiempo_semanal;
    }

    public String getActividadFisica() {
        return actividad_fisica;
    }

    public String getDeporte() {
        return deporte;
    }

    public int getTiempoSemanal() {
        return tiempo_semanal;
    }
    
    // getNombre() y getRut() ELIMINADOS, se heredan
    
    @Override
    public Map<String, String> getCampos() {
        Map<String, String> campos = new HashMap<>();
        campos.put("nombre", nombre);
        campos.put("rut", rut);
        campos.put("Deporte",deporte);
        campos.put("Otra actividad",actividad_fisica);
        campos.put("Tiempo Semanal",String.valueOf(tiempo_semanal) + " minutos");
        return campos;
    }
}