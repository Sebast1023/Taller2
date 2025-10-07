package udistrital.avanzada.taller2.modelo;

import java.io.Serializable;
import java.util.Arrays;

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
        if (posicion == jugadores.length+1) {
            return;
        }
        // Agregar en la posicion actual vacia
        jugadores[posicion] = jugador;
        posicion++;
    }

    public int agregarPuntos(int puntos) {
        this.puntaje += puntos;
        return puntaje;
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
        return Arrays.copyOf(jugadores, jugadores.length);
    }

    public int getPosicion() {
        return posicion;
    }

    /**
     * Metodo para retornar un jugador del vector de jugadores
     *
     * @param indice
     * @return Jugador si no null
     */
    public Jugador getJugador(int indice) {
        if (indice > jugadores.length || indice < 0) {
            return null;
        }
        return jugadores[indice];
    }

    /**
     * Metodo para retornar nombre de un jugador
     *
     * @param indice
     * @return nombre si no null
     */
    public String getNombreJugador(int indice) {
        if (indice > jugadores.length || indice < 0) {
            return null;
        }
        return jugadores[indice].getNombre();
    }

    /**
     * Metodo para retornar apodo de un jugador
     *
     * @param indice
     * @return apodo si no null
     */
    public String getApodoJugador(int indice) {
        if (indice > jugadores.length || indice < 0) {
            return null;
        }
        return jugadores[indice].getApodo();
    }

    public void resetearPuntaje() {
        this.puntaje = 0;
    }

    /**
     * Metodo para obtener nombres y apodos de los todos los jugadores
     *
     * @return array de string primero posicion con el array de nombre, seguda posicion con array de apodos
     */
    public String[][] getNombreApodoJugadores() {
        String[][] nombres = new String[2][jugadores.length];
        for(int i = 0; i < jugadores.length; i++) {
            nombres[0][i] = jugadores[i].getNombre();
            nombres[1][i] = jugadores[i].getApodo();
        }
        return nombres;
    }

    @Override
    public String toString() {
        String aux = nombre + "[\n";
        for (Jugador jugador : jugadores) {
            if (jugador == null) {continue;}
            aux += jugador.toString()+",\n";
        }
        aux += "]";
        return aux;
    }
}
