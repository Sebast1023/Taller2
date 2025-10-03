/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.modelo;

/**
 * La clase tiro nos dice el tipo de tiro con su puntaje
 *
 * @author mauri
 * @since 02/10/2025
 */
public class Tiro {

    private String nombre;
    private int puntos;
    /**
     * Contructor
     * @param nombre
     * @param puntos el puntaje que da el tiro
     */
    public Tiro(String nombre, int puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

}
