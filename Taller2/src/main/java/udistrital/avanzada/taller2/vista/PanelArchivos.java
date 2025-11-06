package udistrital.avanzada.taller2.vista;

import java.awt.*;
import javax.swing.*;

/**
 * Panel inicial de selección de archivos, rediseñado con estética moderna
 * y colores cálidos tipo "madera clara".
 *
 * @author Mauricio
 * @modificado Por ChatGPT (diseño mejorado 2025-10)
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

        // Fondo general
        setLayout(new BorderLayout());
        setBackground(new Color(250, 245, 235)); // tono cálido claro

        // Contenedor central
        JPanel contenedorPrincipal = new JPanel();
        contenedorPrincipal.setLayout(new BoxLayout(contenedorPrincipal, BoxLayout.Y_AXIS));
        contenedorPrincipal.setOpaque(true);
        contenedorPrincipal.setBackground(new Color(255, 253, 247));
        contenedorPrincipal.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 150, 100), 2, true),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        // Título principal
        lblTitulo = new JLabel("⚙️ Configuración de Archivos", JLabel.CENTER);
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(90, 60, 20));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblMensaje = new JLabel(" ");
        lblMensaje.setForeground(new Color(180, 0, 0));
        lblMensaje.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Labels de archivos
        lblArchivoPropEscogido = new JLabel("Sin seleccionar", JLabel.CENTER);
        lblArchivoBinEscogido = new JLabel("Sin seleccionar", JLabel.CENTER);
        lblArchivoPropEscogido.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblArchivoBinEscogido.setFont(new Font("SansSerif", Font.PLAIN, 14));

        // Botones uniformes
        btnArchivoProperties.setPreferredSize(new Dimension(220, 40));
        btnArchivoBin.setPreferredSize(new Dimension(220, 40));

        btnArchivoProperties.setBackground(new Color(70, 130, 180));
        btnArchivoProperties.setForeground(Color.WHITE);
        btnArchivoBin.setBackground(new Color(70, 130, 180));
        btnArchivoBin.setForeground(Color.WHITE);

        btnArchivoProperties.setFocusPainted(false);
        btnArchivoBin.setFocusPainted(false);

        // Panel de botones de archivos
        JPanel panelArchivos = new JPanel(new GridLayout(2, 1, 10, 10));
        panelArchivos.setOpaque(false);
        panelArchivos.add(crearFilaArchivo("Archivo Properties", lblArchivoPropEscogido, btnArchivoProperties));
        panelArchivos.add(crearFilaArchivo("Archivo Serializado", lblArchivoBinEscogido, btnArchivoBin));

        // Elección del origen
        lblEleccion = new JLabel("Seleccione desde dónde desea cargar los datos", JLabel.CENTER);
        lblEleccion.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblEleccion.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblEleccion.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));

        opcion1 = new JRadioButton("Propiedades");
        opcion2 = new JRadioButton("Serializado");
        opcion1.setActionCommand("Propiedades");
        opcion2.setActionCommand("Serializado");
        opcion1.setOpaque(false);
        opcion2.setOpaque(false);

        grupo = new ButtonGroup();
        grupo.add(opcion1);
        grupo.add(opcion2);
        opcion1.setSelected(true);

        JPanel panelRadios = new JPanel();
        panelRadios.setOpaque(false);
        panelRadios.add(opcion1);
        panelRadios.add(opcion2);

        contenedorEleccion = new JPanel();
        contenedorEleccion.setLayout(new BoxLayout(contenedorEleccion, BoxLayout.Y_AXIS));
        contenedorEleccion.setOpaque(false);
        contenedorEleccion.add(lblEleccion);
        contenedorEleccion.add(panelRadios);
        contenedorEleccion.setVisible(false);

        // Ensamblar todo
        contenedorPrincipal.add(lblTitulo);
        contenedorPrincipal.add(Box.createVerticalStrut(20));
        contenedorPrincipal.add(panelArchivos);
        contenedorPrincipal.add(Box.createVerticalStrut(20));
        contenedorPrincipal.add(contenedorEleccion);
        contenedorPrincipal.add(Box.createVerticalStrut(10));
        contenedorPrincipal.add(lblMensaje);

        add(contenedorPrincipal, BorderLayout.CENTER);
    }

    // Método auxiliar para crear filas de archivo + etiqueta
    private JPanel crearFilaArchivo(String titulo, JLabel lblArchivo, JButton boton) {
        JPanel fila = new JPanel();
        fila.setOpaque(false);
        fila.setLayout(new BoxLayout(fila, BoxLayout.Y_AXIS));

        JLabel lbl = new JLabel(titulo, JLabel.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 15));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblArchivo.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);

        fila.add(lbl);
        fila.add(Box.createVerticalStrut(5));
        fila.add(lblArchivo);
        fila.add(Box.createVerticalStrut(5));
        fila.add(boton);

        return fila;
    }

    // === Métodos originales conservados ===

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
        contenedorEleccion.setVisible(true);
        revalidate();
        repaint();
    }

    public void setLblMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
    }
}
