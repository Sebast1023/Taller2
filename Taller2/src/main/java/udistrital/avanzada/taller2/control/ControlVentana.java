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
import udistrital.avanzada.taller2.modelo.ArchivoSeleccionado;
import udistrital.avanzada.taller2.vista.PanelEquipo;

/**
 * Clase encargada de la logica de la venta
 * 
 * @author Diego
 * @version 1.1
 * @since 30/09/2025
 */
public class ControlVentana implements ActionListener, ImpresorConsola, EventoVentanaListener {

    private ControlPrincipal logica;
    private Ventana ventana;
    private ArchivoSeleccionado archivosSeleccionados;
    /**
     * Contructor
     * 
     * @param controlPrincipal 
     */
    
    //Inyeccion de dependicias
    public ControlVentana(ControlPrincipal controlPrincipal) {
        this.logica = controlPrincipal;
        this.archivosSeleccionados = new ArchivoSeleccionado();
        // Inyeccion para manejar el evento cerra
        ventana = new Ventana("Juego de la Argolla", this);

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
     * Metodo para mostrar mensaje en consola implementado de la intrefaz
     * ImpresorConsola
     *
     * @param mensaje
     */
    @Override
    public void mostrarMensajeEnConsola(String mensaje) {
        ventana.mostrarEnConsola(mensaje);
    }
    
    /**
     * Metodo para mostrar mensaje de error en consola implementado 
     * de la intrefaz ImpresorConsola
     *
     * @param mensaje
     */
    @Override
    public void mostrarErrorEnConsola(String mensaje) {
        ventana.mostrarErrorEnConsola(mensaje);
    }

    /**
     * Metodo para mostrar mensaje en ventana emergente
     *
     * @param mensaje
     */
    public void mostrarMensajeEmergente(String mensaje) {
            ventana.mostrarMensajeEmergente(mensaje);
    }
        
    /**
     * Metodo para mostrar mensaje en ventana emergente
     *
     * @param mensaje
     */
    public void mostrarMensajeEmergenteTiro(String mensaje) {        
        ventana.mostrarMensajeEmergenteTiro(mensaje);          
    }

    /**
     * Metodo para mostrar el menu de seleccion de varios archivos el de elegir
     * properties y serializador
     */
    public void mostrarMenuArchivos() {
        ventana.activarEleccionDeArchivoSerializado();
    }
    
    /**
     * Metodo para mostrar a usuario mensaje de error al cargar mal
     * los archivos
     * 
     * @param mensaje 
     */
    public void mostrarMensajeArchivo(String mensaje) {
        ventana.mostrarMensajeArchivo(mensaje);
    }
    
    /**
     * Metodo para pasar de modo elegir archivos a modo juego
     */
    public void mostrarEquipos() {
        ventana.mostrarEquipos();
    }

    /**
     * Metodo para pasar de modo elegir archivos a modo juego
     * @param equipo
     */
    public void mostrarGanadores(String nombre, String[][] nombres, int equipo, int puntaje) {
        PanelEquipo ganador = AgregarEquipo(nombre, nombres, equipo, false);
        ganador.cambiarPuntajeEquipo(puntaje);
        ventana.mostrarGanadores(ganador);
    }    
    
    /**
     * Metodo para agregar un equipo a la interfaz gráfica
     * @param nombre nombre del equipo
     * @param nombres matriz de strings con nombres poscion 0 y apodos en 1 de los jugadores
     * @param equipo indica si es equipo A si se pasa 1 y B si se pasa 2
     */
    public PanelEquipo AgregarEquipo(String nombre, String[][] nombres, int equipo, boolean agregar) {  
        // Creamos los colores segun corresponda
        Color borde = (equipo == 1) ? new Color(139, 69, 19) : new Color(0, 100, 0);
        Color puntaje = (equipo == 1) ? new Color(139, 69, 19) : new Color(0, 100, 0);
        // Creamos el panel
        PanelEquipo panelEquipo = ventana.agregarEquipo(nombre, borde, puntaje, agregar);

        ImageIcon imagenJugador = null;
        // recorremos todos los jugadores
        for (int i = 0; i < nombres[0].length; i++) {
            // configuramos la foto predeterminada
            String nombreArchivo = (equipo == 1) ? "jugadorA" : "jugadorB";
            nombreArchivo = +(i + 1) + ".png";
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
            // Agremos el jugaddor al panel equipo
            String nombreCompuesto = "<html><div style='text-align: center;'>"
               + "<b>" + nombres[0][i] + "</b><br>"
               + "(" + nombres[1][i] + ")"
               + "</div></html>";
            panelEquipo.agregarJugador(nombreCompuesto, i);
            // Si la imagen es valida le hacemos set para que se muestre
            if (imagenJugador != null) {
                panelEquipo.setFotoJugador(i, imagenJugador);
            }
        }
        return panelEquipo;
    }
    
    /**
     * Metodo para resaltar jugador que esta en su turno
     * 
     * @param indiceEquipo
     * @param indiceJugador 
     */
    public void resaltarJugador(int indiceEquipo, int indiceJugador) {
        ventana.resaltarJugador(indiceEquipo, indiceJugador);
    }
    
    /**
     * Metodo para resaltar jugador que ya termnio su turno
     * 
     * @param indiceEquipo
     * @param indiceJugador 
     */
    public void desResaltarJugador(int indiceEquipo, int indiceJugador) {
        ventana.desResaltarJugador(indiceEquipo, indiceJugador);
    }
    
    /**
     * Metodo para actualizar el puntaje de un equipo
     * 
     * @param indiceEquipo
     * @param puntaje 
     */
    public void setPuntajeEquipo(int indiceEquipo, int puntaje) {
        ventana.setPuntajeEquipo(indiceEquipo, puntaje);
    }
    
    /**
     * Metodo para mostrar menu de otra ronda
     */
    public void mostraMenuTerminar() {
        ventana.mostrarBotonesTerminar();
    }
    
    /**
     * Metodo para mostrar menu de salir
     */
    public void mostraMenuSalir() {
        ventana.mostrarBotonSalir();
    }
    
    /**
     * Metodo para resetear en la vista todos los puntajes a cero
     * por jugar nueva mano
     */
    public void resetearPuntaje() {
        ArrayList<PanelEquipo> paneles = (ArrayList<PanelEquipo>) ventana.getPanelesEquipos();
        for (PanelEquipo panel : paneles) {
            panel.cambiarPuntajeEquipo(0);
        }
    }
    
    /**
     * Metodo para actualizar el panel Equipo y mostra el que se pase
     * 
     * @param indice del panel a editar
     * @param nombreEquipo 
     * @param nombres matriz de string posicion 0 nombres y posicion 1 apodos de jugadores
     */
    public void modificarEquipo(int indice, String nombreEquipo, String[][] nombres) {
        PanelEquipo panel = ventana.getPanelEquipo(indice);
        panel.setNombreEquipo(nombreEquipo);
        for (int i = 0; i < nombres[0].length; i++) {
            String nombreCompuesto = "<html><div style='text-align: center;'>"
               + "<b>" + nombres[0][i] + "</b><br>"
               + "(" + nombres[1][i] + ")"
               + "</div></html>";
            panel.setDatosJugador(i, nombreCompuesto);
        }
    }
    
    /**
     * Método para imprimir restulados 
     * 
     * @param resultados Un cadenade
     * @param titulo
     */
    public void mostrarResultadosConsola(List<String> resultados, String titulo) {        
        mostrarMensajeEnConsola("\n====================================");
        mostrarMensajeEnConsola("        RESULTADOS "+titulo);
        mostrarMensajeEnConsola("====================================");
        for (String registro : resultados) {
            mostrarMensajeEnConsola(registro);
        }
        mostrarMensajeEnConsola("====================================\n");        
    }
    
    /**
     * Metodo para cerrar la aplicacion
     */
    @Override
    public void salir() {
        logica.salir();
        ventana.setVisible(false);
        ventana.dispose();
        System.exit(0);
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
