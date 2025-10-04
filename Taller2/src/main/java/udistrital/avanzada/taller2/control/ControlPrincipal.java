package udistrital.avanzada.taller2.control;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import udistrital.avanzada.taller2.modelo.Equipo;
import udistrital.avanzada.taller2.modelo.ConexionProperties;

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
    private int maximoPuntajeActual;;

    public ControlPrincipal() {
        // inicializar variables logicas
        this.partidasJugadas = 0;
        this.jugadorTurnoActual = 0;
        this.equipoTurnoActual = 0;
        this.partidasMaximas = 2;
        this.empatados = new ArrayList<>();
        this.maximoPuntajeActual = 0;
        // 
        this.jugadoresAnteriores = new ArrayList<>();
        // inicializar controladores base
        this.controlEquipo = new ControlEquipo();
        this.controlTiro = new ControlTiro();
        this.controlJugador = new ControlJugador();        
        

        // crear conexion (inyección simple)
        this.controlConexion = new ControlConexion(controlEquipo, controlTiro);
        this.controlVentana = new ControlVentana(this);
        // cargar configuracion.properties
        boolean cargado = controlConexion.cargarDesdeArchivo();
        if (!cargado) {
            System.err.println("Warning: No se cargó configuracion.properties (verifica la ruta Specs/data/configuracion.properties).");
        }        
        // inicializar la vista con los equipos ya cargados
        iniciarEquipos();
    }

    private void iniciarJuego() {
        // por implementar: flujo de partida
    }
    
    public void nuevaMano() {
        // Si ya se jugaron todas las partidas salir de la funcion
        if (partidasJugadas == partidasMaximas) {
            return;
        }
        // resetear turnos 
        this.jugadorTurnoActual = 0;
        this.equipoTurnoActual = 0;
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
        
        if (puntajeEquipoActual > maximoPuntajeActual) {
            maximoPuntajeActual = puntajeEquipoActual;
        }
        
        if (empatados.isEmpty()) {
            empatados.add(equipoTurnoActual);
        } else {          
            if (maximoPuntajeActual < puntajeEquipoActual) {
                empatados.clear();
                empatados.add(equipoTurnoActual);                                
            } else if (maximoPuntajeActual == puntajeEquipoActual) {
                empatados.add(equipoTurnoActual);
            }
        }
        // TODO actualizar puntaje equipo en ventana puntajeActual
        
        // Pintar jugada en ventana
        controlVentana.mostraMensajeEmergente(jugador[0] + "-" + jugador[1]
                + "hizo " + nombreTiro + " obteniendo " + puntaje + " puntos"
        );        
        
        //Proximo turno        
        int turnoMaxEquip = controlEquipo.obtenerTamaño()-1;
        int turnoMaxJugador = 3;
        // verificamos si estamos en el ultimo turno de la ronda
        if (jugadorTurnoActual == turnoMaxJugador && equipoTurnoActual == turnoMaxEquip){
            // Si algun equipo logro 21 o más mirar si hay empate
            if (maximoPuntajeActual >= 21) {
                empate();    
            } else {
                // reiniciar turnos para que todos lanzen de nuevo
                this.equipoTurnoActual = 0;
                this.jugadorTurnoActual = 0;
            }
            return;
        }
        // si todavia hay gente con turno en el equipo pasar al siguiente
        if (jugadorTurnoActual < turnoMaxJugador) {
            // TODO desresalatar jugador anterior
            jugadorTurnoActual += 1;
            // TODO resaltar nuevo jugador
        } else {
            // Estamos en el ultimo turno del equipo
            // pasar turno a siguiente equipo
            equipoTurnoActual += 1;
            // Reiniciar turno de jugador para que pase por todos los jugadores del nuevo equipo a jugar
            // TODO desresalatar jugador anterior
            jugadorTurnoActual = 0;
            // TODO resaltar nuevo jugador
        }        
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
        if (!jugadoresAnteriores.isEmpty()){
            do {
                indiceJugador = obtenerNumeroRandom(4);
                // verificar si jugador ya jugo en la anterior ronda de empate
            } while (indiceJugador == jugadoresAnteriores.get(equipoTurnoActual));
        } else {
            indiceJugador = obtenerNumeroRandom(4);
        }
                
        String[] jugador = controlEquipo.obtenerNombresJugador(indiceEquipoActual, indiceJugador);
        //Guardamos el inddice del jugador en el indice correspondiente
        if (equipoTurnoActual < jugadoresAnteriores.size() && equipoTurnoActual >= 0) {
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
        
        // TODO actualizar puntaje equipo en ventana puntajeActual
        
        // Pintar jugada en ventana
        controlVentana.mostraMensajeEmergente(jugador[0] + "-" + jugador[1]
                + "hizo " + nombreTiro + " obteniendo " + puntaje + " puntos"
        );
                
        // Verificar si se termino la ronda de empate
        if (equipoTurnoActual == empatados.size()-1) {
            // Quitamos equipos que no tenga el maximo puntaje
            empatados.removeIf(e -> controlEquipo.getPuntajeEquipo(e) < maximoPuntajeActual);
            // verificar si hay empate otra vez
            empate();
            return;
        }
        // pasar el turno al siguiente equipo
        if (equipoTurnoActual < empatados.size()-1) {
            equipoTurnoActual += 1;
        }
    }

    /**
     * Carga los nombres de equipos y jugadores en la vista
     */
    private void iniciarEquipos() {
        ArrayList<Equipo> equipos = controlEquipo.getEquipos();
        if (equipos == null || equipos.size() < 2) {
            System.err.println("Se necesitan al menos 2 equipos cargados. Revisa configuracion.properties");
            return;
        }

        Equipo equipoA = equipos.get(0);
        Equipo equipoB = equipos.get(1);

        // delegamos la actualización a ControlVentana
        controlVentana.actualizarEquipos(equipoA, equipoB);
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
            //Cambiar el actionCommand del boton lanzar a LanzarEmpate
            //controlVentana.activarModoLanzarEmpate();
            return;
        }
        // TODO Si no hay empate entonces mostrar equipo ganador
        // controlVentana.activarModoLanzarEmpate();
        this.partidasJugadas += 1;
        if (partidasJugadas == partidasMaximas) {
            //TODO mostar boton salir, ocultar boton de otra partida
        } else {
            //TODO mostrar boton terminar y otra partida
        }
    }

    /**
     * Método para obtener numero random
     *
     * @param limite
     * @return
     */
    private int obtenerNumeroRandom(int limite) {
        Random numAleatorio;
        numAleatorio = new Random();
        int numero = numAleatorio.nextInt(limite + 1);
        return numero;
    }

    /**
     * Metodo para cargar archivo de propiedades
     *
     * @param archivo
     */
    public void cargarArchivoProperties(File archivo) {
        ConexionProperties con = new ConexionProperties();
        Properties props = con.cargar(archivo);
        if (props == null) {
            return;
        }
        // Delegamos la creacion de objetos
        CargarObjetos co = new CargarObjetos();
        co.cargarEquipos(props, controlEquipo, controlJugador);
        co.cargarPuntajes(props, controlTiro);
        // Minimo deben haber dos equipos y dos tipos de tiros para iniciar el juego
        if(controlEquipo.obtenerTamaño() >= 2 && controlTiro.getTamaño() >= 2){
            // Enviar a pantalla de juego
        } else {
            // Resetear lista equipos y puntajes
            controlEquipo.borrarTodo();
            controlTiro.borrarTodo();
            // Enviar mensaje no emergente de que el archivo no cumple con lo requerido para arrancar el juego
        }
        
    }   

    public void cargarArchivoSerilizable(File archivo) {
        // conectar a la clase serlizador        
    }
}
