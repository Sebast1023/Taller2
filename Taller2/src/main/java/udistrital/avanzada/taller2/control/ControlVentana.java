package udistrital.avanzada.taller2.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import udistrital.avanzada.taller2.modelo.Equipo;
import udistrital.avanzada.taller2.modelo.Jugador;
import udistrital.avanzada.taller2.modelo.Tiro;
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
        // Validaciones básicas
        if (logica == null || logica.getControlEquipo() == null) {
            ventana.areaMensajes.append("Error: controlador no inicializado.\n");
            return;
        }

        java.util.ArrayList<Equipo> equipos = logica.getControlEquipo().getEquipos();
        if (equipos == null || equipos.size() < 2) {
            ventana.areaMensajes.append("Se necesitan al menos 2 equipos para jugar.\n");
            return;
        }

        // Por ahora jugamos entre los dos primeros equipos (es lo que pide el taller)
        Equipo equipo = equipos.get(0);
        Equipo equipo2 = equipos.get(1);

        // Elegir aleatoriamente qué equipo lanza (0 -> equipoA, 1 -> equipoB)
        int equipoIndex = new java.util.Random().nextInt(2);
        Equipo equipoActual = (equipoIndex == 0) ? equipo : equipo2;

        // Elegir un jugador válido del equipoActual
        Jugador[] jugadores = equipoActual.getJugadores();
        if (jugadores == null) {
            ventana.areaMensajes.append("Equipo " + equipoActual.getNombre() + " no tiene jugadores.\n");
            return;
        }
        java.util.List<Integer> valid = new java.util.ArrayList<>();
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i] != null) {
                valid.add(i);
            }
        }
        if (valid.isEmpty()) {
            ventana.areaMensajes.append("No hay jugadores válidos en " + equipoActual.getNombre() + ".\n");
            return;
        }
        int jugadorIndex = valid.get(new java.util.Random().nextInt(valid.size()));
        Jugador jugador = jugadores[jugadorIndex];

        // Resaltar visualmente al jugador activo
        ventana.resaltarJugador(equipoIndex == 0 ? 'A' : 'B', jugadorIndex);

        // Pedir a ControlPrincipal que simule el tiro
        Tiro tiro = logica.simularLanzamiento();
        if (tiro == null) {
            ventana.areaMensajes.append("No hay tipos de tiro cargados.\n");
            // restaurar después de 1s
            new Timer(1000, ev -> ventana.restaurarJugadores()).start();
            return;
        }

        // Actualizar puntaje en modelo (usar ControlEquipo -> respeto a MVC)
        logica.getControlEquipo().agregarPuntos(equipoActual, tiro.getPuntos());

        // Actualizar vista de puntajes
        if (equipoIndex == 0) {
            ventana.lblPuntajeA.setText("Puntaje: " + equipoActual.getPuntaje());
        } else {
            ventana.lblPuntajeB.setText("Puntaje: " + equipoActual.getPuntaje());
        }

        // Mostrar resultado (usa el método de la vista si existe)
        // si Ventana tiene mostrarResultadoLanzamiento, úsalo:
        try {
            ventana.mostrarResultadoLanzamiento(jugador.getNombre(), equipoActual.getNombre(), tiro.getNombre(), tiro.getPuntos());
        } catch (Throwable ex) {
            // fallback si no existe el método
            JOptionPane.showMessageDialog(ventana,
                    "🎯 Jugador: " + jugador.getNombre() + " (" + equipoActual.getNombre() + ")\n"
                    + "Resultado: " + tiro.getNombre() + " (+" + tiro.getPuntos() + " puntos)",
                    "Resultado del lanzamiento",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        // Registrar en área de mensajes
        ventana.areaMensajes.append("→ " + jugador.getNombre() + " (" + equipoActual.getNombre()
                + "): " + tiro.getNombre() + " (+" + tiro.getPuntos() + ")\n");

        // Restaurar estilos después de 1.2s (timer sin repetir)
        Timer t = new Timer(1200, ev -> ventana.restaurarJugadores());
        t.setRepeats(false);
        t.start();

        // Comprobar si alguno llegó a 21 puntos (regla de finalización simple)
        if (equipoActual.getPuntaje() >= 21) {
            String ganador = equipoActual.getNombre();
            JOptionPane.showMessageDialog(ventana,
                    "¡Fin del juego! Ganador: " + ganador,
                    "Juego terminado",
                    JOptionPane.INFORMATION_MESSAGE);
            // aquí podrías guardar resultados o deshabilitar botones
        }
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
