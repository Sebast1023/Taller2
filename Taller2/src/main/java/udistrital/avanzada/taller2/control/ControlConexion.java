package udistrital.avanzada.taller2.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import udistrital.avanzada.taller2.modelo.Equipo;
import udistrital.avanzada.taller2.modelo.Jugador;

/**
 * Clase ControlConexion. Descripción: Se encarga de la conexión con los
 * diferentes archivos, implementando la lectura de configuracion.properties y
 * cargando equipos/jugadores.
 *
 * NOTA: por la especificación del taller, el .properties debe estar en:
 * Specs/data/configuracion.properties
 *
 * @author Diego
 * @version 1.1
 * @since 07/10/2025
 */
public class ControlConexion {

    private ControlEquipo controlEquipo;
    private ControlTiro controlTiro;

    /**
     * Ruta por defecto (fuera de src)
     */
    private final String rutaPorDefecto = "Specs/data/configuracion.properties";

    public ControlConexion(ControlEquipo controlEquipo, ControlTiro controlTiro) {
        this.controlEquipo = controlEquipo;
        this.controlTiro = controlTiro;
    }

    /**
     * Carga usando la ruta por defecto.
     */
    public boolean cargarDesdeArchivo() {
        return cargarDesdeArchivo(rutaPorDefecto);
    }

    /**
     * Carga la configuración desde un archivo .properties dado.
     *
     * @param rutaArchivo ruta al archivo properties
     * @return true si la carga fue exitosa, false si ocurre error
     */
    public boolean cargarDesdeArchivo(String rutaArchivo) {
        Properties prop = new Properties();

        try (InputStream inputStream = obtenerInputStream(rutaArchivo)) {

            if (inputStream == null) {
                System.err.println("⚠ No se encontró el archivo: " + rutaArchivo);
                return false;
            }

            prop.load(inputStream);

            // --- Equipos y jugadores ---
            int numeroEquipos = Integer.parseInt(prop.getProperty("numeroequipos", "0"));
            for (int i = 1; i <= numeroEquipos; i++) {
                String nombreEquipo = prop.getProperty("equipo" + i + ".nombre", "Equipo" + i);
                Equipo equipo = controlEquipo.crearEquipo(nombreEquipo);

                // Por requerimiento: 4 jugadores por equipo
                for (int j = 1; j <= 4; j++) {
                    String nombreJugador = prop.getProperty("equipo" + i + ".jugador" + j + ".nombre", "Jugador " + j);
                    String apodoJugador = prop.getProperty("equipo" + i + ".jugador" + j + ".apodo", "");
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

        } catch (IOException ex) {
            System.err.println("❌ Error al leer archivo: " + ex.getMessage());
            return false;
        } catch (Exception ex) {
            System.err.println("❌ Error general: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Permite compatibilidad con proyectos que tengan Specs dentro o fuera de
     * src.
     */
    private InputStream obtenerInputStream(String rutaArchivo) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(rutaArchivo);
        if (inputStream == null) {
            try {
                inputStream = new FileInputStream(rutaArchivo);
            } catch (IOException e) {
                return null;
            }
        }
        return inputStream;
    }
}
