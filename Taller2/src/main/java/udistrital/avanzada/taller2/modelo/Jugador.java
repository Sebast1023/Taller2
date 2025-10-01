/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.modelo;

/**
 * Clase Jugador.java representa a los jugadores del juego
 * Sus instancias seran agrupadas en equipos de a 4
 * 
 * @author Mauricio
 * @since 30/09/2025
 */
public class Jugador {
    String nombre;
    String apodo;
    // imagen de la persona es una ubicacion local
    String foto; 
    // constructor
    public Jugador() {
    }
    /**
     * Constructor con los parametros
     * @param nombre
     * @param apodo
     * @param foto ubicacion local de la imagen
     */
    public Jugador(String nombre, String apodo, String foto) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getFoto() {
        return foto;
    }
    /**
     * metodo setter de la foto
     * @param foto ubicacion local de la imagen
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    
}
