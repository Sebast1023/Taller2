package udistrital.avanzada.taller2.control;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import udistrital.avanzada.taller2.vista.Ventana;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
        ventana.btnTerminar.addActionListener(this);
    }

    private void lanzarArgolla() {
        ventana.areaMensajes.append(">> Lanzamiento realizado...\n");
    }

    private void nuevaRonda() {
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
     * Metodo para mostrar el menu de seleccion de varios archivos el de elegir
     * properties y serializador
     */
    public void mostrarMenuArchivos() {
        ventana.activarEleccionDeArchivoSerializado();
    }

    public void mostrarMensajeArchivo(String mensaje) {
        ventana.mostrarMensajeArchivo(mensaje);
    }

    public void mostrarEquipos() {
        ventana.mostrarEquipos();
//<<<<<<< HEAD
//    }
//
//    public void AgregarEquipo(String nombre, String[] nombres, String[] apodos) {
//        PanelEquipo panelEquipo = ventana.agregarEquipo(nombre);
//        for (int i = 0; i < nombres.length; i++) {
//            panelEquipo.agregarJugador(nombres[i], i);
    }    
    
    public void AgregarEquipo(String nombre, String[][] nombres, int equipo) {  
        Color borde = (equipo == 1) ? new Color(139, 69, 19) : new Color(0, 100, 0);
        Color puntaje = (equipo == 1) ? new Color(139, 69, 19) : new Color(0, 100, 0);
        
        PanelEquipo panelEquipo = ventana.agregarEquipo(nombre, borde, puntaje);  
        
        ImageIcon imagenJugador = null;                
        
        for (int i = 0; i < nombres[0].length; i++) {
            String nombreArchivo = (equipo == 1) ? "jugadorA" : "jugadorB";
            nombreArchivo =+ (i + 1) + ".png";            
            String ruta = "Specs/data/images/" + nombreArchivo;
            URL url = getClass().getClassLoader().getResource(ruta);
            if (url == null) {
                url = getClass().getClassLoader().getResource("Specs/data/images/predeterminada.png");
            }
            if (url != null) {
                ImageIcon icono = new ImageIcon(url);
                Image img = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imagenJugador = new ImageIcon(img);
            }
            panelEquipo.agregarJugador(nombres[0][i],i);
            
            if (imagenJugador != null) {
                panelEquipo.setFotoJugador(i, imagenJugador);
            }            
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

    public void mostraMenuTerminar() {
        ventana.mostrarBotonesTerminar();
    }

    public void mostraMenuSalir() {
        ventana.mostrarBotonSalir();
    }

    public void resetearPuntaje() {
        ArrayList<PanelEquipo> paneles = (ArrayList<PanelEquipo>) ventana.getPanelesEquipos();
        for (PanelEquipo panel : paneles) {
            panel.cambiarPuntajeEquipo(0);
        }
    }

    public void modificarEquipo(int indice, String nombreEquipo, String[][] nombres) {
        PanelEquipo panel = ventana.getPanelEquipo(indice);
        panel.setNombreEquipo(nombreEquipo);
        for (int i = 0; i < nombres[0].length; i++) {
            panel.setDatosJugador(i, nombres[0][i], nombres[1][i]);
        }
    }

    /**
 * Muestra un mensaje simple en consola desde la vista.
 */
public void mostrarMensajeConsola(String mensaje) {
    ventana.mostrarEnConsola(mensaje);
}

/**
 * Envía una lista de resultados a la vista para imprimirlos en consola.
 */
public void mostrarResultadosConsola(List<String> resultados) {
    ventana.mostrarResultadosEnConsola(resultados);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "Lanzar":
                //lanzarArgolla();
                logica.lanzar();
                break;
            case "NuevaRonda":
                resetearPuntaje();
                ventana.mostrarBotonesJuego();
                logica.nuevaMano();
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
            case "CargarArchivos":
                cargarArchivos();
                break;
            case "Terminar":
                ventana.mostrarBotonSalir();
                break;
            default:
                break;
        }

    }
}
