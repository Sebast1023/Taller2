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
     * Método para crear un jugador implementa el metodo de la interfaz
     *
     * @param nombre
     * @param apodo
     * @return jugador creado
     */
    public Jugador crearJugador(String nombre, String apodo) {
        Jugador aux = new Jugador(nombre, apodo);
        return aux;
    }
}
