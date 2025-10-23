package com.mycompany.prueba;

import java.util.HashMap;
import java.util.Map;

// Cambiado a extends RegistroBase
public class Alimentacion extends RegistroBase {
    private int calorias;
    private float proteinas;
    private float grasas;
    private float carbohidratos;
    
    public Alimentacion(String nombre, String rut, int calorias, float proteinas, float grasas, float carbohidratos) {
        // Llamada al constructor padre
        super(nombre, rut); 
        this.calorias = calorias;
        this.proteinas = proteinas;
        this.grasas = grasas;
        this.carbohidratos = carbohidratos;
    }

    public int getCalorias() {
        return calorias;
    }

    public float getProteinas() {
        return proteinas;
    }

    public float getGrasas() {
        return grasas;
    }

    public float getCarbohidratos() {
        return carbohidratos;
    }
    
    // getNombre() y getRut() ELIMINADOS, se heredan

    @Override
    public Map<String, String> getCampos() {
        Map<String, String> campos = new HashMap<>();
        campos.put("nombre", nombre);
        campos.put("rut", rut);
        campos.put("Kcalorias", String.valueOf(calorias));
        campos.put("Proteínas", String.valueOf(proteinas) + " g");
        campos.put("Grasas", String.valueOf(grasas) + " g");
        campos.put("Carbohidratos", String.valueOf(carbohidratos) + " g");
        return campos;
    }
}