package udistrital.avanzada.taller2.control;

import java.util.ArrayList;
import udistrital.avanzada.taller2.modelo.Tiro;

/**
 * La clase gestiona todos los tipos de tiro que hay en la aplicacion
 *
 * @author mauricio
 * @since 02/10/2025
 */
public class ControlTiro {

    private ArrayList<Tiro> tiros;

    public ControlTiro() {
        this.tiros = new ArrayList<Tiro>();
    }

    /**
     * Crea un tiro y lo agrega a lista de tiros
     *
     * @param nombre
     * @param puntaje
     */
    public void crearTiro(String nombre, int puntaje) {
        // crear el tiro
        Tiro aux;
        aux = new Tiro(nombre, puntaje);
        // añadir a la lista
        tiros.add(aux);
    }

    /**
     * Método para obtener el numero de elementos de la lista de tiros
     *
     * @return entero que representa tamaño de la lista
     */
    public int getTamaño() {
        return this.tiros.size();
    }

    /**
     * Método para obtener un tiro de la lista por su posicion de lista
     *
     * @param indice posicion del elemento en la lista
     * @return Tiro si esta fuera de rango null
     */
    public Tiro getTiro(int indice) {
        // Si el indice esta fuera de la lista retornar null
        if (indice < 0 || indice >= this.tiros.size()) {
            return null;
        }
        Tiro aux;
        aux = tiros.get(indice);
        return aux;
    }

    /**
     * Metodo para obtener nombre de un tiro por indice
     *
     * @param indice
     * @return cadena nombre
     */
    public String getNombreTiro(int indice) {
        return tiros.get(indice).getNombre();
    }

    /**
     * Metodo para obtener nombre de un tiro por indice
     *
     * @param indice
     * @return entero puntos
     */
    public int getPuntajeTiro(int indice) {
        return tiros.get(indice).getPuntos();
    }

    /**
     * Metodo para vaciar lista de puntajes
     */
    public void borrarTodo() {
        tiros.clear();
    }
}
