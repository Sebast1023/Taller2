package udistrital.avanzada.taller2.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import udistrital.avanzada.taller2.modelo.Equipo;
import udistrital.avanzada.taller2.modelo.Jugador;
import udistrital.avanzada.taller2.vista.Ventana;

/**
 *
 * @author Diego
 * @version 1.1
 * @since 30/09/2025
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

    public void actualizarEquipos(Equipo equipoA, Equipo equipoB) {
        // Actualizar títulos de los paneles (TitledBorder)
        ventana.setTituloEquipoA(equipoA.getNombre());
        ventana.setTituloEquipoB(equipoB.getNombre());

        // Opcional: actualizar puntajes (si quieres empezar en 0)
        ventana.lblPuntajeA.setText("Puntaje: " + equipoA.getPuntaje());
        ventana.lblPuntajeB.setText("Puntaje: " + equipoB.getPuntaje());

        // Jugadores del Equipo A
        Jugador[] jugadoresA = equipoA.getJugadores();
        for (int i = 0; i < ventana.lblNombresA.length; i++) {
            if (jugadoresA != null && i < jugadoresA.length && jugadoresA[i] != null) {
                ventana.lblNombresA[i].setText(
                        jugadoresA[i].getNombre() + " (" + jugadoresA[i].getApodo() + ")"
                );
            } else {
                ventana.lblNombresA[i].setText("Jugador " + (i + 1));
            }
        }

        // Jugadores del Equipo B
        Jugador[] jugadoresB = equipoB.getJugadores();
        for (int i = 0; i < ventana.lblNombresB.length; i++) {
            if (jugadoresB != null && i < jugadoresB.length && jugadoresB[i] != null) {
                ventana.lblNombresB[i].setText(
                        jugadoresB[i].getNombre() + " (" + jugadoresB[i].getApodo() + ")"
                );
            } else {
                ventana.lblNombresB[i].setText("Jugador " + (i + 1));
            }
        }
    }

    private void lanzarArgolla() {
        ventana.areaMensajes.append(">> Lanzamiento realizado...\n");
    }

    private void nuevaRonda() {
        ventana.mostrarMensajeEmergente("Se inicia una nueva ronda.");
        ventana.areaMensajes.setText("");
    }

    private void salir() {
        int opcion = JOptionPane.showConfirmDialog(
                ventana,
                "¿Seguro que deseas salir del juego?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            ventana.setVisible(false);
            ventana.dispose();
            System.exit(0);
        }
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
