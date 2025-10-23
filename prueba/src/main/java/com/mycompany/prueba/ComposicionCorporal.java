package com.mycompany.prueba;

import java.util.HashMap;
import java.util.Map;

// Cambiado a extends RegistroBase
public class ComposicionCorporal extends RegistroBase {
    private float imc; //indice de masa corporal
    private float whr; //indice cintura cadera
    private float ict; //indice cintura estatura
    private float iac; //indice de adiposidad corporal
    private float pgc; //indice de grasa corporal
    private float peso;
    private final float mmc;
    
    public ComposicionCorporal(String nombre, String rut, float imc, float whr, float ict, float iac, float pgc, float peso) {
        // Llamada al constructor padre
        super(nombre, rut); 
        this.imc = imc;
        this.whr = whr;
        this.ict = ict;
        this.iac = iac;
        this.pgc = pgc;
        this.mmc = peso-((peso*pgc)/100);
    }


    public float getImc() {
        return imc;
    }

    public float getWhr() {
        return whr;
    }

    public float getIct() {
        return ict;
    }

    public float getIac() {
        return iac;
    }

    public float getPgc() {
        return pgc;
    }
    
    public float getPeso() {
        return peso;
    }
    
    public float getMmc() {
        return mmc;
    }
    // getNombre() y getRut() ELIMINADOS, se heredan

    @Override
    public Map<String, String> getCampos() {
        Map<String, String> campos = new HashMap<>();
        campos.put("nombre", nombre);
        campos.put("rut", rut);
        campos.put("peso", String.valueOf(peso));
        campos.put("IMC",String.valueOf(imc));
        campos.put("Índice Cintura Cadera (WHR)",String.valueOf(whr));
        campos.put("Índice Cintura Estatura (ICT)",String.valueOf(ict));
        campos.put("Índice Adiposidad Corporal (IAC)",String.valueOf(iac));
        campos.put("Porcentaje Grasa Corporal (PGC)",String.valueOf(pgc) + " %");
        campos.put("Porcentaje de Grasa Corporal (MMC)",String.valueOf(mmc));
        return campos;
    }
}