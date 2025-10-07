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
 * Clase equipo que además de su diseño base maneja los paneles jugador
 * 
 * @author Mauricio
 * @since 04/10/2025
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
    
    /**
     * Metodo para agregar un panelJugador a la lista de paneles y mostrarlo
     * 
     * @param Nombre del jugador
     * @param indice donde insertaremos el panel
     */
    public void agregarJugador(String Nombre, int indice) {
        PanelJugador panelJugador = new PanelJugador(Nombre, indice);
        contenedorJugadores.add(panelJugador);
        jugadores[indice] = panelJugador;
        contenedorJugadores.revalidate();
        contenedorJugadores.repaint();
        this.revalidate();
        this.repaint();
    }
    
    /**
     * Metodo para resaltar un jugadro en especifico
     * 
     * @param indice 
     */
    public void resaltarJugador(int indice) {
        jugadores[indice].resaltar();
    }
    
    /**
     * Metodo para desresaltar un jugadro en especifico
     * 
     * @param indice 
     */
    public void desResaltarJugador(int indice) {
        jugadores[indice].desResaltar();
    }
    
    /**
     * Actualizar puntaje de equipo en interfaz
     * 
     * @param puntaje 
     */
    public void cambiarPuntajeEquipo(int puntaje) {
        lblPuntaje.setText("Puntaje: " + puntaje);
    }

    public void setNombreEquipo(String titulo) {
        titledBorderNombreEquipo.setTitle(titulo);
        this.revalidate();
        this.repaint();
    }
    
    /**
     * Metodo para cambiar los datos mostrados del jugador
     * 
     * @param indice panel a actualizar
     * @param Nombre del jugador
     * @param Apodo del jugador
     */
    public void setDatosJugador(int indice, String Nombre, String Apodo) {
        jugadores[indice].setNombre(Nombre);
    }
    
    /**
     * Metodo para actualizar foto de jugador
     * 
     * @param indice jugador a actualizar
     * @param foto imagen ImageIcon
     */
    public void setFotoJugador(int indice, ImageIcon foto) {
        jugadores[indice].setFoto(foto);
    }
}
