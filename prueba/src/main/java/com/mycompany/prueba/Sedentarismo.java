package com.mycompany.prueba;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Objects;

// Cambiado a extends RegistroBase
public class Sedentarismo extends RegistroBase {
    private String tipoDeTrabajo;
    private Float horasDeSueno;
    private String estres;
    private ActividadFisica actividadFisica; 

    public Sedentarismo(String archivoActividad, String nombre, String rut,
                        String tipoDeTrabajo, Float horasDeSueno, String estres) {
        // Llamada al constructor padre
        super(nombre, rut); 
        this.tipoDeTrabajo = tipoDeTrabajo;
        this.horasDeSueno = horasDeSueno;
        this.estres = estres;
        this.actividadFisica = cargarActividad(archivoActividad);
    }

    private ActividadFisica cargarActividad(String ruta) {
        Archivo archivo = new Archivo(ruta);
        return archivo.mapFila(fila -> {
            // Se utiliza this.rut que es heredado y protected
            if (fila.length >= 5 && fila[1].equals(this.rut)) { 
                return new ActividadFisica(
                        fila[0], // nombre
                        fila[1], // rut
                        fila[2], // deporte
                        fila[3], // actividad que no sea deporte
                        Integer.parseInt(fila[4]) // tiempo total semanal
                );
            }
            return null;
        }).stream().filter(a -> a != null).findFirst().orElse(null);
    }

    // getNombre() y getRut() ELIMINADOS, se heredan

    @Override
    public Map<String, String> getCampos() {
        Map<String, String> campos = new HashMap<>();
        campos.put("nombre", nombre);
        campos.put("rut", rut);
        campos.put("Trabajo", tipoDeTrabajo);
        campos.put("Horas de Sueño", String.valueOf(horasDeSueno));
        campos.put("Estres", estres);

        if (actividadFisica != null) {
            campos.put("Actividad física", actividadFisica.getNombre());
            campos.put("Deporte", actividadFisica.getDeporte());
            campos.put("Tiempo Semanal Actividad", String.valueOf(actividadFisica.getTiempoSemanal()) + " minutos");
        }
        return campos;
    }

    public ActividadFisica getActividadFisica(){
        return actividadFisica;
    }
}