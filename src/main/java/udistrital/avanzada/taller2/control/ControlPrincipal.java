package udistrital.avanzada.taller2.control;

import udistrital.avanzada.taller2.vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControlPrincipal implements ActionListener {

    private VentanaPrincipal ventana;

    public ControlPrincipal() {
        ventana = new VentanaPrincipal("Juego de la Argolla");

        // registrar listeners
        ventana.btnLanzar.addActionListener(this);
        ventana.btnNuevaRonda.addActionListener(this);
        ventana.btnSalir.addActionListener(this);
    }

    private void lanzarArgolla() {
        ventana.areaMensajes.append(">> Lanzamiento realizado...\n");
    }

    private void nuevaRonda() {
        JOptionPane.showMessageDialog(ventana, "Se inicia una nueva ronda.");
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
