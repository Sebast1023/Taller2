/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mauri
 */
public class PanelJugador extends JPanel {

    public JLabel foto;
    public JLabel nombre;

    public PanelJugador(String nombres, int indice) {
        this.setLayout(new BorderLayout(5, 5));
        this.setBackground(new Color(255, 255, 255));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        this.setPreferredSize(new Dimension(120, 80));

        this.foto = new JLabel("📷", JLabel.CENTER);
        this.foto.setPreferredSize(new Dimension(100, 100));
        this.foto.setOpaque(true);
        this.foto.setBackground(new Color(235, 235, 235));
        this.foto.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));

        nombre = new JLabel(nombres + (indice + 1), JLabel.CENTER);
        nombre.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        this.add(this.foto, BorderLayout.CENTER);
        this.add(nombre, BorderLayout.SOUTH);
    }

    public void resaltar() {
        this.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
        this.repaint();
    }

    public void desResaltar() {
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        this.repaint();
    }

    public void setNombre(String nombre) {
        this.nombre.setText(nombre);
    }

    public void setFoto(ImageIcon foto) {
        this.foto.setIcon(foto);
    }
}
