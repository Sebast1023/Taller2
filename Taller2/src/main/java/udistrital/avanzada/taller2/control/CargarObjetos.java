/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.control;

import java.util.Properties;
import udistrital.avanzada.taller2.modelo.Equipo;
import udistrital.avanzada.taller2.modelo.Jugador;

/**
 *  Clase a la que se le delega la creaacion de objetos desde fuentes externas
 * 
 * @author Mauricio
 * @Since 04/10/2025
 */
public class CargarObjetos {
    /**
     * Metodo para cargar los equipos y jugadores desde un objeto Properties
     *
     * @param props
     * @param controlEquipo
     * @param controlJugador
     */
    public void cargarEquipos(Properties props, ControlEquipo controlEquipo, ControlJugador controlJugador) {
        int numeroEquipos = Integer.parseInt(props.getProperty("numeroequipos", "0"));
        // Crear equipo con jugadores
        for (int i = 1; i <= numeroEquipos; i++) {
            // bandera nos ayuda a determinar si el equipo debe agregarse a la lista de equipos
            boolean bandera = true;
            String nombreEquipo = props.getProperty("equipo" + i + ".nombre");
            if (nombreEquipo == null) {
                continue;
            }
            Equipo equipo = controlEquipo.crearEquipo(nombreEquipo);

            // el requerimiento dice 4 jugadores/Equipo
            for (int j = 1; j <= 4; j++) {
                String nombreJugador = props.getProperty("equipo" + i + ".jugador" + j + ".nombre");
                String apodoJugador = props.getProperty("equipo" + i + ".jugador" + j + ".apodo");
                /*
                    si alguna de las propiedades de jugador es null entonces jugador 
                    no esta completo y por ende el equipo tampoco por lo cual no se agrega
                 */
                if (nombreJugador == null || apodoJugador == null) {
                    bandera = false;
                }
                Jugador jugador = controlJugador.crearJugador(nombreJugador, apodoJugador);
                controlEquipo.agregarJugador(equipo, jugador);
            }
            if (bandera) {
                controlEquipo.agregarEquipo(equipo);
            }
        }
    }

    /**
     * Metodo para cargar puntajes del juego desde un objeto Properties
     *
     * @param props
     * @param controlTiro
     */
    public void cargarPuntajes(Properties props, ControlTiro controlTiro) {
        int cantidadPuntajes = Integer.parseInt(props.getProperty("cantidadpuntajes", "0"));

        for (int i = 1; i <= cantidadPuntajes + 1; i++) {
            try {
                String nombre = props.getProperty("puntaje" + i + ".nombre");
                int puntaje = Integer.parseInt(props.getProperty("puntaje" + i + ".puntos", "0"));
                // si nombre null no agregar a la lista
                if (nombre == null) {
                    continue;
                }
                controlTiro.crearTiro(nombre, puntaje);
            } catch (Exception e) {
            }

        }
    }
}
