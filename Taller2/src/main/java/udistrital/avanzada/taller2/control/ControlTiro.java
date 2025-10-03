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
     * @return
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
}
