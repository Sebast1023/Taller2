package udistrital.avanzada.taller2.control;

import udistrital.avanzada.taller2.modelo.Jugador;

/**
 * Clase que gestiona las propiedades de la clase jugador
 *
 * @author maucirio
 * @since 03/10/2025
 */
public class ControlJugador {
    
    /**
     * Método para crear un jugador
     *
     * @param nombre
     * @param apodo
     * @return jugador creado
     */
    public Jugador crearJugador(String nombre, String apodo) {
        Jugador aux = new Jugador(nombre, apodo);
        return aux;
    }
    /**
     * Método para obtener nombre de un jugador
     *
     * @param jugador
     * @return
     */
    public String obtenerNombreJugador(Jugador jugador) {
        return jugador.getNombre();
    }
    /**
     * Método para obtener apodo de un jugador
     *
     * @param jugador
     * @return
     */
    public String obtenerApodoJugador(Jugador jugador) {
        return jugador.getApodo();
    }
    /**
     * Método para obtener nombres de jugadores
     *
     * @param jugadores
     * @return arreglo de cadenas
     */
    public String[] obtenerNombresJugadores(Jugador[] jugadores) {
        int tamano = jugadores.length;
        String[] nombres = new String[tamano];
        for (int i = 0; i < jugadores.length; i++) {
            nombres[i] = jugadores[i].getNombre();
        }
        return nombres;
    }
  /**
     * Método para obtener apodos de jugadores
     *
     * @param jugadores
     * @return arreglo de cadenas
     */
    public String[] obtenerApodosJugadores(Jugador[] jugadores) {
        int tamano = jugadores.length;
        String[] apodos = new String[tamano];
        for (int i = 0; i < jugadores.length; i++) {
            nombres[i] = jugadores[i].getApodo();
        }
        return apodos;
    }
}
