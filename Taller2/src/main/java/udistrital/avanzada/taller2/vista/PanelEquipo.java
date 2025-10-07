package udistrital.avanzada.taller2.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author mauri
 */
public class PanelEquipo extends JPanel {

    private JPanel contenedorJugadores;
    private PanelJugador[] jugadores;
    private JLabel lblPuntaje;
    private TitledBorder titledBorderNombreEquipo;

    public PanelEquipo(String titulo, Color colorBorde, Color colorPuntaje) {
        jugadores = new PanelJugador[4];
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        titledBorderNombreEquipo = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(colorBorde, 3, true),
                titulo,
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 20),
                colorBorde
        );

        this.setBorder(BorderFactory.createCompoundBorder(titledBorderNombreEquipo,
                BorderFactory.createEmptyBorder(10, 40, 10, 40)
        )
        );
        this.setBackground(new Color(255, 250, 240));
        // Espacio para 4 jugadores
        contenedorJugadores = new JPanel(new GridLayout(4, 1, 8, 8));
        contenedorJugadores.setBackground(new Color(255, 250, 240));

        lblPuntaje = new JLabel("Puntaje: 0", JLabel.CENTER);
        lblPuntaje.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblPuntaje.setForeground(new Color(0, 100, 0));

        this.add(Box.createVerticalStrut(10));
        this.add(contenedorJugadores);
        this.add(lblPuntaje);
        this.add(Box.createVerticalStrut(5));
        
    }

    public void agregarJugador(String Nombre, int indice) {
        PanelJugador panelJugador = new PanelJugador(Nombre, indice);
        contenedorJugadores.add(panelJugador);
        jugadores[indice] = panelJugador;
        contenedorJugadores.revalidate();
        contenedorJugadores.repaint();
        this.revalidate();
        this.repaint();
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

    public void setNombreEquipo(String titulo) {
        titledBorderNombreEquipo.setTitle(titulo);
        this.revalidate();
        this.repaint();
    }

    public void setDatosJugador(int indice, String Nombre, String Apodo) {
        jugadores[indice].setNombre(Nombre);
    }

    public void setFotoJugador(int indice, ImageIcon foto) {
        jugadores[indice].setFoto(foto);
    }
}
