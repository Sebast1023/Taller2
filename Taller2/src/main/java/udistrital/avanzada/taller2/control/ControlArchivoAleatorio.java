/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.control;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import udistrital.avanzada.taller2.modelo.ConexionAleatoria;

/**
 * Clase que maneja logica para la interaccion con los archivos Aleatorios
 *
 * @author sebastian
 * @author 07/210/2025
 */
public class ControlArchivoAleatorio {

    private ConexionAleatoria aleatoria;
    private ImpresorConsola impresor;

    /**
     * Rutas por defecto
     */
    private final String rutaResultados = "Specs/data/resultados.data";

    /**
     * contador interno de registros (clave)
     */
    private int contadorClaves = 1;

    /**
     * Constructor con inyección de ImpresorConsola
     *
     * @param impresor clase que implenta la interfaz ImpresorConsola
     */
    public ControlArchivoAleatorio(ImpresorConsola impresor) {
        this.impresor = impresor;
        try {
            this.aleatoria = new ConexionAleatoria(rutaResultados);
        } catch (IOException ex) {
            impresor.mostrarErrorEnConsola("ControlConexion - no se pudo abrir archivo aleatorio: " + ex.getMessage());
        }
    }

    /**
     * Guarda el resultado de un equipo
     *
     * @param nombreEquipo
     * @param nombresJugadores
     * @param resultado
     */
    public void guardarResultado(String nombreEquipo, String[] nombresJugadores, String resultado) {
        System.out.println("guradra");
        if (aleatoria == null) {
            return;
        }
        try {
            aleatoria.escribirRegistro(contadorClaves++, nombreEquipo, nombresJugadores, resultado);
        } catch (IOException ex) {
            impresor.mostrarErrorEnConsola("ControlConexion - error guardando resultado: " + ex.getMessage());
        }
    }

    /**
     * Obtiene todos los resultados guardados
     *
     * @return Lista de strings con resultados
     */
    public List<String> obtenerResultados() {
        if (aleatoria == null) {
            return java.util.Collections.emptyList();
        }
        try {
            return aleatoria.leerRegistros();
        } catch (IOException ex) {
            impresor.mostrarErrorEnConsola("ControlConexion - error leyendo resultados: " + ex.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Metodo que cierra el archivo aleatorio
     */
    public void cerrar() {
        try {
            if (aleatoria != null) {
                aleatoria.cerrar();                
            }
        } catch (IOException ex) {
            impresor.mostrarErrorEnConsola("ControlConexion - error cerrando archivo aleatorio: " + ex.getMessage());
        }
    }
}
