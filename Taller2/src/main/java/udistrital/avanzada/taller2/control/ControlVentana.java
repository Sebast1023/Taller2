/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import udistrital.avanzada.taller2.vista.Ventana;

/**
 *
 * @author mauri
 */
public class ControlVentana implements ActionListener {

    private ControlPrincipal logica;
    private Ventana ventana;

    public ControlVentana(ControlPrincipal controlPrincipal) {
        this.logica = controlPrincipal;
        ventana = new Ventana("Juego de la Argolla");

        // registrar listeners
        ventana.btnLanzar.addActionListener(this);
        ventana.btnNuevaRonda.addActionListener(this);
        ventana.btnSalir.addActionListener(this);
    }
    
    private void lanzarArgolla() {
        ventana.areaMensajes.append(">> Lanzamiento realizado...\n");
    }

    private void nuevaRonda() {
        ventana.mostrarMensajeEmergente("Se inicia una nueva ronda.");
        ventana.areaMensajes.setText("");
    }

    private void salir() {
        ventana.setVisible(false);
        ventana.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Lanzar".equals(e.getActionCommand())) {
            lanzarArgolla();
        }
        if ("NuevaRonda".equals(e.getActionCommand())) {
            nuevaRonda();
        }
        if ("Salir".equals(e.getActionCommand())) {
            salir();
        }
    }
}
