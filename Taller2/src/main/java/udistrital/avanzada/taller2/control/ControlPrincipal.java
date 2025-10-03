/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.control;

import java.util.ArrayList;
import java.util.Random;
import udistrital.avanzada.taller2.modelo.Equipo;
import udistrital.avanzada.taller2.vista.Ventana;

/**
 * Logica de negocio
 *
 * @author
 * @since 30/09/2025
 */
public class ControlPrincipal {

    Ventana ventana;
    private ControlEquipo controlEquipo;
    private ControlTiro controlTiro;
    private ArrayList<Equipo> empatados;
    

    public ControlPrincipal() {
        this.ventana = new Ventana();
        this.controlEquipo = new ControlEquipo();   
        this.controlTiro = new ControlTiro();
    }

    private void iniciarJuego(){
        
    }
    
    private void iniciarEquipos() {
        //obtener datos del properties o serializador
        //controlVentana.
                
    }
    private void empate(){
    
    } 
    /**
     * Método para obtener numero random 
     * @param limite
     * @return 
     */
    private int obtenerNumeroRandom(int limite){
        Random numAleatorio; 
        numAleatorio = new Random(); 
        int numero = numAleatorio.nextInt(limite+1) ;
        return numero;
    }
    
}
