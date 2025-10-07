package udistrital.avanzada.taller2.vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import udistrital.avanzada.taller2.control.EventoVentanaListener;

/**
 * Clase que muestra la interfaz gráfica
 * 
 * @author Diego
 * @version 1.2
 * @since 03/10/2025
 * @modificacion Se eliminó el label duplicado de nombre de equipo y se usó el
 * TitledBorder para mostrarlo directamente. Se mejoró el diseño visual de
 * puntajes, jugadores y botones.
 */
public class Ventana extends JFrame {

    // Paneles principales
    private JPanel panelTitulo;
    private JPanel panelBotones;
    private JPanel panelCentro;
    private JScrollPane panelScroll;
    private JPanel panelEquipos;
    private CardLayout cardLayout;
    private PanelArchivos panelArchivos;
    private ArrayList<PanelEquipo> panelesEquipos;

    // Botones
    public JButton btnLanzar;
    public JButton btnNuevaRonda;
    public JButton btnSalir;
    public JButton btnTerminar;
    public JButton btnArchivoProperties;
    public JButton btnArchivoBin;
    public JButton btnCargarArchivos;

    // Área de mensajes
    public JTextArea areaMensajes;

    public Ventana(String title, EventoVentanaListener evl) {        
        super(title);
        setSize(1000, 600);
        setLocationRelativeTo(null); // centrar
        
        
        // Evento cierre
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {   
                evl.salir();
            }
        });
        
        setVisible(true);
    }

    @Override
    protected void frameInit() {
        super.frameInit();
        setLayout(new BorderLayout(10, 10));

        // ===== Panel título global =====
        panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(210, 180, 140)); // café claro
        JLabel texto = new JLabel("🎯 Juego de la Argolla 🎯", JLabel.CENTER);
        texto.setFont(new Font("Serif", Font.BOLD, 32));
        texto.setForeground(new Color(60, 30, 10));
        panelTitulo.add(texto);

        // ===== Panel centro con panelEquipos y PanelArchivos =====
        panelEquipos = new JPanel(new GridLayout(1, 3, 15, 0));
        panelEquipos.setBackground(new Color(245, 235, 220));
        panelEquipos.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));
        panelesEquipos = new ArrayList<>();

        // Por si los equipos son muy grandes que se pueda hacer Scroll
        panelScroll = new JScrollPane(
                panelEquipos,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        // Modificar velocidad de Scrooll
        panelScroll.getVerticalScrollBar().setUnitIncrement(16);
                
        btnArchivoProperties = crearBoton("Escoger archivo propiedades", new Color(70, 130, 180));
        btnArchivoBin = crearBoton("Escoger archivo serializado", new Color(70, 130, 180));
        btnCargarArchivos = crearBoton("Cargar archivos", new Color(70, 130, 180));

        btnArchivoProperties.setActionCommand("ObtenerProperties");
        btnArchivoBin.setActionCommand("ObtenerSerializable");
        btnCargarArchivos.setActionCommand("CargarArchivos");

        panelArchivos = new PanelArchivos(btnArchivoProperties, btnArchivoBin);

        cardLayout = new CardLayout();

        panelCentro = new JPanel(cardLayout);
        panelCentro.setBackground(new Color(245, 245, 245));

        panelCentro.add(panelArchivos, "Archivos");
        panelCentro.add(panelScroll, "Equipos");

        // ===== Panel mensajes en el centro =====
        JPanel panelMensajes = new JPanel(new BorderLayout());
        panelMensajes.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
                "Resultados del Juego",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 16),
                Color.DARK_GRAY
        ));
        areaMensajes = new JTextArea(15, 30);
        areaMensajes.setEditable(false);
        areaMensajes.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaMensajes);
        panelMensajes.add(scroll, BorderLayout.CENTER);

        // ===== Panel botones =====
        panelBotones = new JPanel(new BorderLayout());

        btnSalir = crearBoton("❌ Salir", new Color(178, 34, 34));
        btnLanzar = crearBoton("🎲 Lanzar Argolla", new Color(70, 130, 180));
        btnNuevaRonda = crearBoton("🔄 Nueva Ronda", new Color(34, 139, 34));
        btnTerminar = crearBoton("Terminar", new Color(178, 34, 34));

        btnSalir.setActionCommand("Salir");
        btnLanzar.setActionCommand("Lanzar");
        btnNuevaRonda.setActionCommand("NuevaRonda");
        btnTerminar.setActionCommand("Terminar");

        panelBotones.add(btnCargarArchivos, BorderLayout.EAST);
        panelBotones.add(btnSalir, BorderLayout.WEST);

        // ===== Ensamblar todo =====
        this.add(panelTitulo, BorderLayout.NORTH);
        this.add(panelCentro, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);
        
    }

    /**
     * Crea botones estilizados
     */
    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("SansSerif", Font.BOLD, 16));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Efecto hover
        boton.addChangeListener(e -> {
            if (boton.getModel().isRollover()) {
                boton.setBackground(colorFondo.darker());
            } else {
                boton.setBackground(colorFondo);
            }
        });

        return boton;
    }

    /**
     * Método cuadro de dialgo con mensajes emergentes indicando el componente
     * 
     * @param componente
     * @param mensaje 
     */
    public void mensajeEmergente(Component componente, String mensaje) {
        JOptionPane.showMessageDialog(componente, mensaje);
    }
    
    /**
     * Método cuadro de dialogo con mensajes 
     * 
     * @param mensaje 
     */
    public void mostrarMensajeEmergente(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    
    
    /**
     * Método cuadro de dialgo con informacion del tiro y jugador
     * 
     * @param mensaje 
     */
    public void mostrarMensajeEmergenteTiro(String mensaje) {
        JLabel lblMensaje = new JLabel(mensaje);
        lblMensaje.setFont(new Font("Arial", Font.BOLD, 16));
        lblMensaje.setForeground(new Color(0, 102, 204));
        JOptionPane.showMessageDialog(this, lblMensaje, "Resultado Lanzamiento", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Mostra mensaje en consola
     * 
     * @param mensaje 
     */
    public void mostrarEnConsola(String mensaje) {
        System.out.println(mensaje);
    }
    
    /**
     * Mostrar mensaje de error en consola
     * @param mensaje 
     */
    public void mostrarErrorEnConsola(String mensaje) {
        System.err.println(mensaje);
    }   

    /**
     * metodo para obtener la instancia de JFileChooser con la cual se escogeran
     * archivos de precarga
     *
     * @return retorna un JFileChooser
     */
    public JFileChooser obtenerFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        //Carpeta donde se guardan archivos de precarga y poscarga
        File carpetaInicial = new File("Specs/data");
        // Que se abra en la carpeta data predeterminadamente
        fileChooser.setCurrentDirectory(carpetaInicial);
        return fileChooser;
    }

    /**
     * metodo sobrecargado para obtener la instancia de JFileChooser con la cual
     * se escogeran archivos de precarga con un filtro
     *
     * @param descripcion el mensaje que aparecera en el Chooser
     * @param extension la extension de los archivos permitidos
     * @return retorna un JFileChooser
     */
    public JFileChooser obtenerFileChooser(String descripcion, String extension) {
        JFileChooser fileChooser = new JFileChooser();
        //Carpeta donde se guardan archivos de precarga y poscarga
        File carpetaInicial = new File("Specs/data");
        // Que se abra en la carpeta data predeterminadamente
        fileChooser.setCurrentDirectory(carpetaInicial);
        // Filtro con el mensaje que aparecera y la extension del mismo
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(descripcion, extension);
        fileChooser.setFileFilter(filtro);
        return fileChooser;
    }
    
    /**
     * Metodo para agregar equipo vacio a la interfaz
     * 
     * @param titulo
     * @param colorBorde
     * @param colorPuntaje
     * @return PanelEquipo creado
     */
    public PanelEquipo agregarEquipo(String titulo, Color colorBorde, Color colorPuntaje) {
        PanelEquipo panelEquipo = new PanelEquipo(titulo, colorBorde, colorPuntaje);
        panelEquipos.add(panelEquipo);
        panelesEquipos.add(panelEquipo);
        return panelEquipo;
    }
    
    /**
     * Metodo para mostra panel equipos y quitar panel de eleccion de
     * de archivos (PanelArchivos)
     */
    public void mostrarEquipos() {
        cardLayout.show(panelCentro, "Equipos");
        panelCentro.revalidate();
        panelCentro.repaint();
        mostrarBotonesJuego();
    }
    
    /**
     * Metodo para mostrar los botones de ronda 
     */
    public void mostrarBotonesJuego() {
        panelBotones.removeAll();
        panelBotones.add(btnTerminar, BorderLayout.WEST);
        panelBotones.add(btnLanzar, BorderLayout.EAST);
        panelBotones.revalidate();
        panelBotones.repaint();
    }
    
    /**
     * Metodo para mostrar opciones de nueva partida y terminar
     */
    public void mostrarBotonesTerminar() {
        panelBotones.removeAll();
        panelBotones.add(btnTerminar, BorderLayout.WEST);
        panelBotones.add(btnNuevaRonda, BorderLayout.EAST);
        panelBotones.revalidate();
        panelBotones.repaint();
    }
    
    /**
     * Metodo para mostrar solo boton de salida
     */
    public void mostrarBotonSalir() {
        panelBotones.removeAll();
        panelBotones.add(btnSalir, BorderLayout.WEST);
        panelBotones.revalidate();
        panelBotones.repaint();
    }

    /**
     * Metodo para resaltar un jugador en especifico
     * 
     * @param indiceEquipo
     * @param indiceJugador 
     */
    public void resaltarJugador(int indiceEquipo, int indiceJugador) {
        PanelEquipo panelEquipo = panelesEquipos.get(indiceEquipo);
        panelEquipo.resaltarJugador(indiceJugador);
    }
    
    /**
     * Metodo para desresaltar un jugador en especifico
     * 
     * @param indiceEquipo
     * @param indiceJugador 
     */
    public void desResaltarJugador(int indiceEquipo, int indiceJugador) {
        PanelEquipo panelEquipo = panelesEquipos.get(indiceEquipo);;
        panelEquipo.desResaltarJugador(indiceJugador);
    }
    
    /**
     * Metodo para cambiar el nombre del archivo propiedades escogido
     * @param nombre 
     */
    public void setNombreArchivoProp(String nombre) {
        panelArchivos.setLblArchivoPropEscogido(nombre);
    }
    
    /**
     * Metodo para cambiar el nombre del archivo serializado escogido
     * 
     * @param nombre 
     */
    public void setNombreArchivoBin(String nombre) {
        panelArchivos.setLblArchivoBinEscogido(nombre);
    }

    /**
     * Metodo para obtener el origen desde donde se cargan los datos
     * de los archivos cargados
     * 
     * @return 
     */
    public String getSeleccionOrigenCarga() {
        return panelArchivos.getOpcionSeleccionada();
    }
    
    /**
     * Metodo para mostrar la opcion de cargar un archivo serializado
     */
    public void activarEleccionDeArchivoSerializado() {
        panelArchivos.mostrarOpcionSerializador();
    }
    
    /**
     * Metodo para mostrar algun mensaje en PanelArchivos
     * 
     * @param mensaje 
     */
    public void mostrarMensajeArchivo(String mensaje) {
        panelArchivos.setLblMensaje(mensaje);
    }

    /**
     * Metodo para actualizar puntaje de equipo en especifico
     * 
     * @param indice
     * @param puntaje 
     */
    public void setPuntajeEquipo(int indice, int puntaje) {
        PanelEquipo pEquipo = panelesEquipos.get(indice);;
        pEquipo.cambiarPuntajeEquipo(puntaje);
    }
    
    /**
     * Metodo para cambiar el nombre de un equipo en especifico
     * 
     * @param indice
     * @param nombre 
     */
    public void setNombreEquipo(int indice, String nombre) {
        panelesEquipos.get(indice).setNombreEquipo(nombre);        
    }
    
    /**
     * Metodo para obtener un PanelEquipo en especifico
     * 
     * @param indice
     * @return PanelEqupo
     */
    public PanelEquipo getPanelEquipo(int indice) {
        return panelesEquipos.get(indice);
    }
    
    /**
     * Metodo que obtine los paneles de los equipos creados
     * 
     * @return arraylist de PanelEquipo
     */
    public ArrayList<PanelEquipo> getPanelesEquipos() {
        return panelesEquipos;
    }
}
