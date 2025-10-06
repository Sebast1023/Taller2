package udistrital.avanzada.taller2.vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

/**
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

    public Ventana(String title) {
        super(title);
        setSize(1000, 600);
        setLocationRelativeTo(null); // centrar
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

        // ===== Panel centro con equipos y mensajes =====
        panelEquipos = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.add(panelEquipos);

        panelEquipos.setAlignmentX(Component.LEFT_ALIGNMENT);
        wrapper.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelScroll = new JScrollPane(
                wrapper,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
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

        // Evento cierre
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                JOptionPane.showMessageDialog(null,
                        "Usa el botón 'Salir' para cerrar el juego.",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
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

    // Métodos para mensajes emergentes
    public void mensajeEmergente(Component componente, String mensaje) {
        JOptionPane.showMessageDialog(componente, mensaje);
    }

    public void mostrarMensajeEmergente(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // metodo para mostrar en la consola
    public void mostrarEnConsola(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Imprime en consola los resultados almacenados en el archivo aleatorio.
     *
     * @param resultados
     */
    public void mostrarResultadosEnConsola(List<String> resultados) {
        System.out.println("\n====================================");
        System.out.println("        RESULTADOS GUARDADOS        ");
        System.out.println("====================================");
        for (String registro : resultados) {
            System.out.println(registro);
        }
        System.out.println("====================================\n");
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

    public PanelEquipo agregarEquipo(String titulo) {
        PanelEquipo panelEquipo = new PanelEquipo(titulo, Color.green);
        panelEquipos.add(panelEquipo);
        return panelEquipo;
    }

    public void mostrarEquipos() {
        cardLayout.show(panelCentro, "Equipos");
        panelCentro.revalidate();
        panelCentro.repaint();
        mostrarBotonesJuego();
    }

    public void mostrarBotonesJuego() {
        panelBotones.removeAll();
        panelBotones.add(btnTerminar, BorderLayout.WEST);
        panelBotones.add(btnLanzar, BorderLayout.EAST);
        panelBotones.revalidate();
        panelBotones.repaint();
    }

    public void mostrarBotonesTerminar() {
        panelBotones.removeAll();
        panelBotones.add(btnTerminar, BorderLayout.WEST);
        panelBotones.add(btnNuevaRonda, BorderLayout.EAST);
        panelBotones.revalidate();
        panelBotones.repaint();
    }

    public void mostrarBotonSalir() {
        panelBotones.removeAll();
        panelBotones.add(btnSalir, BorderLayout.WEST);
        panelBotones.revalidate();
        panelBotones.repaint();
    }

    public void resaltarJugador(int indiceEquipo, int indiceJugador) {
        PanelEquipo panelEquipo = (PanelEquipo) panelEquipos.getComponent(indiceEquipo);
        panelEquipo.resaltarJugador(indiceJugador);
    }

    public void desResaltarJugador(int indiceEquipo, int indiceJugador) {
        PanelEquipo panelEquipo = (PanelEquipo) panelEquipos.getComponent(indiceEquipo);
        panelEquipo.desResaltarJugador(indiceJugador);
    }

    public void setNombreArchivoProp(String nombre) {
        panelArchivos.setLblArchivoPropEscogido(nombre);
    }

    public void setNombreArchivoBin(String nombre) {
        panelArchivos.setLblArchivoBinEscogido(nombre);
    }

    public String getSeleccionOrigenCarga() {
        return panelArchivos.getOpcionSeleccionada();
    }

    public void activarEleccionDeArchivoSerializado() {
        panelArchivos.mostrarOpcionSerializador();
    }

    public void mostrarMensajeArchivo(String mensaje) {
        panelArchivos.setLblMensaje(mensaje);
    }

    public void setPuntajeEquipo(int indice, int puntaje) {
        PanelEquipo pEquipo = (PanelEquipo) panelEquipos.getComponent(indice);
        pEquipo.cambiarPuntajeEquipo(puntaje);

    }

    public Component[] getPanelesEquipos() {
        return panelEquipos.getComponents();
    }
}
