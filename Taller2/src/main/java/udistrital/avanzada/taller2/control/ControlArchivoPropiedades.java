/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.control;

import java.io.File;
import java.util.Properties;
import udistrital.avanzada.taller2.modelo.ConexionProperties;

/**
 *
 * @author mauri
 */
public class ControlArchivoPropiedades {
    /** Carga desde archivo de propiedades (.properties) */
    public  Properties cargarArchivo(File archivo) {
        ConexionProperties con = new ConexionProperties();
        Properties props = con.cargar(archivo);
        return props;
    }
}
