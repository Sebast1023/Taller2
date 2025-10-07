package udistrital.avanzada.taller2.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import udistrital.avanzada.taller2.modelo.ConexionAleatoria;
import udistrital.avanzada.taller2.modelo.ConexionProperties;
import udistrital.avanzada.taller2.modelo.Equipo;
import udistrital.avanzada.taller2.modelo.Jugador;
import udistrital.avanzada.taller2.modelo.SerializadorEquipos;

/**
 * Clase ControlConexion.
 * Gestiona la conexión con los diferentes tipos de archivos:
 * - .properties → para configuración
 * - .data → acceso aleatorio a resultados
 * - .bin → archivos serializados de equipos
 *
 * Cumple principios MVC y SOLID:
 * - La vista nunca toca archivos directamente.
 * - ControlConexion orquesta los modelos de persistencia.
 *
 * @author Diego
 * @version 2.0
 * @since 03/10/2025
 */
public class ControlConexion {

    private ControlEquipo controlEquipo;
    private ControlTiro controlTiro;
    private ControlJugador controlJugador;
    private ConexionAleatoria aleatoria;

    /** Rutas por defecto */
    private final String rutaConfig = "Specs/data/configuracion.properties";
    private final String rutaResultados = "Specs/data/resultados.data";

    /** contador interno de registros (clave) */
    private int contadorClaves = 1;

    /**
     * Constructor con inyección de controladores
     */
    public ControlConexion(ControlEquipo controlEquipo, ControlTiro controlTiro, ControlJugador controlJugador) {
        this.controlEquipo = controlEquipo;
        this.controlTiro = controlTiro;
        this.controlJugador = controlJugador;
        try {
            this.aleatoria = new ConexionAleatoria(rutaResultados);
        } catch (IOException ex) {
            System.err.println("ControlConexion - no se pudo abrir archivo aleatorio: " + ex.getMessage());
        }
    }

    //      MÉTODOS DE CARGA

    /** Carga desde archivo de propiedades (.properties) */
    public void cargarArchivoProperties(File archivo, boolean cargarEquipos) {
        ConexionProperties con = new ConexionProperties();
        Properties props = con.cargar(archivo);
        if (props == null) return;

        if (cargarEquipos) cargarEquipos(props);
        cargarPuntajes(props);
    }

    /** Carga desde archivo serializado (.bin) */
    public void cargarArchivoSerializable(File archivo, boolean cargarEquipos) {
        SerializadorEquipos se = new SerializadorEquipos();
        if (cargarEquipos) {
            ArrayList<Equipo> equipos = se.leerArchivoSerializado(archivo);
            controlEquipo.setEquipos(equipos);
        }
        se.cerrarArchivoSerializadoIn();
        se.cerrarArchivoSerializadoOut();
    }

    //      CARGA DE DATOS

    /** Carga equipos y jugadores desde un objeto Properties */
    public void cargarEquipos(Properties props) {
        int numeroEquipos = Integer.parseInt(props.getProperty("numeroequipos", "0"));
        for (int i = 1; i <= numeroEquipos; i++) {
            boolean bandera = true;
            String nombreEquipo = props.getProperty("equipo" + i + ".nombre");
//<<<<<<< HEAD
//            if (nombreEquipo == null) continue;
//
//            Equipo equipo = controlEquipo.crearEquipo(nombreEquipo);
//=======
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
//<<<<<<< HEAD
//
//            if (completo) controlEquipo.agregarEquipo(equipo);
//=======
            if (!bandera) {
                controlEquipo.removeEquipo(equipo);
            }

        }
    }

    /** Carga los tipos de tiro y sus puntajes */
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

    //     ARCHIVO ALEATORIO

    /** Guarda el resultado de un equipo */
    public void guardarResultado(Equipo equipo, String resultado) {
        if (aleatoria == null) return;
        try {
            String[] jugadores = new String[4];
            for (int i = 0; i < 4; i++)
                jugadores[i] = equipo.getJugadores()[i].getNombre();

            aleatoria.escribirRegistro(contadorClaves++, equipo.getNombre(), jugadores, resultado);
        } catch (IOException ex) {
            System.err.println("ControlConexion - error guardando resultado: " + ex.getMessage());
        }
    }

    /** Obtiene todos los resultados guardados */
    public List<String> obtenerResultados() {
        if (aleatoria == null) return java.util.Collections.emptyList();
        try {
            return aleatoria.leerRegistros();
        } catch (IOException ex) {
            System.err.println("ControlConexion - error leyendo resultados: " + ex.getMessage());
            return java.util.Collections.emptyList();
        }
    }

    /** Cierra el archivo aleatorio */
    public void cerrar() {
        try {
            if (aleatoria != null) aleatoria.cerrar();
        } catch (IOException ex) {
            System.err.println("ControlConexion - error cerrando archivo aleatorio: " + ex.getMessage());
        }
    }

    //   MÉTODOS DE SERIALIZACIÓN

    public void serializarEquipos(ArrayList<Equipo> equipos) {
        SerializadorEquipos se = new SerializadorEquipos();
        se.config();
        se.escribirArchivoSerializado(equipos);
        se.cerrarArchivoSerializadoOut();
        se.cerrarArchivoSerializadoIn();
    }

    public boolean existeArchivoSerializadoEquipos() {
        SerializadorEquipos se = new SerializadorEquipos();
        boolean existe = se.existeBin();
        se.cerrarArchivoSerializadoOut();
        se.cerrarArchivoSerializadoIn();
        return existe;
    }
}
