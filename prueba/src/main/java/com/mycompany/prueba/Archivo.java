package com.mycompany.prueba;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Archivo {

    private String ruta;
    private List<String[]> datos;
    private boolean tieneCabecera;

    public Archivo(String ruta) { this(ruta, true); }

    public Archivo(String ruta, boolean tieneCabecera) {
        this.ruta = ruta;
        this.datos = new ArrayList<>();
        this.tieneCabecera = tieneCabecera;
        cargarDatos();
    }

    private void cargarDatos() {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            boolean primeraFila = true;
            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.trim().split("\\s+");
                if (primeraFila && tieneCabecera) {
                    primeraFila = false;
                    continue;
                }
                datos.add(columnas);
                primeraFila = false;
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
    }

    public List<String[]> getFilas() { return datos; }

    public <T> List<T> mapFila(Function<String[], T> mapper) {
        List<T> lista = new ArrayList<>();
        for (String[] fila : datos) {
            T obj = mapper.apply(fila);
            if (obj != null) lista.add(obj);
        }
        return lista;
    }
}
