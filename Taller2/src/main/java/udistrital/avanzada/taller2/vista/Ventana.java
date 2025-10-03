/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.vista;

import javax.swing.JFrame;

/**
 *
 * @author 
 * @since 30/09/2025
 */
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Ventana extends JFrame {

    // Paneles principales
    private JPanel panelTitulo;
    private JPanel panelEquipos;
    private JPanel panelBotones;
    private JPanel panelCentro;

    // Botones
    public JButton btnLanzar;
    public JButton btnNuevaRonda;
    public JButton btnSalir;

    // Etiquetas de equipos
    public JLabel lblEquipoA;
    public JLabel lblEquipoB;
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

        // ===== Panel título =====
        panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(210, 180, 140)); // café claro
        JLabel texto = new JLabel("🎯 Juego de la Argolla 🎯", JLabel.CENTER);
        texto.setFont(new Font("Serif", Font.BOLD, 28));
        texto.setForeground(new Color(80, 40, 20));
        panelTitulo.add(texto);

        // ===== Panel centro con equipos y mensajes =====
        panelCentro = new JPanel(new GridLayout(1, 3, 15, 0));
        panelCentro.setBackground(new Color(245, 245, 245));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Panel Equipo A
        JPanel panelA = crearPanelEquipo("Equipo A", new Color(139, 69, 19));
        lblEquipoA = new JLabel("Equipo A");
        lblPuntajeA = new JLabel("Puntaje: 0");
        panelA.add(lblEquipoA);
        panelA.add(lblPuntajeA);

        // Panel Equipo B
        JPanel panelB = crearPanelEquipo("Equipo B", new Color(0, 100, 0));
        lblEquipoB = new JLabel("Equipo B");
        lblPuntajeB = new JLabel("Puntaje: 0");
        panelB.add(lblEquipoB);
        panelB.add(lblPuntajeB);

        // ===== Panel mensajes en el centro =====
        JPanel panelMensajes = new JPanel(new BorderLayout());
        panelMensajes.setBorder(BorderFactory.createTitledBorder("Resultados del Juego"));
        areaMensajes = new JTextArea(15, 30);
        areaMensajes.setEditable(false);
        areaMensajes.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaMensajes);
        panelMensajes.add(scroll, BorderLayout.CENTER);

        // Añadir al centro
        panelCentro.add(panelA);
        panelCentro.add(panelMensajes);
        panelCentro.add(panelB);

        // ===== Panel botones =====
        panelBotones = new JPanel(new BorderLayout());

        btnSalir = new JButton("❌ Salir");
        btnLanzar = new JButton("🎲 Lanzar Argolla");
        btnNuevaRonda = new JButton("🔄 Nueva Ronda");

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
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
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
                BorderFactory.createLineBorder(colorBorde, 2, true),
                titulo,
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 18),
                colorBorde
        ));
        panelEquipo.setBackground(new Color(255, 250, 240));

        // Espacios para 4 jugadores
        JPanel contenedorJugadores = new JPanel(new GridLayout(4, 1, 5, 5));
        contenedorJugadores.setBackground(new Color(255, 250, 240));

        JLabel[] fotos = new JLabel[4];
        JLabel[] nombres = new JLabel[4];

        for (int i = 0; i < 4; i++) {
            JPanel jugadorPanel = new JPanel(new BorderLayout());
            jugadorPanel.setBackground(new Color(255, 250, 240));

            fotos[i] = new JLabel("[Foto]", JLabel.CENTER);
            fotos[i].setPreferredSize(new Dimension(80, 60));

            nombres[i] = new JLabel("Jugador " + (i + 1), JLabel.CENTER);

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
    
    public void mensajeEmergente(Component componente, String mensaje){
        JOptionPane.showMessageDialog(componente, "Se inicia una nueva ronda.");
    }
    public void mostrarMensajeEmergente(String mensaje){
        JOptionPane.showMessageDialog(this, "Se inicia una nueva ronda.");
    }
}
