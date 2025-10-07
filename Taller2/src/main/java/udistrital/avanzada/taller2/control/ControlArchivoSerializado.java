/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.control;

import java.io.File;
import java.util.ArrayList;
import udistrital.avanzada.taller2.modelo.Equipo;
import udistrital.avanzada.taller2.modelo.ConexionSerializador;

/**
 * Clase que maneja logica para la interaccion con los
 * archivos serializados
 * 
 * @author mauricio
 * @since 07/10/2025
 */
public class ControlArchivoSerializado {

    public ArrayList<Equipo> cargarArchivoSerializable(File archivo) {
        ConexionSerializador se = new ConexionSerializador();                
        ArrayList<Equipo> equipos = se.leerArchivoSerializado(archivo);
        se.cerrarArchivoSerializadoIn();
        se.cerrarArchivoSerializadoOut();        
        return  equipos;
    }

    public void serializarEquipos(ArrayList<Equipo> equipos) {
        ConexionSerializador se = new ConexionSerializador();
        se.config();
        se.escribirArchivoSerializado(equipos);
        se.cerrarArchivoSerializadoOut();
        se.cerrarArchivoSerializadoIn();
    }

    public boolean existeArchivoSerializadoEquipos() {
        ConexionSerializador se = new ConexionSerializador();
        boolean existe = se.existeBin();
        se.cerrarArchivoSerializadoOut();
        se.cerrarArchivoSerializadoIn();
        return existe;
    }
}
