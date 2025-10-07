package udistrital.avanzada.taller2.control;

import java.util.Properties;
import udistrital.avanzada.taller2.modelo.Jugador;

/**
 * Clase auxiliar para crear los objetos necesarios para el juego y cargarlos
 * 
 * @author Mauricio
 */
public class FabricaModelo {    
    private ControlEquipo controlEquipo;
    private ControlTiro controlTiro;
    private ControlJugador controlJugador;

    public FabricaModelo(ControlEquipo controlEquipo, ControlTiro controlTiro, ControlJugador controlJugador) {
        this.controlEquipo = controlEquipo;
        this.controlTiro = controlTiro;
        this.controlJugador = controlJugador;
    }
    
    /**
     * Metodo para carga equipos y jugadores desde un objeto Properties
     * 
     * @param props objeto Properties
     */
    public void cargarEquipos(Properties props) {
        int numeroEquipos = Integer.parseInt(props.getProperty("numeroequipos", "0"));
        for (int i = 1; i <= numeroEquipos; i++) {
            boolean bandera = true;
            String nombreEquipo = props.getProperty("equipo" + i + ".nombre");
            if (nombreEquipo == null) {
                continue;
            }
            int equipo = controlEquipo.crearEquipo(nombreEquipo);

            for (int j = 1; j <= 4; j++) {
                String nombreJugador = props.getProperty("equipo" + i + ".jugador" + j + ".nombre");
                String apodoJugador = props.getProperty("equipo" + i + ".jugador" + j + ".apodo");
                if (nombreJugador == null || apodoJugador == null) bandera = false;

                Jugador jugador = controlJugador.crearJugador(nombreJugador, apodoJugador);
                controlEquipo.agregarJugador(equipo, jugador);
            }
            if (!bandera) {
                controlEquipo.removeEquipo(equipo);
            }

        }
    }

    /**
     * Metodo para carga los tipos de tiro y sus puntajes
     * 
     * @param props objeto Properties
     */
    public void cargarPuntajes(Properties props) {
        int cantidadPuntajes = Integer.parseInt(props.getProperty("cantidadpuntajes", "0"));
        for (int i = 1; i <= cantidadPuntajes; i++) {
            try {
                String nombre = props.getProperty("puntaje" + i + ".nombre");
                int puntaje = Integer.parseInt(props.getProperty("puntaje" + i + ".puntos", "0"));
                if (nombre == null) continue;
                controlTiro.crearTiro(nombre, puntaje);
            } catch (Exception ignored) {}
        }
    }
}
