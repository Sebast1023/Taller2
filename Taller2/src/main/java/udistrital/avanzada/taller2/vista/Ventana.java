package udistrital.avanzada.taller2.vista;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Interfaz principal del Juego de la Argolla Llanera
 *
 * @author Diego
 * @version 1.8
 * @since 04/10/2025
 * @modificación Estilo madera, efecto de jugador activo, mensajes en español y
 * popup de lanzamiento.
 * @modificación 1.4 Ajuste de diseño visual: cuadrícula 2x2 para jugadores,
 * botones centrados, márgenes balanceados, colores más suaves y tamaño general
 * optimizado.
 * @modificación 1.5 🎨 Botones con degradado, puntajes centrados, sombreados
 * suaves.
 * @modificación 1.7 🪶 Diseño final sin imágenes de fondo, con Swing puro,
 * márgenes equilibrados, título con borde elegante y botones con estilo estable
 * y redondeado.
 * @modificación 1.8 ✅ Se reincorporan métodos setTituloEquipoA/B y comentarios
 * de control.
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

    // Jugadores
    public JLabel[] lblFotosA;
    public JLabel[] lblNombresA;
    public JLabel[] lblFotosB;
    public JLabel[] lblNombresB;

    // --- Constructor ---
    public Ventana(String title) {
        super(title);

        // Configuración idioma botones JOptionPane
        UIManager.put("OptionPane.yesButtonText", "Sí");
        UIManager.put("OptionPane.noButtonText", "No");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");

        setSize(1100, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));
        setVisible(true);

        inicializarComponentes();
    }

    private void inicializarComponentes() {

        // ===== Panel título =====
        panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(190, 160, 120));
        panelTitulo.setPreferredSize(new Dimension(1100, 90));
        panelTitulo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(90, 60, 30)),
                BorderFactory.createEmptyBorder(15, 10, 10, 10)
        ));

        JLabel texto = new JLabel("🎯 Juego de la Argolla Llanera 🎯", JLabel.CENTER);
        texto.setFont(new Font("Serif", Font.BOLD, 36));
        texto.setForeground(new Color(60, 30, 10));
        texto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 50, 20), 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        texto.setOpaque(true);
        texto.setBackground(new Color(220, 190, 140));
        panelTitulo.add(texto, BorderLayout.CENTER);

        // ===== Panel central =====
        panelCentro = new JPanel(new GridLayout(1, 3, 15, 0));
        panelCentro.setBackground(new Color(245, 235, 220));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));

        panelEquipoA = crearPanelEquipo("Equipo A", new Color(139, 69, 19));
        panelEquipoB = crearPanelEquipo("Equipo B", new Color(0, 100, 0));

        // Puntajes centrados y balanceados
        lblPuntajeA = crearPuntajeLabel(new Color(139, 69, 19));
        lblPuntajeB = crearPuntajeLabel(new Color(0, 100, 0));

        JPanel contPuntA = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        contPuntA.setOpaque(false);
        contPuntA.add(lblPuntajeA);
        panelEquipoA.add(Box.createVerticalStrut(10));
        panelEquipoA.add(contPuntA);

        JPanel contPuntB = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        contPuntB.setOpaque(false);
        contPuntB.add(lblPuntajeB);
        panelEquipoB.add(Box.createVerticalStrut(10));
        panelEquipoB.add(contPuntB);

        // Panel de mensajes
        JPanel panelMensajes = new JPanel(new BorderLayout());
        panelMensajes.setBackground(new Color(250, 245, 235));
        panelMensajes.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(120, 80, 40), 3, true),
                "📜 Resultados del Juego",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 17),
                new Color(80, 50, 20)
        ));
        areaMensajes = new JTextArea(15, 30);
        areaMensajes.setEditable(false);
        areaMensajes.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaMensajes.setBackground(new Color(255, 252, 245));
        JScrollPane scroll = new JScrollPane(areaMensajes);
        panelMensajes.add(scroll, BorderLayout.CENTER);

        panelCentro.add(panelEquipoA);
        panelCentro.add(panelMensajes);
        panelCentro.add(panelEquipoB);

        // ===== Panel botones =====
        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 15));
        panelBotones.setBackground(new Color(242, 232, 216));
        panelBotones.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, new Color(100, 70, 40)));

        btnSalir = crearBotonSuave("❌ Salir", new Color(178, 34, 34), new Color(220, 70, 70));
        btnLanzar = crearBotonSuave("🎲 Lanzar Argolla", new Color(65, 105, 225), new Color(100, 149, 237));
        btnNuevaRonda = crearBotonSuave("🔄 Nueva Ronda", new Color(34, 139, 34), new Color(60, 179, 113));

        btnSalir.setActionCommand("Salir");
        btnLanzar.setActionCommand("Lanzar");
        btnNuevaRonda.setActionCommand("NuevaRonda");

        panelBotones.add(btnSalir);
        panelBotones.add(btnLanzar);
        panelBotones.add(btnNuevaRonda);

        // Ensamblar
        this.add(panelTitulo, BorderLayout.NORTH);
        this.add(panelCentro, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);

        // Confirmación al cerrar
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                int opcion = JOptionPane.showConfirmDialog(
                        Ventana.this,
                        "¿Seguro que deseas salir del juego?",
                        "Confirmar salida",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (opcion == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    // ===== MÉTODOS AUXILIARES =====
    private JLabel crearPuntajeLabel(Color color) {
        JLabel label = new JLabel("Puntaje: 0", JLabel.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setForeground(color.darker());
        label.setOpaque(true);
        label.setBackground(new Color(255, 248, 240));
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2, true),
                BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        return label;
    }

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

        JPanel contenedorJugadores = new JPanel(new GridLayout(2, 2, 10, 10));
        contenedorJugadores.setBackground(new Color(255, 250, 240));

        JLabel[] fotos = new JLabel[4];
        JLabel[] nombres = new JLabel[4];

        for (int i = 0; i < 4; i++) {
            JPanel jugadorPanel = new JPanel(new BorderLayout(5, 5));
            jugadorPanel.setBackground(new Color(255, 255, 255));
            jugadorPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));

            fotos[i] = new JLabel("", JLabel.CENTER);
            fotos[i].setPreferredSize(new Dimension(100, 100));
            fotos[i].setOpaque(true);
            fotos[i].setBackground(new Color(235, 235, 235));

            // Cargar imagen del jugador
            String prefijo = titulo.contains("A") ? "jugadorA" : "jugadorB";
            String nombreArchivo = prefijo + (i + 1) + ".png";
            ImageIcon foto = cargarFotoJugador(nombreArchivo);

            if (foto != null) {
                fotos[i].setIcon(foto);
            } else {
                fotos[i].setText("📷");
            }

            nombres[i] = new JLabel("Jugador " + (i + 1), JLabel.CENTER);
            nombres[i].setFont(new Font("SansSerif", Font.PLAIN, 14));

            jugadorPanel.add(fotos[i], BorderLayout.CENTER);
            jugadorPanel.add(nombres[i], BorderLayout.SOUTH);
            contenedorJugadores.add(jugadorPanel);
        }

        panelEquipo.add(Box.createVerticalStrut(10));
        panelEquipo.add(contenedorJugadores);
        panelEquipo.add(Box.createVerticalStrut(5));

        if (titulo.contains("A")) {
            lblFotosA = fotos;
            lblNombresA = nombres;
        } else {
            lblFotosB = fotos;
            lblNombresB = nombres;
        }
        return panelEquipo;
    }

    // 🎨 Botones redondeados con degradado y hover estable
    private JButton crearBotonSuave(String texto, Color base1, Color base2) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                boolean hover = getModel().isRollover();
                Color c1 = hover ? base2.brighter() : base1;
                Color c2 = hover ? base1.brighter() : base2;
                g2.setPaint(new GradientPaint(0, 0, c1, 0, getHeight(), c2));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.setColor(new Color(0, 0, 0, 40));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 25, 25);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        boton.setContentAreaFilled(false);
        boton.setOpaque(false);
        boton.setFocusPainted(false);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("SansSerif", Font.BOLD, 16));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        return boton;
    }

    // ===== MÉTODOS EXTRA DE TITULOS DE EQUIPO =====
    /**
     * Actualiza el título del panel del equipo A (TitledBorder)
     */
    public void setTituloEquipoA(String titulo) {
        if (panelEquipoA != null && panelEquipoA.getBorder() instanceof TitledBorder tb) {
            tb.setTitle(titulo);
            panelEquipoA.repaint();
        }
    }

    /**
     * Actualiza el título del panel del equipo B (TitledBorder)
     */
    public void setTituloEquipoB(String titulo) {
        if (panelEquipoB != null && panelEquipoB.getBorder() instanceof TitledBorder tb) {
            tb.setTitle(titulo);
            panelEquipoB.repaint();
        }
    }

    // ===== FUNCIONES VISUALES =====
    public void resaltarJugador(char equipo, int indice) {
        restaurarJugadores();
        JLabel[] fotos = (equipo == 'A') ? lblFotosA : lblFotosB;
        JLabel[] nombres = (equipo == 'A') ? lblNombresA : lblNombresB;

        if (fotos == null || nombres == null || indice < 0 || indice >= fotos.length) {
            return;
        }

        for (int i = 0; i < fotos.length; i++) {
            if (i != indice) {
                fotos[i].setBackground(new Color(210, 210, 210));
                nombres[i].setForeground(Color.GRAY);
            }
        }

        fotos[indice].setBackground(new Color(255, 230, 180));
        fotos[indice].setBorder(BorderFactory.createLineBorder(new Color(160, 82, 45), 3));
        nombres[indice].setForeground(new Color(139, 69, 19));
        fotos[indice].repaint();
        nombres[indice].repaint();
    }

    public void restaurarJugadores() {
        restaurar(lblFotosA, lblNombresA);
        restaurar(lblFotosB, lblNombresB);
    }

    private void restaurar(JLabel[] fotos, JLabel[] nombres) {
        if (fotos == null || nombres == null) {
            return;
        }
        for (int i = 0; i < fotos.length; i++) {
            fotos[i].setBackground(new Color(230, 230, 230));
            fotos[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
            nombres[i].setForeground(Color.BLACK);
        }
    }

    // ===== MÉTODOS DE MENSAJES =====
    public void mostrarResultadoLanzamiento(String nombreJugador, String nombreEquipo, String tipo, int puntos) {
        String msg = "🎯 Jugador: " + nombreJugador + " (" + nombreEquipo + ")\n"
                + "Resultado: " + tipo + " (+" + puntos + " puntos)";
        JOptionPane.showMessageDialog(this, msg, "Resultado del lanzamiento", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarMensajeEmergente(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }

    // ===== CARGA DE IMAGEN =====
    private ImageIcon cargarFotoJugador(String nombreArchivo) {
        String ruta = "Specs/data/images/" + nombreArchivo;
        java.net.URL url = getClass().getClassLoader().getResource(ruta);
        if (url == null) {
            url = getClass().getClassLoader().getResource("Specs/data/images/predeterminada.png");
        }
        if (url != null) {
            ImageIcon icono = new ImageIcon(url);
            Image img = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        return null;
    }
}
