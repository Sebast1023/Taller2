package udistrital.avanzada.taller2.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author mauri
 */
public class PanelEquipo extends JPanel {

    JPanel contenedorJugadores;
    PanelJugador[] jugadores;
    JLabel lblPuntaje;

    public PanelEquipo(String titulo, Color colorBorde) {
        jugadores = new PanelJugador[4];
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(colorBorde, 3, true),
                    titulo,
                    TitledBorder.CENTER,
                    TitledBorder.TOP,
                    new Font("SansSerif", Font.BOLD, 20),
                    colorBorde
                ),
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
        
        
        this.add(contenedorJugadores);
        this.add(lblPuntaje);        
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
        lblPuntaje.setText("Puntaje: "+puntaje);
    }
}
