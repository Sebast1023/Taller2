package udistrital.avanzada.taller2.control;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import udistrital.avanzada.taller2.modelo.Equipo;
import udistrital.avanzada.taller2.modelo.Jugador;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para la clase ControlEquipo.
 * 
 * Esta clase verifica el correcto funcionamiento de los métodos de
 * gestión de equipos y jugadores, incluyendo creación, eliminación,
 * adición de jugadores, puntajes y reinicio de valores.
 *
 * @author sebas
 * @since 07/10/2025
 */
public class ControlEquipoTest {

    private ControlEquipo control;

    /**
     * Método ejecutado una sola vez antes de todas las pruebas.
     * Se usa para inicializar recursos globales 
     */
    @BeforeAll
    static void setUpBeforeAll() {
    }

    /**
     * Método ejecutado una sola vez después de todas las pruebas.
     * Se usa para liberar recursos o imprimir mensajes de finalización.
     */
    @AfterAll
    static void tearDownAfterAll() {
    }

    /**
     * Método que se ejecuta antes de cada prueba individual.
     * Crea un nuevo objeto ControlEquipo limpio para cada test.
     */
    @BeforeEach
    void setUp() {
        control = new ControlEquipo();
    }

    /**
     * Método que se ejecuta después de cada prueba individual.
     * Libera los recursos usados en la prueba actual.
     */
    @AfterEach
    void tearDown() {
        control = null;
    }

    /**
     * Verifica que al crear un equipo, se asigne correctamente su nombre
     * y se retorne el índice correspondiente en la lista interna.
     */
    @Test
    void testCrearEquipo() {
        int indice = control.crearEquipo("Llaneros");
        assertEquals(0, indice, "El índice del primer equipo debe ser 0");
        assertEquals("Llaneros", control.getEquipo(indice).getNombre(), 
                     "El nombre del equipo creado debe coincidir con el asignado");
    }

    /**
     * Comprueba que al agregar un equipo manualmente con ControlEquipo agregarEquipo},
     * el tamaño de la lista de equipos aumente correctamente.
     */
    @Test
    void testAgregarEquipoYObtenerTamaño() {
        Equipo e1 = new Equipo();
        e1.setNombre("Llaneros");
        control.agregarEquipo(e1);
        assertEquals(1, control.getTamaño(), "Debe haber un equipo en la lista");
    }

    /**
     * Prueba la funcionalidad de agregar un jugador a un equipo existente.
     * Se espera que el jugador quede registrado correctamente.
     */
    @Test
    void testAgregarJugador() {
        int indice = control.crearEquipo("Llaneros");
        Jugador j = new Jugador("Pedro", "El Toro");
        control.agregarJugador(indice, j);
        assertEquals("Pedro", control.obtenerJugador(indice, 0).getNombre(),
                     "El nombre del jugador agregado debe ser 'Pedro'");
    }

    /**
     * Verifica que el método ControlEquipo agregarPuntos sume
     * correctamente el puntaje de un equipo.
     */
    @Test
    void testAgregarPuntosYObtenerPuntaje() {
        int indice = control.crearEquipo("Llaneros");
        control.agregarPuntos(indice, 5);
        assertEquals(5, control.obtenerPuntaje(indice),
                     "El puntaje del equipo debe ser 5 después de sumar puntos");
    }

    /**
     * Comprueba que el método ControlEquipo resetearPuntaje reinicie
     * correctamente los puntajes de todos los equipos a cero.
     */
    @Test
    void testResetearPuntaje() {
        int indice = control.crearEquipo("Llaneros");
        control.agregarPuntos(indice, 10);
        control.resetearPuntaje();
        assertEquals(0, control.obtenerPuntaje(indice),
                     "El puntaje debe reiniciarse a cero");
    }

    /**
     * Verifica que el método ControlEquipo borrarTodo elimine
     * correctamente todos los equipos almacenados.
     */
    @Test
    void testBorrarTodo() {
        control.crearEquipo("A");
        control.crearEquipo("B");
        control.borrarTodo();
        assertEquals(0, control.getTamaño(), "La lista debe quedar vacía");
    }

    /**
     * Prueba el método ControlEquipo obtenerNombresJugador asegurando
     * que se retornen correctamente el nombre y apodo del jugador.
     */
    @Test
    void testObtenerNombresJugador() {
        int indice = control.crearEquipo("Llaneros");
        Jugador j = new Jugador("Pedro", "El Toro");
        control.agregarJugador(indice, j);
        String[] datos = control.obtenerNombresJugador(indice, 0);
        assertEquals("Pedro", datos[0], "El nombre del jugador debe ser 'Pedro'");
        assertEquals("El Toro", datos[1], "El apodo del jugador debe ser 'El Toro'");
    }

    /**
     * Prueba los métodos getter y setter del atributo ControlEquipo equipos
     * Se asegura que los valores asignados se conserven correctamente.
     */
    @Test
    void testSetYGetEquipos() {
        ArrayList<Equipo> lista = new ArrayList<>();
        Equipo e1 = new Equipo();
        e1.setNombre("Los Criollos");
        lista.add(e1);
        control.setEquipos(lista);
        assertEquals("Los Criollos", control.getEquipos().get(0).getNombre(),
                     "El nombre del equipo obtenido debe coincidir con el establecido");
    }

    /**
     * Comprueba que el método ControlEquipo removeEquipo elimine
     * correctamente un equipo específico de la lista.
     */
    @Test
    void testRemoveEquipo() {
        control.crearEquipo("A");
        control.crearEquipo("B");
        control.removeEquipo(0);
        assertEquals(1, control.getTamaño(), "Debe quedar un solo equipo tras eliminar uno");
    }
}

