package udistrital.avanzada.taller2.control;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import udistrital.avanzada.taller2.modelo.ConexionProperties;
import udistrital.avanzada.taller2.modelo.Equipo;
import udistrital.avanzada.taller2.modelo.Jugador;
import udistrital.avanzada.taller2.modelo.SerializadorEquipos;

/**
 * Clase ControlConexion. Descripción: Esta clase se encargará de la conexión
 * con los diferentes archivos, por ahora implementa la lectura del archivo
 * configuracion.properties y carga equipos/jugadores y tiros/puntajes en los
 * controladores que se le inyecten.
 *
 * @author Diego
 * @version 1.0
 * @since 03/10/2025
 */

/* Fecha de Modificacion: 04/10/2025
 * Modificado por: Mauricio
 * Nro. Orden de Trabajo: 006
 * Descripción de la modificación:
 *    - Se añadio conexion a serializador
 *    - Se separan metodos de construccion de objetos
 *    - Se separa resposabilidad directa de cargar el archivo a su correspondite
 *      clase SerializadorEquipos y ConexionProperties segun corresponda
 */
public class ControlConexion {

    private ControlEquipo controlEquipo;
    private ControlTiro controlTiro;
    private ControlJugador controlJugador;

    public ControlConexion(ControlEquipo controlEquipo, ControlTiro controlTiro, ControlJugador controlJugador) {
        this.controlEquipo = controlEquipo;
        this.controlTiro = controlTiro;
        this.controlJugador = controlJugador;
    }

    /**
     * Metodo para cargar archivo de propiedades y crear objetos
     *
     * @param archivo Un archivo con extension .properties
     * @param cargarEquipos indica si se cargan los equipos desde este archivo
     */
    public void cargarArchivoProperties(File archivo, boolean cargarEquipos) {
        ConexionProperties con = new ConexionProperties();
        Properties props = con.cargar(archivo);
        if (props == null) {
            return;
        }
        if (cargarEquipos) {
            cargarEquipos(props);
        }
        cargarPuntajes(props);
    }

    /**
     * Método para cargar archivo de serializador y crear objetos
     *
     * @param archivo Un archivo con extension .bin
     * @param cargarEquipos indica si se cargan los equipos desde este archivo
     */
    public void cargarArchivoSerilizable(File archivo, boolean cargarEquipos) {
        // Instanciamos una clase serializador
        SerializadorEquipos se = new SerializadorEquipos();
        if (cargarEquipos) {
            // deserializar
            ArrayList<Equipo> equipos = se.leerArchivoSerializado(archivo);
            controlEquipo.setEquipos(equipos);
        }
        // Cerrar canales
        se.cerrarArchivoSerializadoIn();
        se.cerrarArchivoSerializadoOut();
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
            int equipo = controlEquipo.crearEquipo(nombreEquipo);

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
                // si nombre null no agregar a la lista
                if (nombre == null) {
                    continue;
                }
                controlTiro.crearTiro(nombre, puntaje);
            } catch (Exception e) {
            }
        }
    }
    
    /**
     * Metodo para serializar lista de equipos
     * @param equipos 
     */
    public void serializarEquipos(ArrayList<Equipo> equipos) {
        SerializadorEquipos se = new SerializadorEquipos();
        se.config();
        se.escribirArchivoSerializado(equipos);
        se.cerrarArchivoSerializadoOut();
        se.cerrarArchivoSerializadoIn();        
    }
    
    /**
     * Metodo para saber si existe un archivo serializado de equipos
     * @return 
     */
    public boolean existeArchivoSerializadoEquipos() {
        SerializadorEquipos se = new SerializadorEquipos();
        boolean b = se.existeBin();
        se.cerrarArchivoSerializadoOut();
        se.cerrarArchivoSerializadoIn();
        return b;
    }
}
