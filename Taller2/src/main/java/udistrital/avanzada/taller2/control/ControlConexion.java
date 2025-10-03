package udistrital.avanzada.taller2.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import udistrital.avanzada.taller2.modelo.Equipo;
import udistrital.avanzada.taller2.modelo.Jugador;

/**
 * Clase ControlConexion.
 * Descripción: Esta clase se encargará de la conexión con los diferentes archivos,
 * por ahora implementa la lectura del archivo configuracion.properties y carga
 * equipos/jugadores y tiros/puntajes en los controladores que se le inyecten.
 *
 * NOTA: por la especificación del taller, el .properties debe estar en:
 *   Specs/data/configuracion.properties
 *
 * @author Diego
 * @version 1.0
 * @since 03/10/2025
 */
public class ControlConexion {

    private ControlEquipo controlEquipo;
    private ControlTiro controlTiro;

    /** Ruta por defecto */
    private String rutaPorDefecto = "Specs/data/configuracion.properties";

    public ControlConexion(ControlEquipo controlEquipo, ControlTiro controlTiro) {
        this.controlEquipo = controlEquipo;
        this.controlTiro = controlTiro;
    }

    /** Carga usando la ruta por defecto */
    public boolean cargarDesdeArchivo() {
        return cargarDesdeArchivo(rutaPorDefecto);
    }

    /**
     * Carga la configuración desde un archivo .properties dado.
     * Rellena controlEquipo y controlTiro con los datos leídos.
     *
     * @param rutaArchivo ruta al archivo properties
     * @return true si la carga fue exitosa, false en caso de error
     */
    public boolean cargarDesdeArchivo(String rutaArchivo) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(rutaArchivo)) {
            prop.load(fis);

            // --- Equipos y jugadores ---
            int numeroEquipos = Integer.parseInt(prop.getProperty("numeroequipos", "0"));
            for (int i = 1; i <= numeroEquipos; i++) {
                String nombreEquipo = prop.getProperty("equipo" + i + ".nombre", "Equipo" + i);
                Equipo equipo = controlEquipo.crearEquipo(nombreEquipo);

                // el requerimiento dice 4 jugadores/Equipo
                for (int j = 1; j <= 4; j++) {
                    String nombreJugador = prop.getProperty("equipo" + i + ".jugador" + j + ".nombre", "Jugador" + j);
                    String apodoJugador = prop.getProperty("equipo" + i + ".jugador" + j + ".apodo", "");
                    // Se asume que existe un constructor Jugador(nombre, apodo)
                    Jugador jugador = new Jugador(nombreJugador, apodoJugador);
                    controlEquipo.agregarJugador(equipo, jugador);
                }
            }

            // --- Tipos de tiro / puntajes ---
            int cantidadPuntajes = Integer.parseInt(prop.getProperty("cantidadpuntajes", "0"));
            for (int p = 1; p <= cantidadPuntajes; p++) {
                String nombre = prop.getProperty("puntaje" + p + ".nombre", "tipo" + p);
                int puntos = Integer.parseInt(prop.getProperty("puntaje" + p + ".puntos", "0"));
                controlTiro.crearTiro(nombre, puntos);
            }

            return true;
        } catch (IOException | NumberFormatException ex) {
            System.err.println("ControlConexion - error cargando archivo: " + ex.getMessage());
            return false;
        }
    }
}


