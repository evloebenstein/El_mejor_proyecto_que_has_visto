package com.mycompany.prueba;

import java.util.Map;

/**
 * Clase base abstracta que centraliza la gestión de los atributos comunes 'nombre' y 'rut'
 * para todas las clases que implementan la interfaz Registro.
 */
public abstract class RegistroBase implements Registro {
    
    // Atributos protegidos para que las clases hijas puedan acceder a ellos
    protected String nombre;
    protected String rut;

    // Constructor que inicializa los atributos comunes
    public RegistroBase(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
    }

    // Implementación final y heredable de los métodos de la interfaz Registro
    @Override
    public final String getNombre() {
        return nombre;
    }

    @Override
    public final String getRut() {
        return rut;
    }
    
    
    // Este método queda abstracto, obligando a las clases hijas a implementar sus campos específicos
    @Override
    public abstract Map<String, String> getCampos();
}