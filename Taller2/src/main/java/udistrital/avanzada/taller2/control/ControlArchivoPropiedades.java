/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.control;

import java.io.File;
import java.util.Properties;
import udistrital.avanzada.taller2.modelo.ConexionProperties;

/**
 * Clase que maneja logica para la interaccion con los
 * archivos de propiedades
 * 
 * @author mauricio
 * @since 07/10/2025
 */
public class ControlArchivoPropiedades {
    /**
     * Carga desde archivo de propiedades (.properties
     * 
     * @param archivo archivo con extension .properties
     * @return objeto Properties si no null
     */    
    public  Properties cargarArchivo(File archivo) {
        ConexionProperties con = new ConexionProperties();
        Properties props = con.cargar(archivo);
        return props;
    }
}
