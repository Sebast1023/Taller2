/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.vista;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author mauri
 */
public class PanelArchivos extends JPanel{
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
    
    public PanelArchivos(JButton btnArchivoProperties,JButton btnArchivoBin) {
        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
        btnArchivoProperties.setPreferredSize(new Dimension(120, 40));
        btnArchivoBin.setPreferredSize(new Dimension(120, 40));
        
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.X_AXIS));
        JPanel contenedor1 = new JPanel();
        contenedor1.setLayout(new BoxLayout(contenedor1, BoxLayout.Y_AXIS));
        JPanel contenedor2 = new JPanel();
        contenedor2.setLayout(new BoxLayout(contenedor2, BoxLayout.Y_AXIS));
        
        JPanel contenedorEleccion = new JPanel();
        contenedorEleccion.setLayout(new BoxLayout(contenedorEleccion, BoxLayout.Y_AXIS));
        
        JPanel contenedorOpciones = new JPanel();
        contenedorOpciones.setLayout(new BoxLayout(contenedorOpciones, BoxLayout.Y_AXIS));        
        
        lblArchivoBinEscogido = new JLabel("Sin seleccionar");
        lblArchivoPropEscogido = new JLabel("Sin seleccionar");
        lblTitulo = new JLabel("Seleccione lo requerido");
        lblEleccion = new JLabel("Seleccione desde donde desea cargar los datos");
        lblMensaje = new JLabel("");
        lblMensaje.setAlignmentX(lblTitulo.CENTER_ALIGNMENT);
        
        panelHeader.add(lblTitulo);
        lblTitulo.setAlignmentX(lblTitulo.CENTER_ALIGNMENT);        
        
        contenedor1.add(lblArchivoPropEscogido);
        contenedor1.add(btnArchivoProperties);
        lblArchivoPropEscogido.setAlignmentX(lblArchivoPropEscogido.CENTER_ALIGNMENT);
        btnArchivoProperties.setAlignmentX(btnArchivoProperties.CENTER_ALIGNMENT);
        
        contenedor2.add(lblArchivoBinEscogido);
        contenedor2.add(btnArchivoBin);        
        lblArchivoBinEscogido.setAlignmentX(lblArchivoBinEscogido.CENTER_ALIGNMENT);
        btnArchivoBin.setAlignmentX(btnArchivoBin.CENTER_ALIGNMENT);
        contenedor2.setVisible(false);
        
        panelContenido.add(contenedor1);
        panelContenido.add(contenedor2);                

        opcion1 = new JRadioButton("Propiedades");
        opcion2 = new JRadioButton("Serializado");
        opcion1.setActionCommand("Propiedades");
        opcion2.setActionCommand("Serializado");
        opcion1.addActionListener(e ->lblMensaje.setText(""));
        opcion2.addActionListener(e ->lblMensaje.setText(""));

        // Grupo de botones (solo uno puede estar seleccionado)
        grupo = new ButtonGroup();
        grupo.add(opcion1);
        grupo.add(opcion2);

        // Valor predefinido propeidades
        opcion1.setSelected(true);
               
        contenedorOpciones.add(opcion1);
        contenedorOpciones.add(opcion2);
        opcion1.setAlignmentX(opcion1.CENTER_ALIGNMENT);
        opcion2.setAlignmentX(opcion2.CENTER_ALIGNMENT);
        
        contenedorEleccion.add(lblEleccion);
        lblEleccion.setAlignmentX(lblEleccion.CENTER_ALIGNMENT);
        contenedorEleccion.add(contenedorOpciones);
        contenedorEleccion.setVisible(false);
        
        this.add(panelHeader);
        this.add(panelContenido);        
        this.add(contenedorEleccion);
        this.add(lblMensaje);
        this.contenedorEleccion = contenedorEleccion;
        this.contenedorElegirSerializado = contenedor2;
        
    }
    
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
