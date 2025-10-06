package udistrital.avanzada.taller2.control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Logica de negocio
 *
 * @author Diego
 * @version 1.1
 * @since 30/09/2025
 */
public class ControlPrincipal {

    private ControlVentana controlVentana;
    private ControlEquipo controlEquipo;
    private ControlTiro controlTiro;
    private ControlConexion controlConexion;
    // empatados nos ayuda a saber que equipos estan empatados
    private ArrayList<Integer> empatados;
    // jugadoresAnteriores nos ayuda a saber quien jugo antes en una ronda de empate para que no se repita en la siguiente
    // Tendra la misma cantidad de elementos que empatados, asi por ronda de empate se tiene el indice
    // del equipo en empatados y el indice del jugador que jugo esa ronda
    private ArrayList<Integer> jugadoresAnteriores;
    private ControlJugador controlJugador;
    private int partidasJugadas;
    // Equipo en turno
    private int equipoTurnoActual;
    // Jugador en turno
    private int jugadorTurnoActual;
    // patidas maximas
    private int partidasMaximas;
    // el puntaje mas alto sacado en la ejecucion del juego
    private int maximoPuntajeActual;
    // el puntaje mas alto sacado en la ejecucion del juego
    private boolean empate;

    ;

    public ControlPrincipal() {
        // inicializar variables logicas
        this.partidasJugadas = 0;
        this.jugadorTurnoActual = 0;
        this.equipoTurnoActual = 0;
        this.partidasMaximas = 2;
        this.empatados = new ArrayList<>();
        this.maximoPuntajeActual = 0;
        this.empate = false;
        // 
        this.jugadoresAnteriores = new ArrayList<>();
        // inicializar controladores base
        this.controlEquipo = new ControlEquipo();
        this.controlTiro = new ControlTiro();
        this.controlJugador = new ControlJugador();

        // crear conexion (inyección simple)
        this.controlConexion = new ControlConexion(controlEquipo, controlTiro, controlJugador);
        this.controlVentana = new ControlVentana(this);

        // Si hay un archivo serializado de equipos en Specs/data entonces mostrar
        // menu de archivos para cargar los dos archivos solicitados .Properties y .bin
        // y escoga desde donde quiere carga la informacion de los equipos
        if (controlConexion.existeArchivoSerializadoEquipos()) {
            controlVentana.mostrarMenuArchivos();
        }
    }

    private void iniciarJuego() {
    }

    public void nuevaMano() {
        // Si ya se jugaron todas las partidas salir de la funcion
        if (partidasJugadas == partidasMaximas) {
            return;
        }
        // empate falso para que suceda el lanzamiento normal
        empate = false;
        // resetear variables auxiliares
        this.jugadorTurnoActual = 0;
        this.equipoTurnoActual = 0;
        this.maximoPuntajeActual = 0;
        // resetear listas auxiliares para empate
        this.empatados.clear();
        this.jugadoresAnteriores.clear();
        // resetear puntaje de todos los equipos a cero
        controlEquipo.resetearPuntaje();
        //TODO actualizar vista para restear todos los puntjaes a cero

        // Vaciar lista empatados 
        empatados.clear();
    }

    public void lanzarArgolla() {
        if (partidasJugadas >= partidasMaximas) {
            return;
        }
        String[] jugador = controlEquipo.obtenerNombresJugador(equipoTurnoActual, jugadorTurnoActual);
        // Un movimiento aletario
        int numeroAleatorio = obtenerNumeroRandom(controlTiro.getTamaño());
        // obtenemos nombre y puntaje
        String nombreTiro = controlTiro.getNombreTiro(numeroAleatorio);
        int puntaje = controlTiro.getPuntajeTiro(numeroAleatorio);
        // sumar a equipo
        int puntajeEquipoActual = controlEquipo.agregarPuntos(equipoTurnoActual, puntaje);

        controlVentana.setPuntajeEquipo(equipoTurnoActual, puntajeEquipoActual);

        // Pintar jugada en ventana
        controlVentana.mostraMensajeEmergente(
                jugador[0]
                + "\n"
                + jugador[1]
                + "\nhizo "
                + nombreTiro
                + " obteniendo "
                + puntaje
                + " puntos"
        );

        //Proximo turno        
        int turnoMaxEquip = controlEquipo.getTamaño() - 1;
        int turnoMaxJugador = 3;

        controlVentana.desResaltarJugador(equipoTurnoActual, jugadorTurnoActual);

        // Estamos en el ultimo turno del equipo        
        if (jugadorTurnoActual == turnoMaxJugador) {
            if (puntajeEquipoActual > maximoPuntajeActual) {
                maximoPuntajeActual = puntajeEquipoActual;
                empatados.clear();
                empatados.add(equipoTurnoActual);
            } else if (maximoPuntajeActual == puntajeEquipoActual) {
                empatados.add(equipoTurnoActual);
            }

        }

        // si todavia hay gente con turno en el equipo pasar al siguiente
        if (jugadorTurnoActual < turnoMaxJugador) {
            jugadorTurnoActual += 1;
            controlVentana.resaltarJugador(equipoTurnoActual, jugadorTurnoActual);
            return;
        }

        // verificamos si estamos en el ultimo turno de la ronda
        if (jugadorTurnoActual == turnoMaxJugador && equipoTurnoActual == turnoMaxEquip) {
            // Si algun equipo logro 21 o más mirar si hay empate
            if (maximoPuntajeActual >= 21) {
                empate();
            } else {
                // reiniciar turnos para que todos lanzen de nuevo                
                this.equipoTurnoActual = 0;
                this.jugadorTurnoActual = 0;
                controlVentana.resaltarJugador(equipoTurnoActual, jugadorTurnoActual);
            }
            return;
        }
        // pasar turno a siguiente equipo
        equipoTurnoActual += 1;
        // Reiniciar turno de jugador para que pase por todos los jugadores del nuevo equipo a jugar
        jugadorTurnoActual = 0;
        controlVentana.resaltarJugador(equipoTurnoActual, jugadorTurnoActual);

    }

    public void lanzarArgollaEmpate() {
        if (partidasJugadas >= partidasMaximas) {
            return;
        }

        // obtenemos el indice del equipo actual que esta guardado en empatados
        int indiceEquipoActual = empatados.get(equipoTurnoActual);
        // indice del jugador que lanza
        int indiceJugador;
        // Se genera un indice random para que juege uno del equipo aleatoriamente
        if (!jugadoresAnteriores.isEmpty()) {
            do {
                indiceJugador = obtenerNumeroRandom(4);
                // verificar si jugador ya jugo en la anterior ronda de empate
            } while (indiceJugador == jugadoresAnteriores.get(equipoTurnoActual));
        } else {
            indiceJugador = obtenerNumeroRandom(4);
        }
        controlVentana.resaltarJugador(indiceEquipoActual, indiceJugador);

        String[] jugador = controlEquipo.obtenerNombresJugador(indiceEquipoActual, indiceJugador);
        //Guardamos el inddice del jugador en el indice correspondiente
        if (equipoTurnoActual < jugadoresAnteriores.size() && equipoTurnoActual > 0) {
            jugadoresAnteriores.set(equipoTurnoActual, indiceJugador);
        } else {
            jugadoresAnteriores.add(indiceJugador);
        }
        // Un movimiento aletario
        int numeroAleatorio = obtenerNumeroRandom(controlTiro.getTamaño());
        // obtenemos nombre y puntaje
        String nombreTiro = controlTiro.getNombreTiro(numeroAleatorio);
        int puntaje = controlTiro.getPuntajeTiro(numeroAleatorio);
        // sumar a equipo
        int puntajeEquipoActual = controlEquipo.agregarPuntos(indiceEquipoActual, puntaje);

        if (puntajeEquipoActual > maximoPuntajeActual) {
            maximoPuntajeActual = puntajeEquipoActual;
        }

        // Actualizar puntaje equipo en ventana puntajeActual
        controlVentana.setPuntajeEquipo(equipoTurnoActual, puntajeEquipoActual);
        // Pintar jugada en ventana
        controlVentana.mostraMensajeEmergente(
                jugador[0]
                + "\n"
                + jugador[1]
                + "\nhizo "
                + nombreTiro
                + " obteniendo "
                + puntaje
                + " puntos"
        );

        controlVentana.desResaltarJugador(indiceEquipoActual, indiceJugador);
        // Verificar si se termino la ronda de empate
        if (equipoTurnoActual == empatados.size() - 1) {
            // Quitamos equipos que no tenga el maximo puntaje
            ArrayList<Integer> nuevosEmpatados = new ArrayList<>();
            for (int i = 0; i < empatados.size(); i++) {
                if (controlEquipo.getPuntajeEquipo(i) == maximoPuntajeActual) {
                    nuevosEmpatados.add(i);
                    jugadoresAnteriores.remove(i);
                }
            }
            empatados = nuevosEmpatados;
            // verificar si hay empate otra vez
            empate();
            return;
        }
        // pasar el turno al siguiente equipo
        if (equipoTurnoActual < empatados.size() - 1) {
            equipoTurnoActual += 1;
            controlVentana.resaltarJugador(equipoTurnoActual, indiceJugador);
        }
    }

    public void lanzar() {
        if (true) {
            lanzarArgolla();
        } else {
            lanzarArgollaEmpate();
        }
    }

    private void empate() {
        // No hay ni un ganador entonces salir de la funcion
        if (empatados.isEmpty()) {
            return;
        }
        // Reiniciamos las variables de turno
        jugadorTurnoActual = 0;
        equipoTurnoActual = 0;
        // Si hay empate jugar hasta desempate
        if (empatados.size() > 1) {
            controlVentana.mostraMensajeEmergente("Hay empate");
            //Cambiar el actionCommand del boton lanzar a LanzarEmpate
            controlVentana.resaltarJugador(equipoTurnoActual, jugadorTurnoActual);
            // activar modo de lanzamiento empate
            empate = true;
            return;
        }
               // TODO Si no hay empate entonces mostrar equipo ganador
        // TODO guardar info en archivo aleatorio
        // ---------------------------------------------------------
        // Guardar resultados de la ronda en el archivo aleatorio:
        // Para cada equipo se grabará "GANÓ" si su puntaje == maximoPuntajeActual
        // en caso contrario "PERDIÓ".
        try {
            int totalEquipos = controlEquipo.getTamaño();
            for (int idx = 0; idx < totalEquipos; idx++) {
                // obtener equipo por indice
                udistrital.avanzada.taller2.modelo.Equipo eq = controlEquipo.getEquipos().get(idx);
                String resultadoEquipo = (controlEquipo.getPuntajeEquipo(idx) == maximoPuntajeActual) ? "GANO" : "PERDIO";
                controlConexion.guardarResultado(eq, resultadoEquipo);
            }
        } catch (Exception ex) {
            System.err.println("Error guardando resultados: " + ex.getMessage());
        }

        // actualizar conteo de partidas y mostrar menús
        this.partidasJugadas += 1;
        // activar modo para lanzamiento normal
        empate = false;
        if (partidasJugadas >= partidasMaximas) {
            controlVentana.mostraMenuSalir();
            // mostar boton salir, ocultar boton de otra partida            
        } else {
            // mostrar boton terminar y otra partida
            controlVentana.mostraMenuTerminar();
        }
        return;
    }

    /**
     * Método para obtener numero random
     *
     * @param limite hasta que numero se requiere inclusivo
     * @return
     */
    private int obtenerNumeroRandom(int limite) {
        Random numAleatorio;
        numAleatorio = new Random();
        int numero = numAleatorio.nextInt(limite);
        return numero;
    }

    /**
     * Metodo para inicializar precarga de datos
     *
     * @param propiedades archivo .properties
     * @param serializado archivo .bin
     * @param origen 1 para cargar equipos desde propiedades y 2 para cargar
     * desde serializado
     */
    public void cargarArchivosPrecarga(File propiedades, File serializado, int origen) {
        if (serializado == null && origen == 2) {
            //TODO Mostrar que debe escoger el serilizado si quiere cargar desde ahi
        }
        // Delegamos a controlConexion
        // Si el serializador no existe cargar todo desde properties
        if (serializado == null && origen == 1) {
            controlConexion.cargarArchivoProperties(propiedades, true);
        } else {
            // Se deben cargar los equipos desde serializador
            controlConexion.cargarArchivoProperties(propiedades, false);
            controlConexion.cargarArchivoSerializable(serializado, true);
        }
        // Verificamos si podemos iniciar juego
        if (controlEquipo.getTamaño() >= 2 && controlTiro.getTamaño() >= 2) {
            controlVentana.mostrarEquipos();
            for (int i = 0; i < controlEquipo.getTamaño(); i++) {
                String nombre = controlEquipo.obtenerNombreEquipo(i);
                String[] nombres = controlJugador.obtenerNombresJugadores(controlEquipo.obtenerJugadores(i));
                String[] apodos = controlJugador.obtenerApodosJugadores(controlEquipo.obtenerJugadores(i));
                controlVentana.AgregarEquipo(nombre, nombres, apodos);
            }
            controlVentana.resaltarJugador(equipoTurnoActual, jugadorTurnoActual);
        } else {
            // Resetear lista equipos y puntajes
            controlEquipo.borrarTodo();
            controlTiro.borrarTodo();
            controlVentana.mostrarMensajeArchivo("Cargue archivos con datos validos");
        }

    }
    public void salir() {
        // Guardamos equipos antes de salir del juego
        if (controlEquipo.getTamaño() > 0) {
            controlConexion.serializarEquipos(controlEquipo.getEquipos());
        }
        //Mostrar los datos del archivo aleatorio
        List<String> resultados = controlConexion.obtenerResultados();
        controlVentana.mostrarResultadosConsola(resultados);
        controlConexion.cerrar();
        System.exit(0);
    }

}
