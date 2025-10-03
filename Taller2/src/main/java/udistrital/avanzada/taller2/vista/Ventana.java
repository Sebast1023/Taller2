package udistrital.avanzada.taller2.vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.JFileChooser;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

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

    // Paneles de Equipos
    private JPanel panelEquipoA;
    private JPanel panelEquipoB;

    // Botones
    public JButton btnLanzar;
    public JButton btnNuevaRonda;
    public JButton btnSalir;

    // Etiquetas de puntajes
    public JLabel lblPuntajeA;
    public JLabel lblPuntajeB;

    // Área de mensajes
    public JTextArea areaMensajes;

    // Arreglos para los jugadores (A y B)
    public JLabel[] lblFotosA;
    public JLabel[] lblNombresA;
    public JLabel[] lblFotosB;
    public JLabel[] lblNombresB;

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
        panelCentro = new JPanel(new GridLayout(1, 3, 15, 0));
        panelCentro.setBackground(new Color(245, 245, 245));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Panel Equipo A (nombre en el borde, puntaje abajo)
        panelEquipoA = crearPanelEquipo("Equipo A", new Color(139, 69, 19));
        lblPuntajeA = new JLabel("Puntaje: 0", JLabel.CENTER);
        lblPuntajeA.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblPuntajeA.setForeground(new Color(139, 69, 19));
        panelEquipoA.add(lblPuntajeA);

        panelEquipoB = crearPanelEquipo("Equipo B", new Color(0, 100, 0));
        lblPuntajeB = new JLabel("Puntaje: 0", JLabel.CENTER);
        lblPuntajeB.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblPuntajeB.setForeground(new Color(0, 100, 0));
        panelEquipoB.add(lblPuntajeB);

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

        // Añadir al centro
        panelCentro.add(panelEquipoA);
        panelCentro.add(panelMensajes);
        panelCentro.add(panelEquipoB);

        // ===== Panel botones =====
        panelBotones = new JPanel(new BorderLayout());

        btnSalir = crearBoton("❌ Salir", new Color(178, 34, 34));
        btnLanzar = crearBoton("🎲 Lanzar Argolla", new Color(70, 130, 180));
        btnNuevaRonda = crearBoton("🔄 Nueva Ronda", new Color(34, 139, 34));

        btnSalir.setActionCommand("Salir");
        btnLanzar.setActionCommand("Lanzar");
        btnNuevaRonda.setActionCommand("NuevaRonda");

        panelBotones.add(btnSalir, BorderLayout.WEST);
        panelBotones.add(btnLanzar, BorderLayout.CENTER);
        panelBotones.add(btnNuevaRonda, BorderLayout.EAST);

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
     * Método para crear un panel de equipo con espacio para 4 jugadores
     */
    private JPanel crearPanelEquipo(String titulo, Color colorBorde) {
        JPanel panelEquipo = new JPanel();
        panelEquipo.setLayout(new BoxLayout(panelEquipo, BoxLayout.Y_AXIS));
        panelEquipo.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(colorBorde, 3, true),
                titulo,
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 20),
                colorBorde
        ));
        panelEquipo.setBackground(new Color(255, 250, 240));

        // Espacios para 4 jugadores
        JPanel contenedorJugadores = new JPanel(new GridLayout(4, 1, 8, 8));
        contenedorJugadores.setBackground(new Color(255, 250, 240));

        JLabel[] fotos = new JLabel[4];
        JLabel[] nombres = new JLabel[4];

        for (int i = 0; i < 4; i++) {
            JPanel jugadorPanel = new JPanel(new BorderLayout(5, 5));
            jugadorPanel.setBackground(new Color(255, 255, 255));
            jugadorPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));

            fotos[i] = new JLabel("📷", JLabel.CENTER);
            fotos[i].setPreferredSize(new Dimension(80, 80));
            fotos[i].setOpaque(true);
            fotos[i].setBackground(new Color(230, 230, 230));
            fotos[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));

            nombres[i] = new JLabel("Jugador " + (i + 1), JLabel.CENTER);
            nombres[i].setFont(new Font("SansSerif", Font.PLAIN, 14));

            jugadorPanel.add(fotos[i], BorderLayout.CENTER);
            jugadorPanel.add(nombres[i], BorderLayout.SOUTH);

            contenedorJugadores.add(jugadorPanel);
        }

        panelEquipo.add(contenedorJugadores);

        // Guardar referencias en variables globales
        if (titulo.contains("A")) {
            lblFotosA = fotos;
            lblNombresA = nombres;
        } else {
            lblFotosB = fotos;
            lblNombresB = nombres;
        }

        return panelEquipo;
    }

    /**
     * Actualiza el título del panel del equipo A (TitledBorder).
     */
    public void setTituloEquipoA(String titulo) {
        if (panelEquipoA != null && panelEquipoA.getBorder() instanceof TitledBorder) {
            TitledBorder tb = (TitledBorder) panelEquipoA.getBorder();
            tb.setTitle(titulo);
            panelEquipoA.repaint();
            panelEquipoA.revalidate();
        }
    }

    /**
     * Actualiza el título del panel del equipo B (TitledBorder).
     */
    public void setTituloEquipoB(String titulo) {
        if (panelEquipoB != null && panelEquipoB.getBorder() instanceof TitledBorder) {
            TitledBorder tb = (TitledBorder) panelEquipoB.getBorder();
            tb.setTitle(titulo);
            panelEquipoB.repaint();
            panelEquipoB.revalidate();
        }
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
    * metodo para obtener la instancia de JFileChooser con la cual se escogeran
    * archivos de precarga
    * @return retorna un JFileChooser
    */
    public JFileChooser obtenerFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        //Carpeta donde se guardan archivos de precarga y poscarga
        File archivo = new File("Specs/data");
        // Que se abra en la carpeta data predeterminadamente
        fileChooser.setCurrentDirectory(carpetaInicial);
        return fileChooser;
    }
}
