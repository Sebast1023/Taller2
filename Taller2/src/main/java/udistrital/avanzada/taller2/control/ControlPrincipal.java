package udistrital.avanzada.taller2.control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import udistrital.avanzada.taller2.modelo.Equipo;
import udistrital.avanzada.taller2.modelo.Jugador;
import udistrital.avanzada.taller2.modelo.ConexionProperties;
import udistrital.avanzada.taller2.vista.Ventana;

/**
 * Logica de negocio
 *
 * @author Diego
 * @version 1.1
 * @since 30/09/2025
 */
public class ControlPrincipal {

    private ControlVentana controlVentana;
    private ControlEquipo controlEquipo;
    private ControlTiro controlTiro;
    private ControlConexion controlConexion;
    private ArrayList<Equipo> empatados;
    private ControlJugador controlJugador;

    public ControlPrincipal() {
        // inicializar controladores base
        this.controlEquipo = new ControlEquipo();
        this.controlTiro = new ControlTiro();
        this.controlJugador = new ControlJugador();

        // crear conexion (inyección simple)
        this.controlConexion = new ControlConexion(controlEquipo, controlTiro);

        // cargar configuracion.properties
        boolean cargado = controlConexion.cargarDesdeArchivo();
        if (!cargado) {
            System.err.println("Warning: No se cargó configuracion.properties (verifica la ruta Specs/data/configuracion.properties).");
        }

        // crear la UI (le pasamos 'this' como ya lo hacías)
        this.controlVentana = new ControlVentana(this);

        // inicializar la vista con los equipos ya cargados
        iniciarEquipos();
    }

    private void iniciarJuego() {
        // por implementar: flujo de partida
    }

    /**
     * Carga los nombres de equipos y jugadores en la vista
     */
    private void iniciarEquipos() {
        ArrayList<Equipo> equipos = controlEquipo.getEquipos();
        if (equipos == null || equipos.size() < 2) {
            System.err.println("Se necesitan al menos 2 equipos cargados. Revisa configuracion.properties");
            return;
        }

        Equipo equipoA = equipos.get(0);
        Equipo equipoB = equipos.get(1);

        // delegamos la actualización a ControlVentana
        controlVentana.actualizarEquipos(equipoA, equipoB);
    }

    private void empate() {
    }

    /**
     * Método para obtener numero random
     *
     * @param limite
     * @return
     */
    private int obtenerNumeroRandom(int limite) {
        Random numAleatorio;
        numAleatorio = new Random();
        int numero = numAleatorio.nextInt(limite + 1);
        return numero;
    }

    /**
     * Metodo para cargar archivo de propiedades
     *
     * @param archivo
     */
    public void cargarArchivoProperties(File archivo) {
        ConexionProperties con = new ConexionProperties();
        Properties props = con.cargar(archivo);
        if (props == null) {
            return;
        }
        cargarEquipos(props);
        cargarPuntajes(props);
    }

    /**
     * Metodo para cargar los equipos y jugadores desde un objeto Properties
     *
     * @param props
     */
    public void cargarEquipos(Properties props) {
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
     */
    public void cargarPuntajes(Properties props) {
        int cantidadPuntajes = Integer.parseInt(props.getProperty("cantidadpuntajes", "0"));

        for (int i = 1; i <= cantidadPuntajes + 1; i++) {
            try {
                String nombre = props.getProperty("puntaje" + i + ".nombre");
                int puntaje = Integer.parseInt(props.getProperty("puntaje" + i + ".puntos", "0"));
                if (nombre == null) {
                    continue;
                }
                controlTiro.crearTiro(nombre, puntaje);
            } catch (Exception e) {
            }

        }
    }

    public void cargarArchivoSerilizable(File archivo) {
        // conectar a la clase serlizador        
    }
}
