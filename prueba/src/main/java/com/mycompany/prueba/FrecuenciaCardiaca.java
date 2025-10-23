package com.mycompany.prueba;

import java.util.HashMap;
import java.util.Map;

// Cambiado a extends RegistroBase
public class FrecuenciaCardiaca extends RegistroBase {
    private int frecuencia;
    private int duracion;
    private int presionArterial;
    private String latidosIrregulares;
    private String fecha;

    public FrecuenciaCardiaca(String nombre, String rut, int frecuencia, int duracion, int presionArterial,
                              String latidosIrregulares, String fecha) {
        // Llamada al constructor padre
        super(nombre, rut); 
        
        this.frecuencia = frecuencia;
        this.duracion = duracion;
        this.presionArterial = presionArterial;
        this.latidosIrregulares = latidosIrregulares;
        this.fecha = fecha;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getPresionArterial() {
        return presionArterial;
    }

    public String getLatidosIrregulares() {
        return latidosIrregulares;
    }

    public String getFecha() {
        return fecha;
    }

    // getNombre() y getRut() ELIMINADOS, se heredan

    @Override
    public Map<String, String> getCampos() {
        Map<String, String> campos = new HashMap<>();
        campos.put("Nombre", nombre);
        campos.put("RUT", rut);
        campos.put("Fecha", fecha);
        campos.put("Frecuencia Cardiaca", String.valueOf(frecuencia) + " lpm");
        campos.put("Duración", String.valueOf(duracion) + " minutos");
        campos.put("Presión Arterial (Sistólica)", String.valueOf(presionArterial) + " mmHg");
        campos.put("Latidos Irregulares", latidosIrregulares);
        return campos;
    }
}