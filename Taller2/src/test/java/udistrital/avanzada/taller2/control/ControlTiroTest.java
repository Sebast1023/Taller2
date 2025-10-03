/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package udistrital.avanzada.taller2.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import udistrital.avanzada.taller2.modelo.Tiro;

/**
 * Clase de pruebas para ControlTiro
 * Aquí se validan los métodos de la clase ControlTiro
 * @author sebas 
 * @since 02/10/2025
 */
public class ControlTiroTest {

    private ControlTiro control;

    /**
     * Método que se ejecuta antes de cada prueba
     * Se asegura de que siempre iniciemos con un ControlTiro vacío.
     */
    @BeforeEach
    public void setUp() {
        control = new ControlTiro();
    }

    /**
     * Prueba que al crear tiros, el tamaño de la lista aumenta correctamente.
     */
    @Test
    public void testCrearTiroYObtenerTamaño() {
        // Al inicio la lista debe estar vacía
        assertEquals(0, control.getTamaño());

        // Se crean dos tiros (Moñona y Engarzada)
        control.crearTiro("Moñona", 8);
        control.crearTiro("Engarzada", 5);

        // Ahora la lista debe tener 2 elementos
        assertEquals(2, control.getTamaño());
    }

    /**
     * Prueba que se pueda recuperar un tiro válido con su nombre y puntaje.
     */
    @Test
    public void testGetTiroValido() {
        // Crear un tiro
        control.crearTiro("Moñona", 8);

        // Obtener el primer tiro de la lista
        Tiro uno = control.getTiro(0);

        // Verificar que no es null y que sus datos son correctos
        assertNotNull(uno);
        assertEquals("Moñona", uno.getNombre());
        assertEquals(8, uno.getPuntos());
    }

    /**
     * Prueba que si se pide un índice negativo, se retorne null.
     */
    @Test
    public void testGetTiroIndiceNegativo() {
        control.crearTiro("Moñona", 8);

        // Pedir un índice inválido 
        Tiro tiro = control.getTiro(-1);

        // Se espera null porque el índice no existe
        assertNull(tiro);
    }

    /**
     * Prueba que si se pide un índice mayor al tamaño de la lista,
     * se retorne null.
     */
    @Test
    public void testGetTiroIndiceFueraDeRango() {
        control.crearTiro("Moñona", 8);

        // Pedir un índice que no existe (la lista tiene 1, pedimos el 5)
        Tiro tiro = control.getTiro(5);

        // Se espera null porque ese índice está fuera de rango
        assertNull(tiro);
    }
}