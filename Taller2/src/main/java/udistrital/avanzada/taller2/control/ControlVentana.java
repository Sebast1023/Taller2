package udistrital.avanzada.taller2.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import udistrital.avanzada.taller2.modelo.Equipo;
import udistrital.avanzada.taller2.modelo.Jugador;
import udistrital.avanzada.taller2.vista.Ventana;
import javax.swing.JFileChooser;
import udistrital.avanzada.taller2.modelo.ArchivoSeleccionado;
import udistrital.avanzada.taller2.vista.PanelEquipo;

/**
 *
 * @author Diego
 * @version 1.1
 * @since 30/09/2025
 */

public class ControlVentana implements ActionListener {

    private ControlPrincipal logica;
    private Ventana ventana;
    private ArchivoSeleccionado archivosSeleccionados;

    public ControlVentana(ControlPrincipal controlPrincipal) {
        this.logica = controlPrincipal;
        this.archivosSeleccionados = new ArchivoSeleccionado();
        ventana = new Ventana("Juego de la Argolla");

        // registrar listeners
        ventana.btnLanzar.addActionListener(this);
        ventana.btnNuevaRonda.addActionListener(this);
        ventana.btnSalir.addActionListener(this);
        ventana.btnArchivoBin.addActionListener(this);
        ventana.btnArchivoProperties.addActionListener(this);
        ventana.btnCargarArchivos.addActionListener(this);
    }

    private void lanzarArgolla() {
        ventana.areaMensajes.append(">> Lanzamiento realizado...\n");
    }

    private void nuevaRonda() {
        ventana.mostrarMensajeEmergente("Se inicia una nueva ronda.");
        ventana.areaMensajes.setText("");
    }

    private void salir() {
        ventana.setVisible(false);
        ventana.dispose();
        System.exit(0);
    }

    /**
     * Metodo para que el usuario eliga el archivo de precargar .Properties
     */
    public void obtenerArchivoPropiedades() {
        ventana.mostrarMensajeArchivo("");
        JFileChooser fileChooser = ventana.obtenerFileChooser("Archivo de propiedas", "properties");
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            archivosSeleccionados.setArchivoProperties(archivoSeleccionado);
            ventana.setNombreArchivoProp(archivoSeleccionado.getName());
        }
        
    }

    /**
     * Metodo para que el usuario eliga el archivo de precargar .bin
     */
    public void obtenerArchivoSerializador() {
        ventana.mostrarMensajeArchivo("");
        JFileChooser fileChooser = ventana.obtenerFileChooser("Archivo serializado", "bin");
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            archivosSeleccionados.setArchivoBin(archivoSeleccionado);
            ventana.setNombreArchivoBin(archivoSeleccionado.getName());
        }
    }
    /**
     * Metodo para cargar Archivos
     */
    
    public void cargarArchivos() {
        if (archivosSeleccionados.getArchivoProperties() == null) {
            ventana.mostrarMensajeArchivo("¡Necesario escoger archivo properties!");
            return;
        }
        String opcion = ventana.getSeleccionOrigenCarga();
        // Opcion cargar desde propiedades predeterminadamente cargarDesde es 1
        int cargarDesde = 1;
        // Si es desde Serializado entonces cargarDesde es 2
        if (opcion.toLowerCase().equalsIgnoreCase("Serializado")) {
            cargarDesde = 2;
        }
        
        if (cargarDesde == 2 && archivosSeleccionados.getArchivoBin() == null) {
            ventana.mostrarMensajeArchivo("¡Necesario escoger archivo serializado si quiere cargar desde ahi!");
            return;
        }
        
        logica.cargarArchivosPrecarga(
                archivosSeleccionados.getArchivoProperties(),
                archivosSeleccionados.getArchivoBin(),
                cargarDesde
        );
    }

    /**
     * Metodo para mostrar mensaje en consola
     *
     * @param mensaje
     */
    public void mostrarMensajeEnConsola(String mensaje) {
        ventana.mostrarEnConsola(mensaje);
    }

    /**
     * Metodo para mostrar mensaje en ventana emergente
     *
     * @param mensaje
     */
    public void mostraMensajeEmergente(String mensaje) {
        ventana.mostrarMensajeEmergente(mensaje);
    }

    /**
     * Metodo que cambia el action command del boton lanzar a modo empate
     */
    public void activarModoLanzarEmpate() {
        ventana.btnLanzar.setActionCommand("LanzarEmpate");
    }

    /**
     * Metodo que cambia el action command del boton lanzar a modo normal
     */
    public void activarModolanzar() {
        ventana.btnLanzar.setActionCommand("Lanzar");
    }
    
    /**
     * Metodo para mostrar el menu de seleccion de varios archivos
     * el de elegir properties y serializador
     */
    public void mostrarMenuArchivos() {
        ventana.activarEleccionDeArchivoSerializado();
    }
    
    public void mostrarMensajeArchivo(String mensaje) {
        ventana.mostrarMensajeArchivo(mensaje);
    }
        
    public void mostrarEquipos() {
        ventana.mostraEquipos();
    }    
    
    public void AgregarEquipo(String nombre, String[] nombres, String[] apodos) {        
        PanelEquipo panelEquipo = ventana.agregarEquipo(nombre);
        for (int i = 0; i < nombres.length; i++) {
            panelEquipo.agregarJugador(nombres[i], i);
        }
    }    
    
    public void resaltarJugador(int indiceEquipo, int indiceJugador) {
        ventana.resaltarJugador(indiceEquipo, indiceJugador);
    }
    
    public void desResaltarJugador(int indiceEquipo, int indiceJugador) {
        ventana.desResaltarJugador(indiceEquipo, indiceJugador);
    }
    
    public void setPuntajeEquipo(int indiceEquipo, int puntaje) {
        ventana.setPuntajeEquipo(indiceEquipo, puntaje);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "Lanzar":
                //lanzarArgolla();
                logica.lanzarArgolla();                
                break;
            case "NuevaRonda":
                nuevaRonda();
                break;
            case "Salir":
                logica.salir();
                salir();
                break;
            case "ObtenerProperties":
                obtenerArchivoPropiedades();
                break;
            case "ObtenerSerializable":
                obtenerArchivoSerializador();
                break;
            case "LanzarEmpate":
                logica.lanzarArgollaEmpate();
                break;
            case "CargarArchivos":    
                cargarArchivos();
                break;
            default:
                break;
        }

    }
}
