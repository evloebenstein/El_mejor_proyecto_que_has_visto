package com.mycompany.prueba;

/**
 * Clase modelo genérica y reutilizable para estructurar los datos de una correlación
 * entre dos variables (un eje X y un eje Y).
 */
public class ReporteCorrelacion {
    
    private String nombre;
    private String rut;
    private String tipoAnalisis;             // Describe la correlación, ej: "Sedentarismo vs. Frecuencia Cardíaca"
    
    // Datos del Eje X (variable independiente)
    private String nombreEjeX;               // ej: "Actividad Semanal"
    private double valorEjeX;                // ej: 150
    private String unidadEjeX;               // ej: "minutos"

    // Datos del Eje Y (variable dependiente)
    private String nombreEjeY;               // ej: "Frecuencia Cardíaca" o "IMC"
    private double valorEjeY;                // ej: 85.0 o 27.5
    private String unidadEjeY;               // ej: "lpm" o "kg/m²"

    private String analisis;                 // Conclusión textual del análisis

    public ReporteCorrelacion(String nombre, String rut, String tipoAnalisis,
                              String nombreEjeX, double valorEjeX, String unidadEjeX,
                              String nombreEjeY, double valorEjeY, String unidadEjeY,
                              String analisis) {
        this.nombre = nombre;
        this.rut = rut;
        this.tipoAnalisis = tipoAnalisis;
        this.nombreEjeX = nombreEjeX;
        this.valorEjeX = valorEjeX;
        this.unidadEjeX = unidadEjeX;
        this.nombreEjeY = nombreEjeY;
        this.valorEjeY = valorEjeY;
        this.unidadEjeY = unidadEjeY;
        this.analisis = analisis;
    }

    // Getters para la serialización a JSON
    public String getNombre() { return nombre; }
    public String getRut() { return rut; }
    public String getTipoAnalisis() { return tipoAnalisis; }
    public String getNombreEjeX() { return nombreEjeX; }
    public double getValorEjeX() { return valorEjeX; }
    public String getUnidadEjeX() { return unidadEjeX; }
    public String getNombreEjeY() { return nombreEjeY; }
    public double getValorEjeY() { return valorEjeY; }
    public String getUnidadEjeY() { return unidadEjeY; }
    public String getAnalisis() { return analisis; }
}