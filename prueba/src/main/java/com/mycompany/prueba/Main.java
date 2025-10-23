package com.mycompany.prueba;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        final String CARPETA_DATOS = "C:\\Users\\evloe\\Downloads\\output\\";
        final String ARCHIVO_JSON_SALIDA = CARPETA_DATOS + "reporte_optimizado.json";

        // 1. Cargar datos
        List<Personas> personas = cargarPersonas(CARPETA_DATOS + "persona.txt");
        Map<String, List<Registro>> registrosExtras = cargarRegistrosExtras(CARPETA_DATOS);
        
        // 2. Crear la lista principal que contendrá los reportes.
        //    Será una Lista de Mapas, donde cada Mapa es el reporte de una persona.
        List<Map<String, Object>> reportesFinales = new ArrayList<>();
        
        // 3. Definir umbrales
        final int UMBRAL_FCR_ALTA = 80;
        final int UMBRAL_ACTIVO = 150;

        // 4. Iterar sobre cada persona para construir su reporte
        for (Personas p : personas) {
            int tiempoActividadSemanal = obtenerActividadSemanal(registrosExtras, p.getRut());

            // Crear el Mapa principal para la persona con sus datos comunes
            Map<String, Object> reportePersonaMap = new LinkedHashMap<>(); // Usamos LinkedHashMap para mantener el orden
            reportePersonaMap.put("nombre", p.getNombre());
            reportePersonaMap.put("rut", p.getRut());
            reportePersonaMap.put("actividadSemanalMinutos", tiempoActividadSemanal);

            // Crear una Lista para los análisis detallados
            List<Map<String, Object>> analisisDetalladosList = new ArrayList<>();

            // Análisis 1: Frecuencia Cardíaca
            int frecuenciaCardiaca = obtenerFrecuenciaCardiaca(registrosExtras, p.getRut());
            if (frecuenciaCardiaca != -1) {
                Map<String, Object> detalleFC = new LinkedHashMap<>();
                boolean esSedentario = tiempoActividadSemanal < UMBRAL_ACTIVO;
                boolean fcrAlta = frecuenciaCardiaca > UMBRAL_FCR_ALTA;
                String conclusion = (esSedentario && fcrAlta) ? "ALTO RIESGO: Sedentario y FCR Alta." : 
                                    (!esSedentario && !fcrAlta) ? "BAJO RIESGO: Activo y FCR Normal." : "CASO ATÍPICO.";
                
                detalleFC.put("tipoAnalisis", "Frecuencia Cardíaca");
                detalleFC.put("valor", frecuenciaCardiaca);
                detalleFC.put("unidad", "lpm");
                detalleFC.put("conclusion", conclusion);
                analisisDetalladosList.add(detalleFC);
            }

            // Análisis 2: Peso (IMC)
            if (p.getAltura() > 0) {
                Map<String, Object> detalleIMC = new LinkedHashMap<>();
                double imc = p.getPeso() / (p.getAltura() * p.getAltura());
                String clasificacionIMC = (imc < 25) ? "Peso Normal/Bajo" : (imc < 30) ? "Sobrepeso" : "Obesidad";
                boolean esSedentario = tiempoActividadSemanal < UMBRAL_ACTIVO;
                boolean tieneSobrepesoOObesidad = imc >= 25;
                String conclusion = (esSedentario && tieneSobrepesoOObesidad) ? "ALTO RIESGO: Sedentarismo y " + clasificacionIMC + "." :
                                    (!esSedentario && !tieneSobrepesoOObesidad) ? "BAJO RIESGO: Activo y Peso Normal." : "CASO ATÍPICO.";

                detalleIMC.put("tipoAnalisis", "IMC (" + clasificacionIMC + ")");
                detalleIMC.put("valor", Math.round(imc * 100.0) / 100.0); // Redondeamos a 2 decimales
                detalleIMC.put("unidad", "kg/m²");
                detalleIMC.put("conclusion", conclusion);
                analisisDetalladosList.add(detalleIMC);
            }
            
            // Añadir la lista de análisis al mapa principal de la persona
            reportePersonaMap.put("analisisDetallados", analisisDetalladosList);
            
            // Añadir el reporte completo de esta persona a la lista final
            reportesFinales.add(reportePersonaMap);
        }
        
        // 5. EXPORTAR la lista de mapas a un único archivo JSON
        exportarAJson(reportesFinales, ARCHIVO_JSON_SALIDA);

        System.out.println("\n===================================================================");
        System.out.println("Proceso completado. Reporte optimizado generado en:");
        System.out.println(ARCHIVO_JSON_SALIDA);
        System.out.println("===================================================================");
    }
    
    // --- MÉTODOS COMPLETOS PARA OBTENER Y CARGAR DATOS ---

    private static Map<String, List<Registro>> cargarRegistrosExtras(String carpeta) {
        Map<String, List<Registro>> registros = new LinkedHashMap<>();
        cargarFrecuenciaCardiaca(carpeta + "frecuencia_cardiaca.txt", registros);
        cargarAntecedentes(carpeta + "antecedentes.txt", registros);
        cargarSedentarismo(carpeta + "sedentarismo.txt", carpeta + "actividad_fisica.txt", registros);
        return registros;
    }
    
    private static int obtenerFrecuenciaCardiaca(Map<String, List<Registro>> registros, String rut) {
        return registros.getOrDefault(rut, Collections.emptyList()).stream()
            .filter(r -> r instanceof FrecuenciaCardiaca).mapToInt(r -> ((FrecuenciaCardiaca) r).getFrecuencia())
            .findFirst().orElse(-1);
    }

    private static int obtenerActividadSemanal(Map<String, List<Registro>> registros, String rut) {
        return registros.getOrDefault(rut, Collections.emptyList()).stream()
            .filter(r -> r instanceof Sedentarismo).mapToInt(r -> {
                ActividadFisica af = ((Sedentarismo) r).getActividadFisica();
                return af != null ? af.getTiempoSemanal() : 0;
            }).findFirst().orElse(0);
    }
    
    private static List<Personas> cargarPersonas(String ruta) {
        Archivo archivo = new Archivo(ruta);
        List<Personas> personas = archivo.mapFila(fila -> {
            if (fila.length >= 7 && !fila[0].equalsIgnoreCase("Nombre")) {
                return new Personas(fila[0], Integer.parseInt(fila[4]), fila[1],
                        Float.parseFloat(fila[6]), Float.parseFloat(fila[5]), fila[3], fila[2]);
            }
            return null;
        });
        personas.removeIf(Objects::isNull);
        return personas;
    }
    
    private static void exportarAJson(Object data, String rutaArchivo) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }

    private static void cargarFrecuenciaCardiaca(String rutaArchivo, Map<String, List<Registro>> registros) {
        new Archivo(rutaArchivo).mapFila(fila -> {
            if (fila.length >= 7 && !fila[0].equalsIgnoreCase("Nombre")) {
                FrecuenciaCardiaca fc = new FrecuenciaCardiaca(
                        fila[0], fila[1], Integer.parseInt(fila[2]), Integer.parseInt(fila[3]),
                        Integer.parseInt(fila[4]), fila[5], fila[6]);
                registros.computeIfAbsent(fc.getRut(), k -> new ArrayList<>()).add(fc);
            }
            return null;
        });
    }

    private static void cargarAntecedentes(String rutaArchivo, Map<String, List<Registro>> registros) {
        new Archivo(rutaArchivo).mapFila(fila -> {
            if (fila.length >= 7 && !fila[0].equalsIgnoreCase("Nombre")) {
                Antecedentes a = new Antecedentes(
                        fila[0], fila[1], fila[2], fila[3], fila[4], fila[5], fila[6]);
                registros.computeIfAbsent(a.getRut(), k -> new ArrayList<>()).add(a);
            }
            return null;
        });
    }

    private static void cargarSedentarismo(String rutaArchivo, String rutaActividadFisica, Map<String, List<Registro>> registros) {
        new Archivo(rutaArchivo).mapFila(fila -> {
            if (fila.length >= 5 && !fila[0].equalsIgnoreCase("Nombre")) {
                Sedentarismo s = new Sedentarismo(
                        rutaActividadFisica, fila[0], fila[1], fila[2],
                        Float.parseFloat(fila[4]), fila[3]);
                registros.computeIfAbsent(s.getRut(), k -> new ArrayList<>()).add(s);
            }
            return null;
        });
    }
}