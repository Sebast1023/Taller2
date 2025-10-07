package udistrital.avanzada.taller2.control;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import udistrital.avanzada.taller2.modelo.Equipo;
import udistrital.avanzada.taller2.modelo.Jugador;
import udistrital.avanzada.taller2.vista.Ventana;
import udistrital.avanzada.taller2.modelo.Tiro;

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

    public ControlPrincipal() {
        // inicializar controladores base
        this.controlEquipo = new ControlEquipo();
        this.controlTiro = new ControlTiro();

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

    /**
     * Abre un JFileChooser para seleccionar el archivo .properties
     *
     * @return la ruta absoluta seleccionada o null si se cancela
     */
    private String seleccionarArchivoProperties() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccione el archivo de configuración (.properties)");
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir") + File.separator + "Specs" + File.separator + "data"));
        chooser.setFileFilter(new FileNameExtensionFilter("Archivos .properties", "properties"));

        int resultado = chooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();
            return archivo.getAbsolutePath();
        }
        return null;
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
     * Simula y devuelve un Tiro aleatorio usando ControlTiro. Retorna null si
     * no hay tipos de tiro cargados.
     *
     * @return
     */
    public Tiro simularLanzamiento() {
        if (this.controlTiro == null || this.controlTiro.getTamaño() == 0) {
            System.err.println("ControlPrincipal.simularLanzamiento: no hay tipos de tiro cargados.");
            return null;
        }
        Random rnd = new Random();
        int idx = rnd.nextInt(this.controlTiro.getTamaño());
        return this.controlTiro.getTiro(idx);
    }

    // Permite que otros controladores accedan al ControlEquipo
    public ControlEquipo getControlEquipo() {
        return this.controlEquipo;
    }

}
