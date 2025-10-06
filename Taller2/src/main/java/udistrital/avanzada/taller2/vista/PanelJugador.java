/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
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
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        this.setPreferredSize(new Dimension(120, 80));

        foto = new JLabel("📷", JLabel.CENTER);
        foto.setPreferredSize(new Dimension(80, 80));
        foto.setOpaque(true);
        foto.setBackground(new Color(230, 230, 230));
        foto.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));

        nombre = new JLabel(nombres + (indice + 1), JLabel.CENTER);
        nombre.setFont(new Font("SansSerif", Font.PLAIN, 14));

        this.add(foto, BorderLayout.CENTER);
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

}
