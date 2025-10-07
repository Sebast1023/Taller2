/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.control;

import java.util.ArrayList;
import udistrital.avanzada.taller2.modelo.Equipo;
import udistrital.avanzada.taller2.modelo.SerializadorEquipos;

/**
 *
 * @author mauri
 */
public class ServicioArchivo {
    
    public void serializarEquipos(ArrayList<Equipo> equipos) {
        SerializadorEquipos se = new SerializadorEquipos();
        se.config();
        se.escribirArchivoSerializado(equipos);
        se.cerrarArchivoSerializadoOut();
        se.cerrarArchivoSerializadoIn();
    }

    public boolean existeArchivoSerializadoEquipos() {
        SerializadorEquipos se = new SerializadorEquipos();
        boolean existe = se.existeBin();
        se.cerrarArchivoSerializadoOut();
        se.cerrarArchivoSerializadoIn();
        return existe;
    }
}
