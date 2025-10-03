package udistrital.avanzada.taller2.modelo;

import java.io.Serializable;

/**
 * Clase equipo que es la base para el juego por equipos. Tienen 4 jugadores en
 * un array primitivo.
 *
 * @author Mauricio
 * @version 1.0
 * @since 01/10/2025
 */
public class Equipo implements Serializable {

    private String nombre;
    private Jugador[] jugadores;
    // Puntaje total de todos los jugadores del equipo    
    // No es un atributo serializable
    private transient int puntaje;
    // Nos ayuda a saber cuantos elementos null hay en el array de jugadores
    // No es un atributo serializable
    private transient int posicion;

    //Constructor
    public Equipo() {
        this.nombre = "";
        this.puntaje = 0;
        // Se inicializan los jugadores configurando a 4 jugadores por equipo
        this.jugadores = new Jugador[4];
        this.posicion = 0;
    }

    /**
     * Metodo para agregar jugador al array de jugadores
     *
     * @param jugador
     */
    public void agregarJugador(Jugador jugador) {
        // No se agrega jugador si ya hay 4
        if (posicion == jugadores.length - 1) {
            return;
        }
        // Agregar en la posicion actual vacia
        jugadores[posicion] = jugador;
        posicion++;
    }
    
    public void agregarPuntos(int puntos){
        this.puntaje += puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public int getPosicion() {
        return posicion;
    }

}
