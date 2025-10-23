package com.mycompany.prueba;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EstadoDeSalud {

    private ComposicionCorporal composicion;
    private Alimentacion alimentacion;
    private Higiene higiene;
    private Personas persona; // Este campo ahora se inicializará correctamente


    public EstadoDeSalud(String archivoComposicion, String archivoAlimentacion, String archivoHigiene, Personas persona) {
        this.persona = persona; // Se asigna el objeto Persona recibido
        
        // Se obtiene el RUT directamente del objeto Persona para asegurar consistencia
        String rutPersona = persona.getRut(); 

        this.composicion = cargarComposicion(archivoComposicion, rutPersona);
        this.alimentacion = cargarAlimentacion(archivoAlimentacion, rutPersona);
        this.higiene = cargarHigiene(archivoHigiene, rutPersona);
    }

    private ComposicionCorporal cargarComposicion(String ruta, String rut) {
        Archivo archivo = new Archivo(ruta);
        List<ComposicionCorporal> lista = archivo.mapFila(fila -> {
            if(fila.length >= 7 && !fila[0].equalsIgnoreCase("Nombre") && fila[1].equals(rut)){
                return new ComposicionCorporal(
                        fila[0], fila[1],
                        Float.parseFloat(fila[2]),
                        Float.parseFloat(fila[3]),
                        Float.parseFloat(fila[4]),
                        Float.parseFloat(fila[5]),
                        Float.parseFloat(fila[6]),
                        this.persona.getPeso() // <-- CORREGIDO: Ahora 'persona' no es null
                );
            }
            return null;
        });
        lista.removeIf(Objects::isNull);
        return lista.isEmpty() ? null : lista.get(0);
    }

    private Alimentacion cargarAlimentacion(String ruta, String rut) {
        Archivo archivo = new Archivo(ruta);
        List<Alimentacion> lista = archivo.mapFila(fila -> {
            if(fila.length >= 6 && !fila[0].equalsIgnoreCase("Nombre") && fila[1].equals(rut)){
                return new Alimentacion(
                        fila[0], fila[1],
                        Integer.parseInt(fila[2]),
                        Float.parseFloat(fila[3]),
                        Float.parseFloat(fila[4]),
                        Float.parseFloat(fila[5])
                );
            }
            return null;
        });
        lista.removeIf(Objects::isNull);
        return lista.isEmpty() ? null : lista.get(0);
    }

    private Higiene cargarHigiene(String ruta, String rut) {
        Archivo archivo = new Archivo(ruta);
        List<Higiene> lista = archivo.mapFila(fila -> {
            if(fila.length >= 8 && !fila[0].equalsIgnoreCase("Nombre") && fila[1].equals(rut)){
                return new Higiene(
                        fila[0], fila[1],
                        Integer.parseInt(fila[2]),
                        Integer.parseInt(fila[3]),
                        Integer.parseInt(fila[4]),
                        Integer.parseInt(fila[5]),
                        Integer.parseInt(fila[6]),
                        fila[7]
                );
            }
            return null;
        });
        lista.removeIf(Objects::isNull);
        return lista.isEmpty() ? null : lista.get(0);
    }

    public Map<String,String> getEstadoCompleto() {
        Map<String,String> estado = new LinkedHashMap<>();

        // Se puede obtener el nombre y rut directamente del objeto persona
        estado.put("Nombre", this.persona.getNombre());
        estado.put("RUT", this.persona.getRut());

        addCampos(estado, composicion);
        addCampos(estado, alimentacion);
        addCampos(estado, higiene);

        return estado;
    }

    private void addCampos(Map<String,String> estado, Registro registro){
        if(registro != null){
            registro.getCampos().forEach((k,v) -> {
                if(!k.equalsIgnoreCase("nombre") && !k.equalsIgnoreCase("rut")){
                    estado.put(k,v);
                }
            });
        }
    }

    public void mostrarEstado() {
        getEstadoCompleto().forEach((k,v) -> System.out.println(k + ": " + v));
    }
}