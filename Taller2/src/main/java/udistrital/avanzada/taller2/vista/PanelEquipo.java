package udistrital.avanzada.taller2.vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Panel de equipo rediseñado con formato 2x2 para jugadores.
 */
public class PanelEquipo extends JPanel {

    private JPanel contenedorJugadores;
    private PanelJugador[] jugadores;
    private JLabel lblPuntaje;
    private TitledBorder tituloBorde;

    public PanelEquipo(String nombreEquipo, Color colorBorde, Color colorPuntaje) {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(255, 250, 240));

        tituloBorde = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(colorBorde, 3, true),
                nombreEquipo,
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 20),
                colorBorde
        );
        setBorder(tituloBorde);

        // Contenedor 2x2 para los jugadores
        contenedorJugadores = new JPanel(new GridLayout(2, 2, 8, 8));
        contenedorJugadores.setOpaque(false);

        jugadores = new PanelJugador[4];

        lblPuntaje = new JLabel("Puntaje: 0", JLabel.CENTER);
        lblPuntaje.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblPuntaje.setForeground(colorPuntaje);

        add(contenedorJugadores, BorderLayout.CENTER);
        add(lblPuntaje, BorderLayout.SOUTH);
    }

    public void agregarJugador(String nombre, int indice) {
        PanelJugador pj = new PanelJugador(nombre, indice);
        jugadores[indice] = pj;
        contenedorJugadores.add(pj);
    }

    public void resaltarJugador(int indice) {
        jugadores[indice].resaltar();
    }

    public void desResaltarJugador(int indice) {
        jugadores[indice].desResaltar();
    }

    public void cambiarPuntajeEquipo(int puntaje) {
        lblPuntaje.setText("Puntaje: " + puntaje);
    }

    public void setNombreEquipo(String nombre) {
        tituloBorde.setTitle(nombre);
        revalidate();
        repaint();
    }

    public void setDatosJugador(int indice, String nombre, String apodo) {
        jugadores[indice].setNombre(nombre);
    }

    public void setFotoJugador(int indice, ImageIcon foto) {
        jugadores[indice].setFoto(foto);
    }
}
