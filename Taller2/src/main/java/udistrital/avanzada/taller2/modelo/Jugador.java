package udistrital.avanzada.taller2.modelo;

import java.io.Serializable;

/**
 * Clase Jugador.java representa a los jugadores del juego Sus instancias seran
 * agrupadas en equipos de a 4. Implementa Serializable para que sea serializado
 * cuando se serializen las listas de equipos
 *
 * @author Mauricio
 * @version 1.1
 * @since 30/09/2025
 */
public class Jugador implements Serializable {

    private String nombre;
    private String apodo;

    /**
     * Constructor con los parametros
     *
     * @param nombre
     * @param apodo
     */
    public Jugador(String nombre, String apodo) {
        this.nombre = nombre;
        this.apodo = apodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    @Override
    public String toString() {
        String aux = nombre + " " + apodo;
        return aux;
    }
}
