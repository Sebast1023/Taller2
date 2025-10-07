package udistrital.avanzada.taller2.vista;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Panel visual de cada jugador, con diseño moderno y mejor distribución.
 *
 * @author Mauricio
 * @modificado por Diego
 */
public class PanelJugador extends JPanel {

    private JLabel foto;
    private JLabel nombre;

    public PanelJugador(String nombres, int indice) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(140, 140));
        setBackground(new Color(255, 255, 255, 230)); // blanco translúcido
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 170, 120), 2, true),
                new EmptyBorder(8, 8, 8, 8)
        ));

        foto = new JLabel("", JLabel.CENTER);
        foto.setPreferredSize(new Dimension(100, 100));
        foto.setOpaque(true);
        foto.setBackground(new Color(240, 235, 220));
        foto.setBorder(BorderFactory.createLineBorder(new Color(180, 150, 100), 2, true));

        try {
            ImageIcon icono = new ImageIcon(getClass().getResource("/images/jugador.png"));
            Image imagenEscalada = icono.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
            foto.setIcon(new ImageIcon(imagenEscalada));
        } catch (Exception e) {
            foto.setText("📷");
            System.err.println("⚠️ No se encontró la imagen predeterminada: /images/jugador.png");
        }

        nombre = new JLabel(nombres, JLabel.CENTER);
        nombre.setFont(new Font("SansSerif", Font.BOLD, 13));
        nombre.setForeground(new Color(60, 40, 20));
        nombre.setBorder(new EmptyBorder(5, 0, 0, 0));

        add(foto, BorderLayout.CENTER);
        add(nombre, BorderLayout.SOUTH);
    }

    public void resaltar() {
        setBorder(BorderFactory.createLineBorder(new Color(255, 80, 80), 3, true));
    }

    public void desResaltar() {
        setBorder(BorderFactory.createLineBorder(new Color(200, 170, 120), 2, true));
    }

    public void setNombre(String nombre) {
        this.nombre.setText(nombre);
    }

    public void setFoto(ImageIcon imagen) {
        foto.setIcon(imagen);
    }
}
