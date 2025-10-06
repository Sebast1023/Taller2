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
     * Método que crear un equipo y añadirlo a la lista de equipos
     *
     * @param nombre
     * @return equipo creado
     */
    public int crearEquipo(String nombre) {
        Equipo aux = new Equipo();
        aux.setNombre(nombre);
        equipos.add(aux);
        return equipos.indexOf(aux);
    }

    /**
     * Método para agregar un jugador a un equipo
     *
     * @param indice equipo donde se agrega el jugador
     * @param jugador jugador a agregar
     */
    public void agregarJugador(int indice, Jugador jugador) {
        equipos.get(indice).agregarJugador(jugador);
    }

    /**
     * Metodo para obtener puntaje de un equipo
     *
     * @param indice
     * @return
     */
    public int obtenerPuntaje(int indice) {
        return equipos.get(indice).getPuntaje();
    }

    /**
     * Método para obtener los jugadores de un equipo
     *
     * @param equipo
     * @return
     */
    public Jugador[] obtenerJugadores(Equipo equipo) {
        return equipo.getJugadores();
    }

    /**
     * Método para agregar un equipo a la lista de equipos
     *
     * @param equipo
     */
    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
    }
    
    /**
     * Metodo para obtener tamaño de lista de equipos
     * 
     * @return numero de elementos de equipos
     */
    public int getTamaño() {
        return equipos.size();
    }

    /**
     * Método para obtener puntaje de equipo
     *
     * @param indice
     * @return puntaje de equipo
     */
    public int getPuntajeEquipo(int indice) {
        return equipos.get(indice).getPuntaje();
    }

    /**
     * Método para sumar puntaje a equipo
     *
     * @param indice posicion del equipo
     * @param puntaje
     * @return puntaje actual despues de operacion
     */
    public int agregarPuntos(int indice, int puntaje) {
        return equipos.get(indice).agregarPuntos(puntaje);
    }

    /**
     * Método para obtener un jugador de un equipo con el indice
     *
     * @param indiceEquipo
     * @param indiceJugador
     * @return
     */
    public Jugador obtenerJugador(int indiceEquipo, int indiceJugador) {
        return equipos.get(indiceEquipo).getJugador(indiceJugador);
    }

    /**
     * Método para obtener nombre y apodo de un jugador
     *
     * @param indiceEquipo
     * @param indiceJugador
     * @return Vector de Strings con dos elementos primer elemento nombre y
     * segundo el apodo
     */
    public String[] obtenerNombresJugador(int indiceEquipo, int indiceJugador) {

        String[] aux = {"", ""};
        aux[0] = equipos.get(indiceEquipo).getNombreJugador(indiceJugador);
        aux[1] = equipos.get(indiceEquipo).getApodoJugador(indiceJugador);
        return aux;
    }

    /**
     * Método sobre cargado para obtener nombre del equipo con indice
     *
     * @param indice
     * @return
     */
    public String obtenerNombreEquipo(int indice) {
        return equipos.get(indice).getNombre();
    }

    /**
     * Método para resetear los puntajes de los equipos a cero
     */
    public void resetearPuntaje() {
        for (Equipo equipo : equipos) {
            equipo.resetearPuntaje();
        }
    }

    /**
     * Metodo para vaciar lista de equipos
     */
    public void borrarTodo() {
        equipos.clear();
    }
    
    public Equipo getEquipo(int indice) {
        return equipos.get(indice);
    }
    
    public String[][] getNombreApodoJugadores(int indice) {
        return equipos.get(indice).getNombreApodoJugadores();
    }
    
}
