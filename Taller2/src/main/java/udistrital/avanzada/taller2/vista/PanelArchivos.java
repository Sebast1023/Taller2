/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Panel que muestra la selección de archivos del juego de la Argolla, con una
 * apariencia visual moderna, sin alterar su estructura ni lógica original.
 *
 * @author mauri
 */
public class PanelArchivos extends JPanel {

    private JLabel lblArchivoBinEscogido;
    private JLabel lblArchivoPropEscogido;
    private JLabel lblTitulo;
    private JLabel lblMensaje;
    private JLabel lblEleccion;
    private JRadioButton opcion1;
    private JRadioButton opcion2;
    private ButtonGroup grupo;
    private JPanel contenedorEleccion;
    private JPanel contenedorElegirSerializado;

    public PanelArchivos(JButton btnArchivoProperties, JButton btnArchivoBin) {

        // 🎨 Colores y fuentes
        Color fondo = new Color(247, 247, 247);
        Color primario = new Color(230, 120, 40);
        Color texto = new Color(30, 30, 30);
        Font fuenteTitulo = new Font("Segoe UI", Font.BOLD, 22);
        Font fuenteTexto = new Font("Segoe UI", Font.PLAIN, 15);

        setBackground(fondo);
        setBorder(new EmptyBorder(40, 100, 40, 100));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // 🟠 Título principal
        lblTitulo = new JLabel("Seleccione lo requerido");
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setForeground(primario);
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);

        // 📁 Paneles contenedores
        JPanel panelHeader = new JPanel();
        panelHeader.setOpaque(false);
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));

        JPanel panelContenido = new JPanel();
        panelContenido.setOpaque(false);
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.X_AXIS));
        panelContenido.setBorder(new EmptyBorder(30, 0, 30, 0));

        JPanel contenedor1 = new JPanel();
        contenedor1.setOpaque(false);
        contenedor1.setLayout(new BoxLayout(contenedor1, BoxLayout.Y_AXIS));

        JPanel contenedor2 = new JPanel();
        contenedor2.setOpaque(false);
        contenedor2.setLayout(new BoxLayout(contenedor2, BoxLayout.Y_AXIS));

        JPanel contenedorOpciones = new JPanel();
        contenedorOpciones.setOpaque(false);
        contenedorOpciones.setLayout(new BoxLayout(contenedorOpciones, BoxLayout.Y_AXIS));

        contenedorEleccion = new JPanel();
        contenedorEleccion.setOpaque(false);
        contenedorEleccion.setLayout(new BoxLayout(contenedorEleccion, BoxLayout.Y_AXIS));

        // 📋 Etiquetas
        lblArchivoBinEscogido = new JLabel("Sin seleccionar");
        lblArchivoPropEscogido = new JLabel("Sin seleccionar");
        lblArchivoBinEscogido.setFont(fuenteTexto);
        lblArchivoPropEscogido.setFont(fuenteTexto);
        lblArchivoBinEscogido.setForeground(Color.GRAY);
        lblArchivoPropEscogido.setForeground(Color.GRAY);

        lblEleccion = new JLabel("Seleccione desde donde desea cargar los datos");
        lblEleccion.setFont(fuenteTexto);
        lblEleccion.setAlignmentX(CENTER_ALIGNMENT);

        lblMensaje = new JLabel("");
        lblMensaje.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        lblMensaje.setForeground(new Color(90, 90, 90));
        lblMensaje.setAlignmentX(CENTER_ALIGNMENT);

        // 🧩 Botones (ajuste automático al texto)
        btnArchivoProperties.setBackground(new Color(0, 120, 215));
        btnArchivoProperties.setForeground(Color.WHITE);
        btnArchivoProperties.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnArchivoProperties.setFocusPainted(false);
        btnArchivoProperties.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnArchivoProperties.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnArchivoProperties.setAlignmentX(CENTER_ALIGNMENT);
        btnArchivoProperties.setMaximumSize(new Dimension(250, 45));

        btnArchivoBin.setBackground(new Color(0, 120, 215));
        btnArchivoBin.setForeground(Color.WHITE);
        btnArchivoBin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnArchivoBin.setFocusPainted(false);
        btnArchivoBin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnArchivoBin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnArchivoBin.setAlignmentX(CENTER_ALIGNMENT);
        btnArchivoBin.setMaximumSize(new Dimension(250, 45));

        // 📦 Organización interna (igual que en tu versión)
        contenedor1.add(lblArchivoPropEscogido);
        contenedor1.add(Box.createRigidArea(new Dimension(0, 10)));
        contenedor1.add(btnArchivoProperties);
        lblArchivoPropEscogido.setAlignmentX(CENTER_ALIGNMENT);

        contenedor2.add(lblArchivoBinEscogido);
        contenedor2.add(Box.createRigidArea(new Dimension(0, 10)));
        contenedor2.add(btnArchivoBin);
        lblArchivoBinEscogido.setAlignmentX(CENTER_ALIGNMENT);
        contenedor2.setVisible(false);

        panelContenido.add(Box.createHorizontalGlue());
        panelContenido.add(contenedor1);
        panelContenido.add(Box.createRigidArea(new Dimension(50, 0)));
        panelContenido.add(contenedor2);
        panelContenido.add(Box.createHorizontalGlue());

        // 🎚 Radio buttons
        opcion1 = new JRadioButton("Propiedades");
        opcion2 = new JRadioButton("Serializado");
        opcion1.setActionCommand("Propiedades");
        opcion2.setActionCommand("Serializado");
        opcion1.addActionListener(e -> lblMensaje.setText(""));
        opcion2.addActionListener(e -> lblMensaje.setText(""));

        opcion1.setFont(fuenteTexto);
        opcion2.setFont(fuenteTexto);
        opcion1.setOpaque(false);
        opcion2.setOpaque(false);

        grupo = new ButtonGroup();
        grupo.add(opcion1);
        grupo.add(opcion2);
        opcion1.setSelected(true);

        contenedorOpciones.add(opcion1);
        contenedorOpciones.add(Box.createRigidArea(new Dimension(0, 5)));
        contenedorOpciones.add(opcion2);
        opcion1.setAlignmentX(CENTER_ALIGNMENT);
        opcion2.setAlignmentX(CENTER_ALIGNMENT);

        contenedorEleccion.add(lblEleccion);
        contenedorEleccion.add(Box.createRigidArea(new Dimension(0, 10)));
        contenedorEleccion.add(contenedorOpciones);
        contenedorEleccion.setVisible(false);

        // 🧭 Ensamblaje final (idéntico al original)
        panelHeader.add(lblTitulo);
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);

        add(panelHeader);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(panelContenido);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(contenedorEleccion);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(lblMensaje);

        this.contenedorEleccion = contenedorEleccion;
        this.contenedorElegirSerializado = contenedor2;
    }

    // Métodos idénticos
    public void setLblArchivoBinEscogido(String nombre) {
        lblArchivoBinEscogido.setText(nombre);
    }

    public void setLblArchivoPropEscogido(String nombre) {
        lblArchivoPropEscogido.setText(nombre);
    }

    public String getOpcionSeleccionada() {
        return grupo.getSelection().getActionCommand();
    }

    public void mostrarOpcionSerializador() {
        this.contenedorEleccion.setVisible(true);
        this.contenedorElegirSerializado.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void setLblMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
    }
}
