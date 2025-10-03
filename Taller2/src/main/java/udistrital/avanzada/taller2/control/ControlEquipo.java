/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.control;

import java.util.ArrayList;
import java.util.Collections;
import udistrital.avanzada.taller2.modelo.Equipo;
import udistrital.avanzada.taller2.modelo.Jugador;

/**
 * Clase que gestiona los equipos y sus propiedades
 *
 * @author maucirio
 * @since 02/10/2025
 */
public class ControlEquipo {

    private ArrayList<Equipo> equipos;

    public ControlEquipo() {
        this.equipos = new ArrayList<Equipo>();
    }

    /**
     * Método que crear un equipo y devuelve referencia del objeto
     *
     * @param nombre
     * @return
     */
    public Equipo crearEquipo(String nombre) {
        Equipo aux = new Equipo();
        aux.setNombre(nombre);
        this.equipos.add(aux);
        return aux;
    }

    /**
     * Método para agregar un jugador a un equipo
     *
     * @param equipo equipo donde se agrega el jugador
     * @param jugador jugador a agregar
     */
    public void agregarJugador(Equipo equipo, Jugador jugador) {
        equipo.agregarJugador(jugador);
    }

    /**
     * Método para agregar puntos al equipo
     *
     * @param equipo
     * @param puntos
     */
    public void agregarPuntos(Equipo equipo, int puntos) {
        equipo.agregarPuntos(puntos);
    }

    /**
     * Metodo para obtener puntaje de un equipo
     *
     * @param equipo
     * @return
     */
    public int obtenerPuntaje(Equipo equipo) {
        return equipo.getPuntaje();
    }

    /**
     * Método para cambiar el orden de los equipos
     */
    public void cambiarOrdenEquipos() {
        Collections.shuffle(this.equipos);
    }
    /**
     * Método para obtener los jugadores de un equipo
     * @param equipo
     * @return 
     */
    public Jugador[] obtenerJugadores(Equipo equipo){
        return equipo.getJugadores();
    }
    /**
     * Método para obtener nombre del equipo
     * @param equipo
     * @return 
     */
    public String obtenerNombreEquipo(Equipo equipo){
        return equipo.getNombre();
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

}
